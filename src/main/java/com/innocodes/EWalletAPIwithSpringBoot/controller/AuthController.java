package com.innocodes.EWalletAPIwithSpringBoot.controller;

import com.innocodes.EWalletAPIwithSpringBoot.dtos.requestDto.ActivateUserDto;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.requestDto.ChangePasswordDto;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.requestDto.UserLoginDto;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.requestDto.UserSignupDto;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.ApiResponse;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.UserResponseDto;
import com.innocodes.EWalletAPIwithSpringBoot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userServices;

    @Operation(summary = "Create a new user account",
            description = "After creating your account, a verification code will be sent to your provided email. \n" +
                    "Copy the code from you email and enter it in the 'activate-account end point'.\n" +
                    "If no code was sent to you, then use the 'resend-token' end point to resend a new activation code")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserResponseDto>> createUser(@RequestBody UserSignupDto userSignupDto) {

        return ResponseEntity.ok().body(new ApiResponse<>("User signup successful", true, userServices.signup(userSignupDto)));
    }

    @Operation(summary = "Activates a newly created account or an inactive account",
            description = "Once activated, you can then login using the 'login' end point.\n" +
                    "Ensure that you enter the complete verification code sent to your email, it starts with 'verify...'")
    @PostMapping("/activate-account")
    public ResponseEntity<ApiResponse<UserResponseDto>> activateUser(@RequestBody ActivateUserDto activateUserDto) {
        return ResponseEntity.ok(new ApiResponse<>("User activated!",true, userServices.activateUser(activateUserDto)));
    }

    @Operation(summary = "Generates a JWT token upon successful login that will be used for Authorizations",
            description = "You wiil need to append the string 'Bearer ' to the token before adding it to your Authorization header. NOTE: " +
                    "there's a single space after the 'Bearer ' string. Don't forget to include it.")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDto>> loginUser(@RequestBody UserLoginDto userLoginDto) {

        return ResponseEntity.ok(new ApiResponse<>("User logged in successfully!",true, userServices.login(userLoginDto)));
    }

    @Operation(summary = "Resend account activation token or password reset token")
    @PostMapping("/resend-token")
    public ResponseEntity<ApiResponse<String>> resendToken(@Valid @RequestParam String email, @Valid @RequestParam String reason) {
        return ResponseEntity.ok(new ApiResponse<>(userServices.sendToken(email, reason),true, email));
    }

    @Operation(summary = "Resets a password after a reset token has been confirmed",
            description = "Before using this endpoint, ensure the forgot password token has been activated")
    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<String>> forgotPassword(@RequestBody ChangePasswordDto changePasswordDto) {
        return ResponseEntity.ok(new ApiResponse<>(userServices.resetPassword(changePasswordDto),true, null));
    }


}
