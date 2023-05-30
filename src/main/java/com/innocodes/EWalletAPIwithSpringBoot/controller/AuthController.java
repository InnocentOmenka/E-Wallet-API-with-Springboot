package com.innocodes.EWalletAPIwithSpringBoot.controller;

import com.innocodes.EWalletAPIwithSpringBoot.dtos.requestDto.UserSignupDto;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.ApiResponse;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.UserResponseDto;
import com.innocodes.EWalletAPIwithSpringBoot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
