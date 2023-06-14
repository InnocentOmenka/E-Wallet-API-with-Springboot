package com.innocodes.EWalletAPIwithSpringBoot.service;

import com.innocodes.EWalletAPIwithSpringBoot.dtos.paystack.AccountDto;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.paystack.BankDto;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.paystack.FundTransferDto;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.paystack.InitiateTransactionDto;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.ApiResponse;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.TransactionResponseDto;

import java.util.List;

public interface PaymentServices {
    ApiResponse<List<BankDto>> fetchBanks(String currency, String type);

    ApiResponse<String> getPaymentLink(InitiateTransactionDto transactionDto);

    ApiResponse<TransactionResponseDto> verifyTransaction(String paymentReference);

    ApiResponse<AccountDto> getAccountDetails(String accountNumber, String bankCode);

    ApiResponse<FundTransferDto> createTransferRecipient(AccountDto accountDto);

    ApiResponse<TransactionResponseDto> initiateTransfer(FundTransferDto fundTransferDto);
}
