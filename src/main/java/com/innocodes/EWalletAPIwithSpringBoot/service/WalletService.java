package com.innocodes.EWalletAPIwithSpringBoot.service;

import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.WalletResponseDto;

import java.math.BigDecimal;

public interface WalletService {
    WalletResponseDto updateWallet(String eamil, BigDecimal amount);
}
