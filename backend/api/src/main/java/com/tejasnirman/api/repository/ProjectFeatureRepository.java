package com.tejasnirman.api.repository;

import com.tejasnirman.api.model.ProjectFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectFeatureRepository extends JpaRepository<ProjectFeature, Long> {
    List<ProjectFeature> findByProjectId(Long projectId);
}
