package com.tejasnirman.api.repository;

import com.tejasnirman.api.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByVendorId(Long vendorId);
    List<Material> findByCategory(String category);
}
