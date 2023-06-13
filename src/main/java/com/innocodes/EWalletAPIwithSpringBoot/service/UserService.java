package com.innocodes.EWalletAPIwithSpringBoot.service;

import com.innocodes.EWalletAPIwithSpringBoot.dtos.requestDto.ActivateUserDto;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.requestDto.ChangePasswordDto;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.requestDto.UserLoginDto;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.UserResponseDto;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.requestDto.UserSignupDto;

public interface UserService {
    UserResponseDto signup(UserSignupDto userDto);
    String sendToken(String userEmail, String mailSubject);

    UserResponseDto activateUser(ActivateUserDto activateUserDto);

    UserResponseDto login(UserLoginDto userLoginDto);

    String resetPassword(ChangePasswordDto changePasswordDto);
}
