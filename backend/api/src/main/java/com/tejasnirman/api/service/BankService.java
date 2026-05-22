package com.tejasnirman.api.service;

import com.tejasnirman.api.dto.BankDTO;
import com.tejasnirman.api.model.Bank;
import com.tejasnirman.api.model.User;
import com.tejasnirman.api.repository.BankRepository;
import com.tejasnirman.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankService {

    private final BankRepository bankRepository;
    private final UserRepository userRepository;

    @Autowired
    public BankService(BankRepository bankRepository, UserRepository userRepository) {
        this.bankRepository = bankRepository;
        this.userRepository = userRepository;
    }

    public List<BankDTO> getAllBanks() {
        return bankRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BankDTO getBankById(Long id) {
        Bank bank = bankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank not found with id: " + id));
        return convertToDTO(bank);
    }

    public BankDTO getBankByUserId(Long userId) {
        Bank bank = bankRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Bank profile not found for user: " + userId));
        return convertToDTO(bank);
    }

    public BankDTO createBank(Long userId, BankDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

        bankRepository.findByUserId(userId).ifPresent(b -> {
            throw new RuntimeException("Bank profile already exists for user: " + userId);
        });

        Bank bank = new Bank();
        bank.setUser(user);
        bank.setBankName(dto.getName());
        bank.setInterestRate(dto.getInterestRate());
        bank.setMaxLoanAmount(dto.getMaxLoanAmount());
        bank.setProcessingFee(dto.getProcessingFee());
        bank.setFeatures(dto.getFeatures());
        bank.setLogoUrl(dto.getLogoUrl());

        Bank saved = bankRepository.save(bank);
        return convertToDTO(saved);
    }

    private BankDTO convertToDTO(Bank bank) {
        BankDTO dto = new BankDTO();
        dto.setId(bank.getId());
        dto.setName(bank.getBankName());
        dto.setInterestRate(bank.getInterestRate());
        dto.setMaxLoanAmount(bank.getMaxLoanAmount());
        dto.setProcessingFee(bank.getProcessingFee());
        dto.setFeatures(bank.getFeatures());
        dto.setLogoUrl(bank.getLogoUrl());

        if (bank.getUser() != null) {
            dto.setContactEmail(bank.getUser().getEmail());
        }

        return dto;
    }
}
