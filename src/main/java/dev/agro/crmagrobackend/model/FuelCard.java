package dev.agro.crmagrobackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import dev.agro.crmagrobackend.model.enums.FuelCardStatus;

public class FuelCard {
    
    private Long id;
    private String cardNumber;
    private String provider;
    private Long machineId;
    private String employeeId; // Employee ID
    private BigDecimal balance;
    private BigDecimal dailyLimit;
    private BigDecimal monthlyLimit;
    private String allowedStations; // JSON array of station IDs
    private String allowedFuelTypes; // JSON array of fuel types
    private FuelCardStatus status;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;
    private String notes;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public FuelCard() {}
    
    public FuelCard(String cardNumber, String provider, Long machineId, String employeeId) {
        this.cardNumber = cardNumber;
        this.provider = provider;
        this.machineId = machineId;
        this.employeeId = employeeId;
        this.status = FuelCardStatus.ACTIVE;
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
    
    public String getCardNumber() {
        return cardNumber;
    }
    
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public String getProvider() {
        return provider;
    }
    
    public void setProvider(String provider) {
        this.provider = provider;
    }
    
    public Long getMachineId() {
        return machineId;
    }
    
    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }
    
    public String getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    public BigDecimal getDailyLimit() {
        return dailyLimit;
    }
    
    public void setDailyLimit(BigDecimal dailyLimit) {
        this.dailyLimit = dailyLimit;
    }
    
    public BigDecimal getMonthlyLimit() {
        return monthlyLimit;
    }
    
    public void setMonthlyLimit(BigDecimal monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }
    
    public String getAllowedStations() {
        return allowedStations;
    }
    
    public void setAllowedStations(String allowedStations) {
        this.allowedStations = allowedStations;
    }
    
    public String getAllowedFuelTypes() {
        return allowedFuelTypes;
    }
    
    public void setAllowedFuelTypes(String allowedFuelTypes) {
        this.allowedFuelTypes = allowedFuelTypes;
    }
    
    public FuelCardStatus getStatus() {
        return status;
    }
    
    public void setStatus(FuelCardStatus status) {
        this.status = status;
    }
    
    public LocalDateTime getValidFrom() {
        return validFrom;
    }
    
    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }
    
    public LocalDateTime getValidTo() {
        return validTo;
    }
    
    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
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
