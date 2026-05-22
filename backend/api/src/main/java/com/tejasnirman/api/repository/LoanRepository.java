package com.tejasnirman.api.repository;

import com.tejasnirman.api.model.Loan;
import com.tejasnirman.api.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserId(Long userId);
    List<Loan> findByBankId(Long bankId);
    List<Loan> findByStatus(LoanStatus status);
}
