package com.tejasnirman.api.controller;

import com.tejasnirman.api.dto.ApiResponse;
import com.tejasnirman.api.dto.EnquiryDTO;
import com.tejasnirman.api.security.UserPrincipal;
import com.tejasnirman.api.service.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enquiries")
public class EnquiryController {

    private final EnquiryService enquiryService;

    @Autowired
    public EnquiryController(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_OWNER')")
    public ResponseEntity<?> createEnquiry(@AuthenticationPrincipal UserPrincipal currentUser, @RequestBody EnquiryDTO dto) {
        try {
            EnquiryDTO saved = enquiryService.createEnquiry(currentUser.getId(), dto);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_OWNER') or hasRole('ROLE_VENDOR')")
    public ResponseEntity<List<EnquiryDTO>> getMyEnquiries(@AuthenticationPrincipal UserPrincipal currentUser) {
        return ResponseEntity.ok(enquiryService.getMyEnquiries(currentUser.getId()));
    }

    @GetMapping("/property/{propertyId}")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<List<EnquiryDTO>> getPropertyEnquiries(@PathVariable Long propertyId) {
        return ResponseEntity.ok(enquiryService.getEnquiriesForProperty(propertyId));
    }

    @GetMapping("/vendor/{vendorId}")
    @PreAuthorize("hasRole('ROLE_VENDOR') or hasRole('ROLE_USER')")
    public ResponseEntity<List<EnquiryDTO>> getVendorEnquiries(@PathVariable Long vendorId) {
        return ResponseEntity.ok(enquiryService.getEnquiriesForVendor(vendorId));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_VENDOR')")
    public ResponseEntity<?> updateEnquiryStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            EnquiryDTO updated = enquiryService.updateEnquiryStatus(id, status);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }
}
