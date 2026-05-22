package com.tejasnirman.api.controller;

import com.tejasnirman.api.dto.ApiResponse;
import com.tejasnirman.api.dto.LeasePropertyDTO;
import com.tejasnirman.api.security.UserPrincipal;
import com.tejasnirman.api.service.LeasePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leases")
public class LeaseController {

    private final LeasePropertyService leasePropertyService;

    @Autowired
    public LeaseController(LeasePropertyService leasePropertyService) {
        this.leasePropertyService = leasePropertyService;
    }

    @GetMapping
    public ResponseEntity<List<LeasePropertyDTO>> getAvailableLeaseProperties() {
        return ResponseEntity.ok(leasePropertyService.getAvailableLeaseProperties());
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<List<LeasePropertyDTO>> getMyLeaseProperties(@AuthenticationPrincipal UserPrincipal currentUser) {
        return ResponseEntity.ok(leasePropertyService.getMyLeaseProperties(currentUser.getId()));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<?> createLeaseProperty(@AuthenticationPrincipal UserPrincipal currentUser, @RequestBody LeasePropertyDTO dto) {
        try {
            LeasePropertyDTO saved = leasePropertyService.createLeaseProperty(currentUser.getId(), dto);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }
}
