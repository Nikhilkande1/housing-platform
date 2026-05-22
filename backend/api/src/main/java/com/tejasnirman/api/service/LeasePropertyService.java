package com.tejasnirman.api.service;

import com.tejasnirman.api.dto.LeasePropertyDTO;
import com.tejasnirman.api.model.*;
import com.tejasnirman.api.repository.LeasePropertyRepository;
import com.tejasnirman.api.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeasePropertyService {

    private final LeasePropertyRepository leasePropertyRepository;
    private final PropertyRepository propertyRepository;

    @Autowired
    public LeasePropertyService(LeasePropertyRepository leasePropertyRepository,
                                PropertyRepository propertyRepository) {
        this.leasePropertyRepository = leasePropertyRepository;
        this.propertyRepository = propertyRepository;
    }

    public List<LeasePropertyDTO> getAvailableLeaseProperties() {
        return leasePropertyRepository.findByIsActiveTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<LeasePropertyDTO> getMyLeaseProperties(Long ownerId) {
        return leasePropertyRepository.findAll().stream()
                .filter(lp -> lp.getProperty().getOwner() != null && lp.getProperty().getOwner().getId().equals(ownerId))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public LeasePropertyDTO createLeaseProperty(Long userId, LeasePropertyDTO dto) {
        Property property = propertyRepository.findById(dto.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + dto.getPropertyId()));

        if (!property.getOwner().getId().equals(userId)) {
            throw new RuntimeException("You do not own this property to lease it!");
        }

        LeaseProperty lp = new LeaseProperty();
        lp.setProperty(property);
        lp.setLeaseType(LeaseType.valueOf(dto.getLeaseType().toUpperCase()));
        lp.setMonthlyIncome(dto.getMonthlyIncome());
        lp.setOccupancyRate(dto.getOccupancyRate() != null ? dto.getOccupancyRate() : 0.0);
        lp.setIsActive(true);

        LeaseProperty saved = leasePropertyRepository.save(lp);
        return convertToDTO(saved);
    }

    private LeasePropertyDTO convertToDTO(LeaseProperty lp) {
        LeasePropertyDTO dto = new LeasePropertyDTO();
        dto.setId(lp.getId());
        dto.setPropertyId(lp.getProperty().getId());
        dto.setLeaseType(lp.getLeaseType().name());
        dto.setMonthlyIncome(lp.getMonthlyIncome());
        dto.setOccupancyRate(lp.getOccupancyRate());
        dto.setIsActive(lp.getIsActive());

        if (lp.getProperty() != null) {
            dto.setPropertyTitle(lp.getProperty().getTitle());
            dto.setLocation(lp.getProperty().getLocation());
            dto.setEstimatedRoi(lp.getProperty().getEstimatedRoi());
            dto.setImageUrl(lp.getProperty().getImageUrl());
            if (lp.getProperty().getOwner() != null) {
                dto.setOwnerName(lp.getProperty().getOwner().getName());
            }
        }

        return dto;
    }
}
