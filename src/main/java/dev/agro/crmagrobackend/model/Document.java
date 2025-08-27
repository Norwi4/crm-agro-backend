package dev.agro.crmagrobackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import dev.agro.crmagrobackend.model.enums.DocumentType;
import dev.agro.crmagrobackend.model.enums.DocumentStatus;

public class Document {
    
    private Long id;
    private String number;
    private DocumentType type;
    private String title;
    private String description;
    private String relatedEntityType; // TASK, WORK_ENTRY, WAYBILL, MAINTENANCE, etc.
    private Long relatedEntityId;
    private String content; // JSON with document content
    private String filePath; // Path to generated file
    private String fileType; // PDF, EXCEL, etc.
    private BigDecimal totalAmount;
    private String currency;
    private DocumentStatus status;
    private String createdBy; // Employee ID
    private String approvedBy; // Employee ID
    private LocalDateTime approvedAt;
    private boolean isExportedTo1C;
    private String exportStatus;
    private String exportNumber; // 1C document number
    private LocalDateTime exportedAt;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public Document() {}
    
    public Document(String number, DocumentType type, String title, String relatedEntityType, Long relatedEntityId) {
        this.number = number;
        this.type = type;
        this.title = title;
        this.relatedEntityType = relatedEntityType;
        this.relatedEntityId = relatedEntityId;
        this.status = DocumentStatus.DRAFT;
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
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public DocumentType getType() {
        return type;
    }
    
    public void setType(DocumentType type) {
        this.type = type;
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
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
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
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public DocumentStatus getStatus() {
        return status;
    }
    
    public void setStatus(DocumentStatus status) {
        this.status = status;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public String getApprovedBy() {
        return approvedBy;
    }
    
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
    
    public LocalDateTime getApprovedAt() {
        return approvedAt;
    }
    
    public void setApprovedAt(LocalDateTime approvedAt) {
        this.approvedAt = approvedAt;
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
    
    public String getExportNumber() {
        return exportNumber;
    }
    
    public void setExportNumber(String exportNumber) {
        this.exportNumber = exportNumber;
    }
    
    public LocalDateTime getExportedAt() {
        return exportedAt;
    }
    
    public void setExportedAt(LocalDateTime exportedAt) {
        this.exportedAt = exportedAt;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
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
