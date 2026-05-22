package com.tejasnirman.api.service;

import com.tejasnirman.api.dto.MaterialDTO;
import com.tejasnirman.api.model.Material;
import com.tejasnirman.api.model.Vendor;
import com.tejasnirman.api.repository.MaterialRepository;
import com.tejasnirman.api.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final VendorRepository vendorRepository;

    @Autowired
    public MaterialService(MaterialRepository materialRepository, VendorRepository vendorRepository) {
        this.materialRepository = materialRepository;
        this.vendorRepository = vendorRepository;
    }

    public List<MaterialDTO> getAllMaterials() {
        return materialRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MaterialDTO> getMaterialsByVendor(Long vendorId) {
        return materialRepository.findByVendorId(vendorId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MaterialDTO> getMaterialsByCategory(String category) {
        return materialRepository.findByCategory(category).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MaterialDTO createMaterial(Long userId, MaterialDTO dto) {
        Vendor vendor = vendorRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Vendor profile not found for user: " + userId));

        Material material = new Material();
        material.setVendor(vendor);
        material.setName(dto.getName());
        material.setCategory(dto.getCategory());
        material.setPricePerUnit(dto.getPricePerUnit());
        material.setUnit(dto.getUnit());
        material.setDescription(dto.getDescription());
        material.setImageUrl(dto.getImageUrl());

        Material saved = materialRepository.save(material);
        return convertToDTO(saved);
    }

    private MaterialDTO convertToDTO(Material material) {
        MaterialDTO dto = new MaterialDTO();
        dto.setId(material.getId());
        dto.setName(material.getName());
        dto.setCategory(material.getCategory());
        dto.setDescription(material.getDescription());
        dto.setPricePerUnit(material.getPricePerUnit());
        dto.setUnit(material.getUnit());
        dto.setImageUrl(material.getImageUrl());

        if (material.getVendor() != null) {
            dto.setVendorId(material.getVendor().getId());
            dto.setVendorName(material.getVendor().getCompanyName());
        }

        return dto;
    }
}
