package com.tejasnirman.api.repository;

import com.tejasnirman.api.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {
    Optional<Bank> findByUserId(Long userId);
}
