package com.innocodes.EWalletAPIwithSpringBoot.repository;

import com.innocodes.EWalletAPIwithSpringBoot.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
    Optional<Wallet> findByEmail(String email);
}
