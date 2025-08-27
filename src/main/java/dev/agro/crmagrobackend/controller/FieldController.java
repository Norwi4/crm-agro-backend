package dev.agro.crmagrobackend.controller;

import dev.agro.crmagrobackend.model.Field;
import dev.agro.crmagrobackend.repository.FieldRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/fields")
@Tag(name = "Fields", description = "Field management endpoints")
public class FieldController {
    
    @Autowired
    private FieldRepository fieldRepository;
    
    @GetMapping
    @Operation(summary = "Get all fields", description = "Retrieve all fields")
    @PreAuthorize("hasAnyRole('ADMIN', 'AGROLOGIST', 'MANAGER')")
    public ResponseEntity<List<Field>> getAllFields(
            @RequestParam(required = false) String crop,
            @RequestParam(required = false) String season,
            @RequestParam(required = false) Boolean isActive) {
        
        List<Field> fields;
        
        if (crop != null && season != null) {
            // Для простоты возвращаем все поля и фильтруем на уровне приложения
            fields = fieldRepository.findAll();
            fields = fields.stream()
                    .filter(field -> crop.equals(field.getCrop()) && season.equals(field.getSeason()))
                    .toList();
        } else if (crop != null) {
            fields = fieldRepository.findByCrop(crop);
        } else if (season != null) {
            fields = fieldRepository.findBySeason(season);
        } else if (isActive != null) {
            fields = fieldRepository.findByIsActive(isActive);
        } else {
            fields = fieldRepository.findAll();
        }
        
        return ResponseEntity.ok(fields);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get field by ID", description = "Retrieve a specific field by its ID")
    @PreAuthorize("hasAnyRole('ADMIN', 'AGROLOGIST', 'MANAGER')")
    public ResponseEntity<Field> getFieldById(@PathVariable Long id) {
        Optional<Field> field = fieldRepository.findById(id);
        return field.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @Operation(summary = "Create new field", description = "Create a new field")
    @PreAuthorize("hasAnyRole('ADMIN', 'AGROLOGIST')")
    public ResponseEntity<Field> createField(@Valid @RequestBody Field field) {
        Field savedField = fieldRepository.save(field);
        return ResponseEntity.ok(savedField);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update field", description = "Update an existing field")
    @PreAuthorize("hasAnyRole('ADMIN', 'AGROLOGIST')")
    public ResponseEntity<Field> updateField(@PathVariable Long id, @Valid @RequestBody Field field) {
        Optional<Field> existingField = fieldRepository.findById(id);
        if (existingField.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        field.setId(id);
        Field updatedField = fieldRepository.save(field);
        return ResponseEntity.ok(updatedField);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete field", description = "Delete a field (soft delete)")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        Optional<Field> fieldOpt = fieldRepository.findById(id);
        if (fieldOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        fieldRepository.softDeleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search fields", description = "Search fields by name")
    @PreAuthorize("hasAnyRole('ADMIN', 'AGROLOGIST', 'MANAGER')")
    public ResponseEntity<List<Field>> searchFields(@RequestParam String name) {
        List<Field> fields = fieldRepository.findByNameContaining(name);
        return ResponseEntity.ok(fields);
    }
    
    @GetMapping("/area-range")
    @Operation(summary = "Get fields by area range", description = "Get fields within specified area range")
    @PreAuthorize("hasAnyRole('ADMIN', 'AGROLOGIST', 'MANAGER')")
    public ResponseEntity<List<Field>> getFieldsByAreaRange(
            @RequestParam BigDecimal minArea,
            @RequestParam BigDecimal maxArea) {
        List<Field> fields = fieldRepository.findByAreaRange(minArea, maxArea);
        return ResponseEntity.ok(fields);
    }
    
    @GetMapping("/statistics")
    @Operation(summary = "Get field statistics", description = "Get statistics about fields")
    @PreAuthorize("hasAnyRole('ADMIN', 'AGROLOGIST', 'MANAGER')")
    public ResponseEntity<Map<String, Object>> getFieldStatistics() {
        List<Field> activeFields = fieldRepository.findByIsActive(true);
        long totalFields = activeFields.size();
        
        long totalCrops = activeFields.stream()
                .map(Field::getCrop)
                .distinct()
                .count();
        
        BigDecimal totalArea = fieldRepository.getTotalArea();
        
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("totalFields", totalFields);
        statistics.put("totalCrops", totalCrops);
        statistics.put("totalArea", totalArea);
        
        return ResponseEntity.ok(statistics);
    }
}
