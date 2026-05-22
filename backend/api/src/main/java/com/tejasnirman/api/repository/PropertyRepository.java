package com.tejasnirman.api.repository;

import com.tejasnirman.api.model.Property;
import com.tejasnirman.api.model.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByOwnerId(Long ownerId);
    List<Property> findByType(PropertyType type);
    List<Property> findByIsVerifiedTrue();
}
