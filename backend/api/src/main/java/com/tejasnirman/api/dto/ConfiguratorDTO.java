package com.tejasnirman.api.dto;

import java.math.BigDecimal;
import java.util.List;

public class ConfiguratorDTO {
    private Integer plotSize;
    private BigDecimal budget;
    private String architecturalStyle;
    private List<String> flooring;
    private List<String> fabrication;
    private List<String> doors;
    private List<String> kitchen;
    private List<String> bathroom;
    private List<String> luxury;
    private List<String> smart;
    private List<String> utility;
    private List<String> infra;
    private BigDecimal totalEstimate;

    public ConfiguratorDTO() {}

    public ConfiguratorDTO(Integer plotSize, BigDecimal budget, String architecturalStyle, List<String> flooring, List<String> fabrication, List<String> doors, List<String> kitchen, List<String> bathroom, List<String> luxury, List<String> smart, List<String> utility, List<String> infra, BigDecimal totalEstimate) {
        this.plotSize = plotSize;
        this.budget = budget;
        this.architecturalStyle = architecturalStyle;
        this.flooring = flooring;
        this.fabrication = fabrication;
        this.doors = doors;
        this.kitchen = kitchen;
        this.bathroom = bathroom;
        this.luxury = luxury;
        this.smart = smart;
        this.utility = utility;
        this.infra = infra;
        this.totalEstimate = totalEstimate;
    }

    public Integer getPlotSize() {
        return plotSize;
    }

    public void setPlotSize(Integer plotSize) {
        this.plotSize = plotSize;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public String getArchitecturalStyle() {
        return architecturalStyle;
    }

    public void setArchitecturalStyle(String architecturalStyle) {
        this.architecturalStyle = architecturalStyle;
    }

    public List<String> getFlooring() {
        return flooring;
    }

    public void setFlooring(List<String> flooring) {
        this.flooring = flooring;
    }

    public List<String> getFabrication() {
        return fabrication;
    }

    public void setFabrication(List<String> fabrication) {
        this.fabrication = fabrication;
    }

    public List<String> getDoors() {
        return doors;
    }

    public void setDoors(List<String> doors) {
        this.doors = doors;
    }

    public List<String> getKitchen() {
        return kitchen;
    }

    public void setKitchen(List<String> kitchen) {
        this.kitchen = kitchen;
    }

    public List<String> getBathroom() {
        return bathroom;
    }

    public void setBathroom(List<String> bathroom) {
        this.bathroom = bathroom;
    }

    public List<String> getLuxury() {
        return luxury;
    }

    public void setLuxury(List<String> luxury) {
        this.luxury = luxury;
    }

    public List<String> getSmart() {
        return smart;
    }

    public void setSmart(List<String> smart) {
        this.smart = smart;
    }

    public List<String> getUtility() {
        return utility;
    }

    public void setUtility(List<String> utility) {
        this.utility = utility;
    }

    public List<String> getInfra() {
        return infra;
    }

    public void setInfra(List<String> infra) {
        this.infra = infra;
    }

    public BigDecimal getTotalEstimate() {
        return totalEstimate;
    }

    public void setTotalEstimate(BigDecimal totalEstimate) {
        this.totalEstimate = totalEstimate;
    }
}
