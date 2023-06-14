package com.innocodes.EWalletAPIwithSpringBoot.service;

import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.WalletResponseDto;

import java.math.BigDecimal;

public interface WalletServices {
    WalletResponseDto updateWallet(String email, BigDecimal amount);
}
