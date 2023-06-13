package com.innocodes.EWalletAPIwithSpringBoot.dtos.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordDto {
    @NotBlank(message = "email must be provided")
    private String email;
    @NotBlank(message = "password must be provided")
    private String password;
    //    @NotBlank(message = "verification token must be provided")
    private String verificationToken;
}
