package dev.agro.crmagrobackend.model;

import java.time.LocalDateTime;

public class Photo {
    
    private Long id;
    private String fileName;
    private String originalFileName;
    private String filePath;
    private String fileType;
    private Long fileSize;
    private String relatedEntityType; // TASK, WORK_ENTRY, MAINTENANCE, FIELD, etc.
    private Long relatedEntityId;
    private String photoType; // BEFORE, AFTER, GENERAL, etc.
    private String geo; // JSON with coordinates
    private LocalDateTime takenAt;
    private String takenBy; // Employee ID
    private String description;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public Photo() {}
    
    public Photo(String fileName, String originalFileName, String filePath, String relatedEntityType, Long relatedEntityId) {
        this.fileName = fileName;
        this.originalFileName = originalFileName;
        this.filePath = filePath;
        this.relatedEntityType = relatedEntityType;
        this.relatedEntityId = relatedEntityId;
        this.isActive = true;
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
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public String getOriginalFileName() {
        return originalFileName;
    }
    
    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public String getFileType() {
        return fileType;
    }
    
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
    public Long getFileSize() {
        return fileSize;
    }
    
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    
    public String getRelatedEntityType() {
        return relatedEntityType;
    }
    
    public void setRelatedEntityType(String relatedEntityType) {
        this.relatedEntityType = relatedEntityType;
    }
    
    public Long getRelatedEntityId() {
        return relatedEntityId;
    }
    
    public void setRelatedEntityId(Long relatedEntityId) {
        this.relatedEntityId = relatedEntityId;
    }
    
    public String getPhotoType() {
        return photoType;
    }
    
    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }
    
    public String getGeo() {
        return geo;
    }
    
    public void setGeo(String geo) {
        this.geo = geo;
    }
    
    public LocalDateTime getTakenAt() {
        return takenAt;
    }
    
    public void setTakenAt(LocalDateTime takenAt) {
        this.takenAt = takenAt;
    }
    
    public String getTakenBy() {
        return takenBy;
    }
    
    public void setTakenBy(String takenBy) {
        this.takenBy = takenBy;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
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
