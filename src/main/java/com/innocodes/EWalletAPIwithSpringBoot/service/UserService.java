package com.innocodes.EWalletAPIwithSpringBoot.service;

import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.UserResponseDto;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.requestDto.UserSignupDto;

public interface UserService {
    UserResponseDto signup(UserSignupDto userDto);
}
