package com.tejasnirman.api.service;

import com.tejasnirman.api.model.Property;
import com.tejasnirman.api.model.PropertyType;
import com.tejasnirman.api.model.User;
import com.tejasnirman.api.repository.PropertyRepository;
import com.tejasnirman.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository, UserRepository userRepository) {
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public List<Property> getAllVerifiedProperties() {
        return propertyRepository.findByIsVerifiedTrue();
    }

    public List<Property> getMyProperties(Long ownerId) {
        return propertyRepository.findByOwnerId(ownerId);
    }

    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    public List<Property> getPropertiesByType(PropertyType type) {
        return propertyRepository.findByType(type);
    }

    public Property createProperty(Long ownerId, Property property) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + ownerId));
        property.setOwner(owner);
        if (property.getIsVerified() == null) {
            property.setIsVerified(false);
        }
        return propertyRepository.save(property);
    }

    public Property verifyProperty(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + propertyId));
        property.setIsVerified(true);
        return propertyRepository.save(property);
    }

    public Property updateProperty(Long propertyId, Property propertyDetails) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + propertyId));

        if (propertyDetails.getTitle() != null) property.setTitle(propertyDetails.getTitle());
        if (propertyDetails.getDescription() != null) property.setDescription(propertyDetails.getDescription());
        if (propertyDetails.getType() != null) property.setType(propertyDetails.getType());
        if (propertyDetails.getLocation() != null) property.setLocation(propertyDetails.getLocation());
        if (propertyDetails.getPrice() != null) property.setPrice(propertyDetails.getPrice());
        if (propertyDetails.getMonthlyRent() != null) property.setMonthlyRent(propertyDetails.getMonthlyRent());
        if (propertyDetails.getEstimatedRoi() != null) property.setEstimatedRoi(propertyDetails.getEstimatedRoi());
        if (propertyDetails.getOccupancyRate() != null) property.setOccupancyRate(propertyDetails.getOccupancyRate());
        if (propertyDetails.getIsVerified() != null) property.setIsVerified(propertyDetails.getIsVerified());
        if (propertyDetails.getImageUrl() != null) property.setImageUrl(propertyDetails.getImageUrl());

        return propertyRepository.save(property);
    }
}
