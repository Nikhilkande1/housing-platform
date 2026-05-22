package com.tejasnirman.api.controller;

import com.tejasnirman.api.dto.ApiResponse;
import com.tejasnirman.api.dto.BankDTO;
import com.tejasnirman.api.security.UserPrincipal;
import com.tejasnirman.api.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public ResponseEntity<List<BankDTO>> getAllBanks() {
        return ResponseEntity.ok(bankService.getAllBanks());
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_BANK')")
    public ResponseEntity<?> getMyBankProfile(@AuthenticationPrincipal UserPrincipal currentUser) {
        try {
            BankDTO bank = bankService.getBankByUserId(currentUser.getId());
            return ResponseEntity.ok(bank);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_BANK')")
    public ResponseEntity<?> createBankProfile(@AuthenticationPrincipal UserPrincipal currentUser, @RequestBody BankDTO dto) {
        try {
            BankDTO saved = bankService.createBank(currentUser.getId(), dto);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }
}
