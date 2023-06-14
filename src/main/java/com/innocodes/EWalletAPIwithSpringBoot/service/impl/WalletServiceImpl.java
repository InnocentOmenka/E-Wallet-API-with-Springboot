package com.innocodes.EWalletAPIwithSpringBoot.service.impl;

import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.WalletResponseDto;
import com.innocodes.EWalletAPIwithSpringBoot.models.Wallet;
import com.innocodes.EWalletAPIwithSpringBoot.repository.WalletRepository;
import com.innocodes.EWalletAPIwithSpringBoot.service.WalletServices;
import com.innocodes.EWalletAPIwithSpringBoot.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletServices {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private AppUtil appUtil;

    @Override
    public WalletResponseDto updateWallet(String email, BigDecimal amount) {

        Wallet wallet = walletRepository.findByEmail(email)
                .orElse(Wallet.builder()
                        .walletUuid(appUtil.generateSerialNumber("0"))
                        .balance(BigDecimal.ZERO)
                        .email(email)
                        .build());
        wallet.setBalance(wallet.getBalance().add(amount));
        wallet.setUpdatedAt();

        return appUtil.getMapper().convertValue(walletRepository.save(wallet), WalletResponseDto.class);
    }}
