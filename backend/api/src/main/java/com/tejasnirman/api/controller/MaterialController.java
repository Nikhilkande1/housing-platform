package com.tejasnirman.api.controller;

import com.tejasnirman.api.dto.ApiResponse;
import com.tejasnirman.api.dto.MaterialDTO;
import com.tejasnirman.api.security.UserPrincipal;
import com.tejasnirman.api.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public ResponseEntity<List<MaterialDTO>> getAllMaterials() {
        return ResponseEntity.ok(materialService.getAllMaterials());
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<MaterialDTO>> getMaterialsByVendor(@PathVariable Long vendorId) {
        return ResponseEntity.ok(materialService.getMaterialsByVendor(vendorId));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<MaterialDTO>> getMaterialsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(materialService.getMaterialsByCategory(category));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_VENDOR')")
    public ResponseEntity<?> createMaterial(@AuthenticationPrincipal UserPrincipal currentUser, @RequestBody MaterialDTO dto) {
        try {
            MaterialDTO saved = materialService.createMaterial(currentUser.getId(), dto);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }
}
