package com.innocodes.EWalletAPIwithSpringBoot.service;

import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.TransactionResponseDto;

public interface TransactionServices {
    TransactionResponseDto logTransaction(TransactionResponseDto transactionResponseDto);
}
