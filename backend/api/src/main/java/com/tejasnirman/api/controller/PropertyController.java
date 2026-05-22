package com.tejasnirman.api.controller;

import com.tejasnirman.api.dto.ApiResponse;
import com.tejasnirman.api.model.Property;
import com.tejasnirman.api.security.UserPrincipal;
import com.tejasnirman.api.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public List<Property> getAllVerifiedProperties() {
        return propertyService.getAllVerifiedProperties();
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_USER')")
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_USER')")
    public List<Property> getMyProperties(@AuthenticationPrincipal UserPrincipal currentUser) {
        return propertyService.getMyProperties(currentUser.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPropertyById(@PathVariable Long id) {
        return propertyService.getPropertyById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(new ApiResponse(false, "Property not found")));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<?> createProperty(@AuthenticationPrincipal UserPrincipal currentUser, @RequestBody Property property) {
        try {
            Property created = propertyService.createProperty(currentUser.getId(), property);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PutMapping("/{id}/verify")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_USER')")
    public ResponseEntity<?> verifyProperty(@PathVariable Long id) {
        try {
            Property verified = propertyService.verifyProperty(id);
            return ResponseEntity.ok(verified);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<?> updateProperty(@PathVariable Long id, @RequestBody Property propertyDetails) {
        try {
            Property updated = propertyService.updateProperty(id, propertyDetails);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }
}
