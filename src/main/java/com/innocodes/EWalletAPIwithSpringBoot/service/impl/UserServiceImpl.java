package com.innocodes.EWalletAPIwithSpringBoot.service.impl;

import com.innocodes.EWalletAPIwithSpringBoot.dtos.requestDto.UserSignupDto;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.UserResponseDto;
import com.innocodes.EWalletAPIwithSpringBoot.enums.Roles;
import com.innocodes.EWalletAPIwithSpringBoot.enums.Status;
import com.innocodes.EWalletAPIwithSpringBoot.exception.ValidationException;
import com.innocodes.EWalletAPIwithSpringBoot.models.User;
import com.innocodes.EWalletAPIwithSpringBoot.repository.UserRepository;
import com.innocodes.EWalletAPIwithSpringBoot.service.UserService;
import com.innocodes.EWalletAPIwithSpringBoot.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AppUtil app;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
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
        return null;
    }
}
