package dev.agro.crmagrobackend.repository;

import dev.agro.crmagrobackend.model.Field;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class FieldRepository {
    
    private final JdbcTemplate jdbcTemplate;
    
    private final RowMapper<Field> fieldRowMapper = (rs, rowNum) -> {
        Field field = new Field();
        field.setId(rs.getLong("id"));
        field.setName(rs.getString("name"));
        field.setArea(rs.getBigDecimal("area"));
        field.setCrop(rs.getString("crop"));
        field.setSeason(rs.getString("season"));
        field.setSoil(rs.getString("soil"));
        field.setGeojson(rs.getString("geojson"));
        field.setOwnerId(rs.getLong("owner_id"));
        field.setDescription(rs.getString("description"));
        field.setActive(rs.getBoolean("is_active"));
        field.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
        field.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
        return field;
    };
    
    public FieldRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<Field> findAll() {
        String sql = "SELECT * FROM fields ORDER BY id";
        return jdbcTemplate.query(sql, fieldRowMapper);
    }
    
    public Optional<Field> findById(Long id) {
        String sql = "SELECT * FROM fields WHERE id = ?";
        List<Field> fields = jdbcTemplate.query(sql, fieldRowMapper, id);
        return fields.isEmpty() ? Optional.empty() : Optional.of(fields.get(0));
    }
    
    public List<Field> findByIsActive(boolean isActive) {
        String sql = "SELECT * FROM fields WHERE is_active = ? ORDER BY id";
        return jdbcTemplate.query(sql, fieldRowMapper, isActive);
    }
    
    public List<Field> findByCrop(String crop) {
        String sql = "SELECT * FROM fields WHERE crop = ? ORDER BY id";
        return jdbcTemplate.query(sql, fieldRowMapper, crop);
    }
    
    public List<Field> findBySeason(String season) {
        String sql = "SELECT * FROM fields WHERE season = ? ORDER BY id";
        return jdbcTemplate.query(sql, fieldRowMapper, season);
    }
    
    public List<Field> findByOwnerId(Long ownerId) {
        String sql = "SELECT * FROM fields WHERE owner_id = ? ORDER BY id";
        return jdbcTemplate.query(sql, fieldRowMapper, ownerId);
    }
    
    public List<Field> findByAreaRange(BigDecimal minArea, BigDecimal maxArea) {
        String sql = "SELECT * FROM fields WHERE area BETWEEN ? AND ? ORDER BY area";
        return jdbcTemplate.query(sql, fieldRowMapper, minArea, maxArea);
    }
    
    public List<Field> findByNameContaining(String name) {
        String sql = "SELECT * FROM fields WHERE LOWER(name) LIKE LOWER(?) ORDER BY id";
        String searchPattern = "%" + name + "%";
        return jdbcTemplate.query(sql, fieldRowMapper, searchPattern);
    }
    
    public BigDecimal getTotalArea() {
        String sql = "SELECT COALESCE(SUM(area), 0) FROM fields WHERE is_active = true";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class);
    }
    
    public BigDecimal getTotalAreaByCrop(String crop) {
        String sql = "SELECT COALESCE(SUM(area), 0) FROM fields WHERE crop = ? AND is_active = true";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, crop);
    }
    
    public Field save(Field field) {
        if (field.getId() == null) {
            return insert(field);
        } else {
            return update(field);
        }
    }
    
    private Field insert(Field field) {
        String sql = "INSERT INTO fields (name, area, crop, season, soil, geojson, owner_id, description, is_active, created_at, updated_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, field.getName());
            ps.setBigDecimal(2, field.getArea());
            ps.setString(3, field.getCrop());
            ps.setString(4, field.getSeason());
            ps.setString(5, field.getSoil());
            ps.setString(6, field.getGeojson());
            ps.setObject(7, field.getOwnerId());
            ps.setString(8, field.getDescription());
            ps.setBoolean(9, field.isActive());
            ps.setObject(10, field.getCreatedAt());
            ps.setObject(11, field.getUpdatedAt());
            return ps;
        }, keyHolder);
        
        field.setId(keyHolder.getKey().longValue());
        return field;
    }
    
    private Field update(Field field) {
        String sql = "UPDATE fields SET name = ?, area = ?, crop = ?, season = ?, soil = ?, geojson = ?, " +
                    "owner_id = ?, description = ?, is_active = ?, updated_at = ? WHERE id = ?";
        
        jdbcTemplate.update(sql,
                field.getName(),
                field.getArea(),
                field.getCrop(),
                field.getSeason(),
                field.getSoil(),
                field.getGeojson(),
                field.getOwnerId(),
                field.getDescription(),
                field.isActive(),
                LocalDateTime.now(),
                field.getId());
        
        return field;
    }
    
    public void deleteById(Long id) {
        String sql = "DELETE FROM fields WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    
    public void softDeleteById(Long id) {
        String sql = "UPDATE fields SET is_active = false, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, LocalDateTime.now(), id);
    }
}
