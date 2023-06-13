package com.innocodes.EWalletAPIwithSpringBoot.repository;

import com.innocodes.EWalletAPIwithSpringBoot.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    boolean existsByReferenceOrId(String reference, String id);
}
