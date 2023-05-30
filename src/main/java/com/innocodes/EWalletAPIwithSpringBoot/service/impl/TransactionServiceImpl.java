package com.innocodes.EWalletAPIwithSpringBoot.service.impl;

import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.TransactionResponseDto;
import com.innocodes.EWalletAPIwithSpringBoot.models.Transactions;
import com.innocodes.EWalletAPIwithSpringBoot.repository.TransactionRepository;
import com.innocodes.EWalletAPIwithSpringBoot.service.TransactionServices;
import com.innocodes.EWalletAPIwithSpringBoot.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionServices {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AppUtil appUtil;

    @Override
    public TransactionResponseDto logTransaction(TransactionResponseDto transactionResponseDto) {

        if (!transactionRepository
                .existsByReferenceOrId(transactionResponseDto.getReference(), transactionResponseDto.getId())) {

            Transactions transaction = appUtil.getMapper().convertValue(transactionResponseDto, Transactions.class);
            transaction.setCustomerEmail(transactionResponseDto.getCustomer().getEmail());
            transactionRepository.save(transaction);
        }

        return transactionResponseDto;
    }
}
