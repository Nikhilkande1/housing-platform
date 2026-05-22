package com.tejasnirman.api.controller;

import com.tejasnirman.api.dto.ApiResponse;
import com.tejasnirman.api.dto.LoanApplicationDTO;
import com.tejasnirman.api.security.UserPrincipal;
import com.tejasnirman.api.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> applyForLoan(@AuthenticationPrincipal UserPrincipal currentUser, @RequestBody LoanApplicationDTO dto) {
        try {
            LoanApplicationDTO saved = loanService.applyForLoan(currentUser.getId(), dto);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_BANK')")
    public ResponseEntity<List<LoanApplicationDTO>> getMyLoans(@AuthenticationPrincipal UserPrincipal currentUser) {
        return ResponseEntity.ok(loanService.getMyLoans(currentUser.getId()));
    }

    @GetMapping("/bank/{bankId}")
    @PreAuthorize("hasRole('ROLE_BANK')")
    public ResponseEntity<List<LoanApplicationDTO>> getLoansByBank(@PathVariable Long bankId) {
        return ResponseEntity.ok(loanService.getLoansByBank(bankId));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ROLE_BANK')")
    public ResponseEntity<?> updateLoanStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            LoanApplicationDTO updated = loanService.updateLoanStatus(id, status);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }
}
