package dev.agro.crmagrobackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import dev.agro.crmagrobackend.model.enums.MaintenanceType;
import dev.agro.crmagrobackend.model.enums.MaintenanceStatus;

public class Maintenance {
    
    private Long id;
    private Long machineId;
    private String title;
    private String description;
    private MaintenanceType type;
    private MaintenanceStatus status;
    private String assignedEmployeeId; // Employee ID
    private LocalDateTime plannedDate;
    private LocalDateTime actualDate;
    private BigDecimal plannedHours;
    private BigDecimal actualHours;
    private BigDecimal plannedCost;
    private BigDecimal actualCost;
    private String materials; // JSON array of materials with quantities and prices
    private String spareParts; // JSON array of spare parts with quantities and prices
    private BigDecimal downtimeHours;
    private String downtimeReason;
    private String notes;
    private String photos; // JSON array of photo paths
    private boolean isExportedTo1C;
    private String exportStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public Maintenance() {}
    
    public Maintenance(Long machineId, String title, MaintenanceType type) {
        this.machineId = machineId;
        this.title = title;
        this.type = type;
        this.status = MaintenanceStatus.PLANNED;
        this.isExportedTo1C = false;
        this.exportStatus = "NOT_EXPORTED";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getMachineId() {
        return machineId;
    }
    
    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public MaintenanceType getType() {
        return type;
    }
    
    public void setType(MaintenanceType type) {
        this.type = type;
    }
    
    public MaintenanceStatus getStatus() {
        return status;
    }
    
    public void setStatus(MaintenanceStatus status) {
        this.status = status;
    }
    
    public String getAssignedEmployeeId() {
        return assignedEmployeeId;
    }
    
    public void setAssignedEmployeeId(String assignedEmployeeId) {
        this.assignedEmployeeId = assignedEmployeeId;
    }
    
    public LocalDateTime getPlannedDate() {
        return plannedDate;
    }
    
    public void setPlannedDate(LocalDateTime plannedDate) {
        this.plannedDate = plannedDate;
    }
    
    public LocalDateTime getActualDate() {
        return actualDate;
    }
    
    public void setActualDate(LocalDateTime actualDate) {
        this.actualDate = actualDate;
    }
    
    public BigDecimal getPlannedHours() {
        return plannedHours;
    }
    
    public void setPlannedHours(BigDecimal plannedHours) {
        this.plannedHours = plannedHours;
    }
    
    public BigDecimal getActualHours() {
        return actualHours;
    }
    
    public void setActualHours(BigDecimal actualHours) {
        this.actualHours = actualHours;
    }
    
    public BigDecimal getPlannedCost() {
        return plannedCost;
    }
    
    public void setPlannedCost(BigDecimal plannedCost) {
        this.plannedCost = plannedCost;
    }
    
    public BigDecimal getActualCost() {
        return actualCost;
    }
    
    public void setActualCost(BigDecimal actualCost) {
        this.actualCost = actualCost;
    }
    
    public String getMaterials() {
        return materials;
    }
    
    public void setMaterials(String materials) {
        this.materials = materials;
    }
    
    public String getSpareParts() {
        return spareParts;
    }
    
    public void setSpareParts(String spareParts) {
        this.spareParts = spareParts;
    }
    
    public BigDecimal getDowntimeHours() {
        return downtimeHours;
    }
    
    public void setDowntimeHours(BigDecimal downtimeHours) {
        this.downtimeHours = downtimeHours;
    }
    
    public String getDowntimeReason() {
        return downtimeReason;
    }
    
    public void setDowntimeReason(String downtimeReason) {
        this.downtimeReason = downtimeReason;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public String getPhotos() {
        return photos;
    }
    
    public void setPhotos(String photos) {
        this.photos = photos;
    }
    
    public boolean isExportedTo1C() {
        return isExportedTo1C;
    }
    
    public void setExportedTo1C(boolean exportedTo1C) {
        isExportedTo1C = exportedTo1C;
    }
    
    public String getExportStatus() {
        return exportStatus;
    }
    
    public void setExportStatus(String exportStatus) {
        this.exportStatus = exportStatus;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
