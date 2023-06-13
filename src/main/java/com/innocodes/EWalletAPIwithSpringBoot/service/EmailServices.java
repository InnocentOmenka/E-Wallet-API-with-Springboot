package com.innocodes.EWalletAPIwithSpringBoot.service;

import com.innocodes.EWalletAPIwithSpringBoot.dtos.requestDto.MailDto;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.ApiResponse;

public interface EmailServices {
    ApiResponse<MailDto> sendEmail(MailDto mailDto);
}
