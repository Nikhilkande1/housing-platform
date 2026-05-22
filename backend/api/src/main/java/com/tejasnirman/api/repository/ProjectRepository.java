package com.tejasnirman.api.repository;

import com.tejasnirman.api.model.Project;
import com.tejasnirman.api.model.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByUserId(Long userId);
    List<Project> findByStatus(ProjectStatus status);
}
