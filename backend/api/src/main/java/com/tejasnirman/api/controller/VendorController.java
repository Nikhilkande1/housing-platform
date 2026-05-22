package com.tejasnirman.api.controller;

import com.tejasnirman.api.dto.ApiResponse;
import com.tejasnirman.api.dto.VendorDTO;
import com.tejasnirman.api.security.UserPrincipal;
import com.tejasnirman.api.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public ResponseEntity<List<VendorDTO>> getAllVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<VendorDTO>> getVendorsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(vendorService.getVendorsByCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVendorById(@PathVariable Long id) {
        try {
            VendorDTO vendor = vendorService.getVendorById(id);
            return ResponseEntity.ok(vendor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_VENDOR')")
    public ResponseEntity<?> getMyVendorProfile(@AuthenticationPrincipal UserPrincipal currentUser) {
        try {
            VendorDTO vendor = vendorService.getVendorByUserId(currentUser.getId());
            return ResponseEntity.ok(vendor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_VENDOR')")
    public ResponseEntity<?> createVendorProfile(@AuthenticationPrincipal UserPrincipal currentUser, @RequestBody VendorDTO dto) {
        try {
            VendorDTO saved = vendorService.createVendor(currentUser.getId(), dto);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }
}
