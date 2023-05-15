package com.innocodes.EWalletAPIwithSpringBoot.dtos.requestDto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupDto {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String country;

    @NotNull
    private String state;

    @NotNull
    private String homeAddress;

    @NotNull
    private String password;

    @NotNull
    private String phoneNumber;
}
