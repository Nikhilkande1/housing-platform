package com.tejasnirman.api.service;

import com.tejasnirman.api.dto.ConfiguratorDTO;
import com.tejasnirman.api.model.*;
import com.tejasnirman.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final FeatureRepository featureRepository;
    private final ProjectFeatureRepository projectFeatureRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          UserRepository userRepository,
                          FeatureRepository featureRepository,
                          ProjectFeatureRepository projectFeatureRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.featureRepository = featureRepository;
        this.projectFeatureRepository = projectFeatureRepository;
    }

    public List<Project> getMyProjects(Long userId) {
        return projectRepository.findByUserId(userId);
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project createProject(Long userId, Project project) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
        project.setUser(user);
        return projectRepository.save(project);
    }

    public Project updateProject(Long projectId, Project projectDetails) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));

        if (projectDetails.getName() != null) project.setName(projectDetails.getName());
        if (projectDetails.getStatus() != null) project.setStatus(projectDetails.getStatus());
        if (projectDetails.getCompletionPercentage() != null) project.setCompletionPercentage(projectDetails.getCompletionPercentage());
        if (projectDetails.getBudget() != null) project.setBudget(projectDetails.getBudget());
        if (projectDetails.getPlotSize() != null) project.setPlotSize(projectDetails.getPlotSize());
        if (projectDetails.getArchitecturalStyle() != null) project.setArchitecturalStyle(projectDetails.getArchitecturalStyle());

        return projectRepository.save(project);
    }

    @Transactional
    public Project saveFromConfigurator(Long userId, ConfiguratorDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

        Project project = new Project();
        project.setUser(user);
        project.setName("Home Build Project (" + dto.getArchitecturalStyle() + ")");
        project.setPlotSize(dto.getPlotSize());
        project.setBudget(dto.getBudget());
        project.setArchitecturalStyle(dto.getArchitecturalStyle());
        project.setStatus(ProjectStatus.PLANNING);
        project.setCompletionPercentage(0.0);

        Project savedProject = projectRepository.save(project);

        // Gather all selected features across different categories
        List<String> featureNames = new ArrayList<>();
        if (dto.getFlooring() != null) featureNames.addAll(dto.getFlooring());
        if (dto.getFabrication() != null) featureNames.addAll(dto.getFabrication());
        if (dto.getDoors() != null) featureNames.addAll(dto.getDoors());
        if (dto.getKitchen() != null) featureNames.addAll(dto.getKitchen());
        if (dto.getBathroom() != null) featureNames.addAll(dto.getBathroom());
        if (dto.getLuxury() != null) featureNames.addAll(dto.getLuxury());
        if (dto.getSmart() != null) featureNames.addAll(dto.getSmart());
        if (dto.getUtility() != null) featureNames.addAll(dto.getUtility());
        if (dto.getInfra() != null) featureNames.addAll(dto.getInfra());

        for (String key : featureNames) {
            String resolvedName = key;
            if (key != null) {
                switch (key.toLowerCase()) {
                    case "marble": resolvedName = "Italian Marble"; break;
                    case "tiles": resolvedName = "Vitrified Tiles"; break;
                    case "wooden": resolvedName = "Wooden Laminate"; break;
                    case "modular": resolvedName = "Gourmet Modular Kitchen"; break;
                    case "premium_fittings": resolvedName = "Luxury Bath Suite"; break;
                    case "standard": resolvedName = "Standard Bath Suite"; break;
                    case "pool": resolvedName = "Swimming Pool"; break;
                    case "theater": resolvedName = "Home Theatre"; break;
                    case "gym": resolvedName = "Private Gym"; break;
                    case "smart_light": resolvedName = "Smart Automation Hub"; break;
                    case "smart_lock": resolvedName = "Biometric Security Lock"; break;
                    case "carved_teak": resolvedName = "Teak Wood Main Door"; break;
                    case "flush_door": resolvedName = "Flush Bedroom Doors"; break;
                    case "borewell": resolvedName = "Borewell System"; break;
                    case "solar": resolvedName = "Solar Power Plant 5kW"; break;
                    case "rainwater": resolvedName = "Rainwater Harvesting"; break;
                    case "glass_facade": resolvedName = "Glass Facade"; break;
                    case "stone_clad": resolvedName = "Cladding Panels"; break;
                    case "earthquake": resolvedName = "Earthquake Resistant Frame"; break;
                    case "thermal": resolvedName = "Thermal Insulation"; break;
                }
            }
            Optional<Feature> featureOpt = featureRepository.findByName(resolvedName);
            if (featureOpt.isPresent()) {
                Feature feature = featureOpt.get();
                ProjectFeature pf = new ProjectFeature();
                pf.setProject(savedProject);
                pf.setFeature(feature);
                pf.setQuantity(1);
                pf.setCustomPrice(feature.getBasePrice());
                projectFeatureRepository.save(pf);
            }
        }

        return savedProject;
    }
}
