package com.tejasnirman.api.controller;

import com.tejasnirman.api.dto.ApiResponse;
import com.tejasnirman.api.dto.ConfiguratorDTO;
import com.tejasnirman.api.model.Project;
import com.tejasnirman.api.security.UserPrincipal;
import com.tejasnirman.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_OWNER')")
    public List<Project> getMyProjects(@AuthenticationPrincipal UserPrincipal currentUser) {
        return projectService.getMyProjects(currentUser.getId());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_OWNER')")
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(new ApiResponse(false, "Project not found")));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public Project createProject(@AuthenticationPrincipal UserPrincipal currentUser, @RequestBody Project project) {
        return projectService.createProject(currentUser.getId(), project);
    }

    @PostMapping("/configurator")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> saveFromConfigurator(@AuthenticationPrincipal UserPrincipal currentUser, @RequestBody ConfiguratorDTO dto) {
        try {
            Project saved = projectService.saveFromConfigurator(currentUser.getId(), dto);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_OWNER')")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        try {
            Project updated = projectService.updateProject(id, projectDetails);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }
}
