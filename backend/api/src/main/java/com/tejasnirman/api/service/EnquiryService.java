package com.tejasnirman.api.service;

import com.tejasnirman.api.dto.EnquiryDTO;
import com.tejasnirman.api.model.*;
import com.tejasnirman.api.repository.EnquiryRepository;
import com.tejasnirman.api.repository.PropertyRepository;
import com.tejasnirman.api.repository.UserRepository;
import com.tejasnirman.api.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnquiryService {

    private final EnquiryRepository enquiryRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final VendorRepository vendorRepository;

    @Autowired
    public EnquiryService(EnquiryRepository enquiryRepository,
                          UserRepository userRepository,
                          PropertyRepository propertyRepository,
                          VendorRepository vendorRepository) {
        this.enquiryRepository = enquiryRepository;
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
        this.vendorRepository = vendorRepository;
    }

    public EnquiryDTO createEnquiry(Long userId, EnquiryDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

        Enquiry enquiry = new Enquiry();
        enquiry.setUser(user);
        enquiry.setMessage(dto.getMessage());
        enquiry.setStatus(EnquiryStatus.NEW);

        if (dto.getPropertyId() != null) {
            Property property = propertyRepository.findById(dto.getPropertyId())
                    .orElseThrow(() -> new RuntimeException("Property not found with id: " + dto.getPropertyId()));
            enquiry.setProperty(property);
        }

        if (dto.getVendorId() != null) {
            Vendor vendor = vendorRepository.findById(dto.getVendorId())
                    .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + dto.getVendorId()));
            enquiry.setVendor(vendor);
        }

        Enquiry saved = enquiryRepository.save(enquiry);
        return convertToDTO(saved);
    }

    public List<EnquiryDTO> getMyEnquiries(Long userId) {
        return enquiryRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<EnquiryDTO> getEnquiriesForProperty(Long propertyId) {
        return enquiryRepository.findByPropertyId(propertyId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<EnquiryDTO> getEnquiriesForVendor(Long vendorId) {
        return enquiryRepository.findByVendorId(vendorId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EnquiryDTO updateEnquiryStatus(Long enquiryId, String status) {
        Enquiry enquiry = enquiryRepository.findById(enquiryId)
                .orElseThrow(() -> new RuntimeException("Enquiry not found with id: " + enquiryId));

        enquiry.setStatus(EnquiryStatus.valueOf(status.toUpperCase()));
        Enquiry updated = enquiryRepository.save(enquiry);
        return convertToDTO(updated);
    }

    private EnquiryDTO convertToDTO(Enquiry enquiry) {
        EnquiryDTO dto = new EnquiryDTO();
        dto.setId(enquiry.getId());
        dto.setUserName(enquiry.getUser().getName());
        dto.setMessage(enquiry.getMessage());
        dto.setStatus(enquiry.getStatus().name());
        dto.setCreatedAt(enquiry.getCreatedAt());

        if (enquiry.getProperty() != null) {
            dto.setPropertyId(enquiry.getProperty().getId());
            dto.setPropertyTitle(enquiry.getProperty().getTitle());
        }

        if (enquiry.getVendor() != null) {
            dto.setVendorId(enquiry.getVendor().getId());
            dto.setVendorName(enquiry.getVendor().getCompanyName());
        }

        return dto;
    }
}
