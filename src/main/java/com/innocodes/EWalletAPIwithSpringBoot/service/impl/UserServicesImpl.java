package com.innocodes.EWalletAPIwithSpringBoot.service.impl;

import com.innocodes.EWalletAPIwithSpringBoot.dtos.requestDto.*;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.UserResponseDto;
import com.innocodes.EWalletAPIwithSpringBoot.enums.Roles;
import com.innocodes.EWalletAPIwithSpringBoot.enums.Status;
import com.innocodes.EWalletAPIwithSpringBoot.exception.AuthenticationException;
import com.innocodes.EWalletAPIwithSpringBoot.exception.NotFoundException;
import com.innocodes.EWalletAPIwithSpringBoot.exception.ValidationException;
import com.innocodes.EWalletAPIwithSpringBoot.models.User;
import com.innocodes.EWalletAPIwithSpringBoot.models.Wallet;
import com.innocodes.EWalletAPIwithSpringBoot.repository.UserRepository;
import com.innocodes.EWalletAPIwithSpringBoot.repository.WalletRepository;
import com.innocodes.EWalletAPIwithSpringBoot.security.CustomUserDetailsServices;
import com.innocodes.EWalletAPIwithSpringBoot.security.JwtUtil;
import com.innocodes.EWalletAPIwithSpringBoot.service.UserService;
import com.innocodes.EWalletAPIwithSpringBoot.util.AppUtil;
import com.innocodes.EWalletAPIwithSpringBoot.util.LocalMemStorage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserService {

    private final AppUtil app;

    private final LocalMemStorage memStorage;

    private final PasswordEncoder passwordEncoder;
    private final EmailServicesImpl mailSender;

    private final Logger logger = LoggerFactory.getLogger(UserServicesImpl.class);


    private final JwtUtil jwtUtil;
    private final AuthenticationManager auth;

    private final CustomUserDetailsServices userDetailsServices;

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Override
    public UserResponseDto signup(UserSignupDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ValidationException("User with email: " + userDto.getEmail() + " already exists");
        }

        User newUser = app.getMapper().convertValue(userDto, User.class);
        String userId = app.generateSerialNumber("usr");
        newUser.setUuid(userId);
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRoles(Roles.USER.name());
        newUser.setStatus(Status.INACTIVE.name());

        newUser = userRepository.save(newUser);
        sendToken(newUser.getEmail(), "Activate your account");

        return app.getMapper().convertValue(newUser, UserResponseDto.class);
    }

    @Override
    public String sendToken(String userEmail, String subject) {
        if (!userRepository.existsByEmail(userEmail))
            throw new ValidationException("User with email: " + userEmail + " does not exists");

        String token = app.generateSerialNumber("verify");
        memStorage.save(userEmail, token, 900); //expires in 15mins

        MailDto mailDto = MailDto.builder()
                .to(userEmail)
                .subject(subject.toUpperCase())
                .message(String.format("Use this generated token to %s: %s (Expires in 15mins)", subject.toLowerCase(), token))
                .build();

        mailSender.sendEmail(mailDto);

        return "Verification Token sent";


    }

    @Override
    public UserResponseDto activateUser(ActivateUserDto activateUserDto) {
        validateToken(activateUserDto.getEmail(), activateUserDto.getVerificationToken());

        User userToActivate = userRepository.findByEmail(activateUserDto.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));


        userToActivate.setStatus(Status.ACTIVE.name());
        UserResponseDto userResponseDto = app.getMapper().convertValue(userRepository.save(userToActivate), UserResponseDto.class);

        Wallet newWallet = Wallet.builder()
                .walletUuid(app.generateSerialNumber("0"))
                .balance(BigDecimal.ZERO)
                .email(activateUserDto.getEmail())
                .build();

        walletRepository.save(newWallet);


        MailDto mailDto = MailDto.builder()
                .to(userToActivate.getEmail())
                .subject("YOUR ACCOUNT IS ACTIVATED")
                .message(String.format("Hi %s, \n You have successfully activated your account. Kindly login to start making use of the app", userResponseDto.getFirstName()))
                .build();

        mailSender.sendEmail(mailDto);

        return userResponseDto;
    }

    @Override
    public UserResponseDto login(UserLoginDto userLoginDto) {
        if (!app.validEmail(userLoginDto.getEmail()))
            throw new ValidationException("Invalid email");
        try {
            Authentication authentication = auth.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword())
            );
            UserResponseDto userResponseDto = null;

            if (authentication.isAuthenticated()) {
                User user = userRepository.findByEmail(userLoginDto.getEmail())
                        .orElseThrow(() -> new AuthenticationException("Invalid login credentials"));

                if (user.getStatus().equals(Status.INACTIVE.name()))
                    throw new ValidationException("User not active");

                logger.info("Generating access token for {}", user.getEmail());

                String accessToken = jwtUtil.generateToken(userDetailsServices.loadUserByUsername(user.getEmail()));

                user.setLastLoginDate(new Date());
                userResponseDto = app.getMapper().convertValue(userRepository.save(user), UserResponseDto.class);

                userResponseDto.setToken(accessToken);
            } else {
                throw new AuthenticationException("Invalid username or password");
            }
            return userResponseDto;

        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage());
        }

    }

    @Override
    public String resetPassword(ChangePasswordDto changePasswordDto) {
        validateToken(changePasswordDto.getEmail(), changePasswordDto.getVerificationToken());

        User userToResetPassword = userRepository.findByEmail(changePasswordDto.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

        userToResetPassword.setPassword(passwordEncoder.encode(changePasswordDto.getPassword()));
        userRepository.save(userToResetPassword);

        MailDto mailDto = MailDto.builder()
                .to(userToResetPassword.getEmail())
                .subject("PASSWORD RESET SUCCESSFUL")
                .message(String.format("Hi %s, \n You have successfully reset your password. Kindly login with your new password. " +
                        "\n If you did not authorize this, kindly create a ticket from our complaint section on the app", userToResetPassword.getFirstName()))
                .build();

        mailSender.sendEmail(mailDto);

        return "password reset successful";
    }

    public void validateToken(String memCachedKey, String token) {

        if (!app.validEmail(memCachedKey))
            throw new ValidationException("Invalid email");

        String cachedToken = memStorage.getValueByKey(memCachedKey);
        if (cachedToken == null)
            throw new ValidationException("Token expired");
        if (!cachedToken.equals(token))
            throw new ValidationException("Invalid token");

        if (!userRepository.existsByEmail(memCachedKey))
            throw new NotFoundException("User not found");
    }
}
