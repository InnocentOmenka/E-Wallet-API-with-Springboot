package com.innocodes.EWalletAPIwithSpringBoot.repository;

import com.innocodes.EWalletAPIwithSpringBoot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
