package com.tejasnirman.api.repository;

import com.tejasnirman.api.model.LeaseProperty;
import com.tejasnirman.api.model.LeaseType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeasePropertyRepository extends JpaRepository<LeaseProperty, Long> {
    List<LeaseProperty> findByPropertyId(Long propertyId);
    List<LeaseProperty> findByLeaseType(LeaseType leaseType);
    List<LeaseProperty> findByIsActiveTrue();
}
