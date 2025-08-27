package dev.agro.crmagrobackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import dev.agro.crmagrobackend.model.enums.WorkEntryStatus;

public class WorkEntry {
    
    private Long id;
    private Long taskId;
    private Long fieldId;
    private Long machineId;
    private String employeeIds; // JSON array of employee IDs
    private BigDecimal area;
    private BigDecimal hours;
    private BigDecimal fuelUsed;
    private String materials; // JSON array of materials with quantities and prices
    private String photos; // JSON array of photo paths
    private String geo; // JSON with coordinates
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String notes;
    private WorkEntryStatus status;
    private boolean isOffline;
    private String syncStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public WorkEntry() {}
    
    public WorkEntry(Long taskId, Long fieldId, Long machineId) {
        this.taskId = taskId;
        this.fieldId = fieldId;
        this.machineId = machineId;
        this.status = WorkEntryStatus.DRAFT;
        this.isOffline = false;
        this.syncStatus = "SYNCED";
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
    
    public Long getTaskId() {
        return taskId;
    }
    
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
    
    public Long getFieldId() {
        return fieldId;
    }
    
    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }
    
    public Long getMachineId() {
        return machineId;
    }
    
    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }
    
    public String getEmployeeIds() {
        return employeeIds;
    }
    
    public void setEmployeeIds(String employeeIds) {
        this.employeeIds = employeeIds;
    }
    
    public BigDecimal getArea() {
        return area;
    }
    
    public void setArea(BigDecimal area) {
        this.area = area;
    }
    
    public BigDecimal getHours() {
        return hours;
    }
    
    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }
    
    public BigDecimal getFuelUsed() {
        return fuelUsed;
    }
    
    public void setFuelUsed(BigDecimal fuelUsed) {
        this.fuelUsed = fuelUsed;
    }
    
    public String getMaterials() {
        return materials;
    }
    
    public void setMaterials(String materials) {
        this.materials = materials;
    }
    
    public String getPhotos() {
        return photos;
    }
    
    public void setPhotos(String photos) {
        this.photos = photos;
    }
    
    public String getGeo() {
        return geo;
    }
    
    public void setGeo(String geo) {
        this.geo = geo;
    }
    
    public LocalDateTime getStartTime() {
        return startTime;
    }
    
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    
    public LocalDateTime getEndTime() {
        return endTime;
    }
    
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public WorkEntryStatus getStatus() {
        return status;
    }
    
    public void setStatus(WorkEntryStatus status) {
        this.status = status;
    }
    
    public boolean isOffline() {
        return isOffline;
    }
    
    public void setOffline(boolean offline) {
        isOffline = offline;
    }
    
    public String getSyncStatus() {
        return syncStatus;
    }
    
    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
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
