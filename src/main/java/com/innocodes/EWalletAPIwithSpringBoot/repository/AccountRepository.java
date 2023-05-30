package com.innocodes.EWalletAPIwithSpringBoot.repository;

import com.innocodes.EWalletAPIwithSpringBoot.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
