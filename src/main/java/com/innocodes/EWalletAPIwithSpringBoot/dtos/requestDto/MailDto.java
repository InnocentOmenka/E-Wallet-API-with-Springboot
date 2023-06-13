package com.innocodes.EWalletAPIwithSpringBoot.dtos.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class MailDto {

    private String to;
    private String subject;
    private String message;
}
