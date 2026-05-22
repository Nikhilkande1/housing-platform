package com.tejasnirman.api.service;

import com.tejasnirman.api.dto.VendorDTO;
import com.tejasnirman.api.model.User;
import com.tejasnirman.api.model.Vendor;
import com.tejasnirman.api.repository.UserRepository;
import com.tejasnirman.api.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;

    @Autowired
    public VendorService(VendorRepository vendorRepository, UserRepository userRepository) {
        this.vendorRepository = vendorRepository;
        this.userRepository = userRepository;
    }

    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<VendorDTO> getVendorsByCategory(String category) {
        return vendorRepository.findByCategory(category).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public VendorDTO getVendorById(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + id));
        return convertToDTO(vendor);
    }

    public VendorDTO getVendorByUserId(Long userId) {
        Vendor vendor = vendorRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Vendor profile not found for user: " + userId));
        return convertToDTO(vendor);
    }

    public VendorDTO createVendor(Long userId, VendorDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

        vendorRepository.findByUserId(userId).ifPresent(v -> {
            throw new RuntimeException("Vendor profile already exists for user: " + userId);
        });

        Vendor vendor = new Vendor();
        vendor.setUser(user);
        vendor.setCompanyName(dto.getCompanyName());
        vendor.setCategory(dto.getCategory());
        vendor.setDescription(dto.getDescription());
        vendor.setLocation(dto.getLocation());
        vendor.setImageUrl(dto.getImageUrl());
        vendor.setIsVerified(false);
        vendor.setRating(5.0);
        vendor.setReviewCount(1);

        Vendor saved = vendorRepository.save(vendor);
        return convertToDTO(saved);
    }

    private VendorDTO convertToDTO(Vendor vendor) {
        VendorDTO dto = new VendorDTO();
        dto.setId(vendor.getId());
        dto.setCompanyName(vendor.getCompanyName());
        dto.setCategory(vendor.getCategory());
        dto.setDescription(vendor.getDescription());
        dto.setRating(vendor.getRating());
        dto.setReviewCount(vendor.getReviewCount());
        dto.setLocation(vendor.getLocation());
        dto.setImageUrl(vendor.getImageUrl());
        dto.setIsVerified(vendor.getIsVerified());
        
        if (vendor.getUser() != null) {
            dto.setContactEmail(vendor.getUser().getEmail());
            dto.setContactPhone(vendor.getUser().getPhoneNumber());
        }
        
        return dto;
    }
}
