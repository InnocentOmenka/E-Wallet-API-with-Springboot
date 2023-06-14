package com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto;

import com.innocodes.EWalletAPIwithSpringBoot.dtos.paystack.BankDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankListApiResponseDto {
    private String message;
    private boolean status;
    private List<BankDto> data;
}
