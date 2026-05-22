package com.tejasnirman.api.controller;

import com.tejasnirman.api.dto.ApiResponse;
import com.tejasnirman.api.dto.PaymentDTO;
import com.tejasnirman.api.security.UserPrincipal;
import com.tejasnirman.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_OWNER')")
    public ResponseEntity<?> createPayment(@AuthenticationPrincipal UserPrincipal currentUser, @RequestBody PaymentDTO dto) {
        try {
            PaymentDTO saved = paymentService.createPayment(currentUser.getId(), dto);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_OWNER')")
    public ResponseEntity<List<PaymentDTO>> getMyPayments(@AuthenticationPrincipal UserPrincipal currentUser) {
        return ResponseEntity.ok(paymentService.getMyPayments(currentUser.getId()));
    }
}
