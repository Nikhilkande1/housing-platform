package com.tejasnirman.api.repository;

import com.tejasnirman.api.model.Feature;
import com.tejasnirman.api.model.FeatureCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
    List<Feature> findByCategory(FeatureCategory category);
    java.util.Optional<Feature> findByName(String name);
}
