package dev.agro.crmagrobackend.model;

import java.time.LocalDateTime;

public class IntegrationLog {
    
    private Long id;
    private String integrationType; // 1C, GPS, FUEL_CARD, WEATHER, etc.
    private String operation; // IMPORT, EXPORT, SYNC, etc.
    private String entityType;
    private Long entityId;
    private String requestData; // JSON with request data
    private String responseData; // JSON with response data
    private String status; // SUCCESS, ERROR, PENDING, etc.
    private String errorMessage;
    private String errorCode;
    private Long processingTime; // in milliseconds
    private String sourceSystem;
    private String targetSystem;
    private String correlationId;
    private LocalDateTime processedAt;
    private LocalDateTime createdAt;
    
    // Constructors
    public IntegrationLog() {}
    
    public IntegrationLog(String integrationType, String operation, String entityType, Long entityId) {
        this.integrationType = integrationType;
        this.operation = operation;
        this.entityType = entityType;
        this.entityId = entityId;
        this.status = "PENDING";
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getIntegrationType() {
        return integrationType;
    }
    
    public void setIntegrationType(String integrationType) {
        this.integrationType = integrationType;
    }
    
    public String getOperation() {
        return operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    public String getEntityType() {
        return entityType;
    }
    
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
    
    public Long getEntityId() {
        return entityId;
    }
    
    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }
    
    public String getRequestData() {
        return requestData;
    }
    
    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }
    
    public String getResponseData() {
        return responseData;
    }
    
    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    
    public Long getProcessingTime() {
        return processingTime;
    }
    
    public void setProcessingTime(Long processingTime) {
        this.processingTime = processingTime;
    }
    
    public String getSourceSystem() {
        return sourceSystem;
    }
    
    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }
    
    public String getTargetSystem() {
        return targetSystem;
    }
    
    public void setTargetSystem(String targetSystem) {
        this.targetSystem = targetSystem;
    }
    
    public String getCorrelationId() {
        return correlationId;
    }
    
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
    
    public LocalDateTime getProcessedAt() {
        return processedAt;
    }
    
    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
