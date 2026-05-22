package com.tejasnirman.api.service;

import com.tejasnirman.api.dto.LoanApplicationDTO;
import com.tejasnirman.api.model.*;
import com.tejasnirman.api.repository.BankRepository;
import com.tejasnirman.api.repository.LoanRepository;
import com.tejasnirman.api.repository.PropertyRepository;
import com.tejasnirman.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BankRepository bankRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository,
                       BankRepository bankRepository,
                       UserRepository userRepository,
                       PropertyRepository propertyRepository) {
        this.loanRepository = loanRepository;
        this.bankRepository = bankRepository;
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
    }

    public LoanApplicationDTO applyForLoan(Long userId, LoanApplicationDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

        Bank bank = bankRepository.findById(dto.getBankId())
                .orElseThrow(() -> new RuntimeException("Bank not found with id: " + dto.getBankId()));

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBank(bank);
        loan.setAmount(dto.getAmount());
        loan.setTenure(dto.getTenure());
        
        // Use interest rate from bank if not provided or just default to bank's rate
        double interestRate = bank.getInterestRate() != null ? bank.getInterestRate() : 8.5;
        loan.setInterestRate(interestRate);
        loan.setStatus(LoanStatus.PENDING);

        if (dto.getPropertyId() != null) {
            Property property = propertyRepository.findById(dto.getPropertyId()).orElse(null);
            loan.setProperty(property);
        }

        // Calculate EMI: P * r * (1+r)^n / ((1+r)^n - 1)
        double amountDouble = dto.getAmount().doubleValue();
        double monthlyRate = (interestRate / 12.0) / 100.0;
        int months = dto.getTenure() * 12;

        double emiVal;
        if (monthlyRate > 0) {
            emiVal = (amountDouble * monthlyRate * Math.pow(1 + monthlyRate, months)) / (Math.pow(1 + monthlyRate, months) - 1);
        } else {
            emiVal = amountDouble / months;
        }

        loan.setEmi(BigDecimal.valueOf(emiVal).setScale(2, RoundingMode.HALF_UP));

        Loan saved = loanRepository.save(loan);
        return convertToDTO(saved);
    }

    public List<LoanApplicationDTO> getMyLoans(Long userId) {
        return loanRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<LoanApplicationDTO> getLoansByBank(Long bankId) {
        return loanRepository.findByBankId(bankId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public LoanApplicationDTO updateLoanStatus(Long loanId, String status) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found with id: " + loanId));

        loan.setStatus(LoanStatus.valueOf(status.toUpperCase()));
        Loan updated = loanRepository.save(loan);
        return convertToDTO(updated);
    }

    private LoanApplicationDTO convertToDTO(Loan loan) {
        LoanApplicationDTO dto = new LoanApplicationDTO();
        dto.setId(loan.getId());
        dto.setBankId(loan.getBank().getId());
        dto.setBankName(loan.getBank().getBankName());
        dto.setAmount(loan.getAmount());
        dto.setTenure(loan.getTenure());
        dto.setInterestRate(loan.getInterestRate());
        dto.setEmi(loan.getEmi());
        dto.setStatus(loan.getStatus().name());
        dto.setAppliedAt(loan.getAppliedAt());

        if (loan.getProperty() != null) {
            dto.setPropertyId(loan.getProperty().getId());
            dto.setPropertyTitle(loan.getProperty().getTitle());
        }

        return dto;
    }
}
