package com.tejasnirman.api.repository;

import com.tejasnirman.api.model.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
    List<Enquiry> findByUserId(Long userId);
    List<Enquiry> findByPropertyId(Long propertyId);
    List<Enquiry> findByVendorId(Long vendorId);
}
