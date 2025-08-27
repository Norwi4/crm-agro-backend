package dev.agro.crmagrobackend.repository;

import dev.agro.crmagrobackend.model.Machine;
import dev.agro.crmagrobackend.model.enums.MachineStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class MachineRepository {
    
    private final JdbcTemplate jdbcTemplate;
    
    private final RowMapper<Machine> machineRowMapper = (rs, rowNum) -> {
        Machine machine = new Machine();
        machine.setId(rs.getLong("id"));
        machine.setName(rs.getString("name"));
        machine.setType(rs.getString("type"));
        machine.setPlate(rs.getString("plate"));
        machine.setVin(rs.getString("vin"));
        machine.setModel(rs.getString("model"));
        machine.setManufacturer(rs.getString("manufacturer"));
        machine.setNormFuel(rs.getBigDecimal("norm_fuel"));
        machine.setHourlyCost(rs.getBigDecimal("hourly_cost"));
        machine.setTelematicsId(rs.getString("telematics_id"));
        machine.setStatus(MachineStatus.valueOf(rs.getString("status")));
        machine.setLastMaintenanceDate(rs.getObject("last_maintenance_date", LocalDate.class));
        machine.setNextMaintenanceDate(rs.getObject("next_maintenance_date", LocalDate.class));
        machine.setTotalHours(rs.getInt("total_hours"));
        machine.setTotalFuel(rs.getBigDecimal("total_fuel"));
        machine.setDescription(rs.getString("description"));
        machine.setActive(rs.getBoolean("is_active"));
        machine.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
        machine.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
        return machine;
    };
    
    public MachineRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<Machine> findAll() {
        String sql = "SELECT * FROM machines ORDER BY id";
        return jdbcTemplate.query(sql, machineRowMapper);
    }
    
    public Optional<Machine> findById(Long id) {
        String sql = "SELECT * FROM machines WHERE id = ?";
        List<Machine> machines = jdbcTemplate.query(sql, machineRowMapper, id);
        return machines.isEmpty() ? Optional.empty() : Optional.of(machines.get(0));
    }
    
    public List<Machine> findByIsActive(boolean isActive) {
        String sql = "SELECT * FROM machines WHERE is_active = ? ORDER BY id";
        return jdbcTemplate.query(sql, machineRowMapper, isActive);
    }
    
    public List<Machine> findByType(String type) {
        String sql = "SELECT * FROM machines WHERE type = ? ORDER BY id";
        return jdbcTemplate.query(sql, machineRowMapper, type);
    }
    
    public List<Machine> findByStatus(MachineStatus status) {
        String sql = "SELECT * FROM machines WHERE status = ? ORDER BY id";
        return jdbcTemplate.query(sql, machineRowMapper, status.name());
    }
    
    public List<Machine> findByNameContaining(String name) {
        String sql = "SELECT * FROM machines WHERE LOWER(name) LIKE LOWER(?) ORDER BY id";
        String searchPattern = "%" + name + "%";
        return jdbcTemplate.query(sql, machineRowMapper, searchPattern);
    }
    
    public List<Machine> findByPlate(String plate) {
        String sql = "SELECT * FROM machines WHERE plate = ? ORDER BY id";
        return jdbcTemplate.query(sql, machineRowMapper, plate);
    }
    
    public Machine save(Machine machine) {
        if (machine.getId() == null) {
            return insert(machine);
        } else {
            return update(machine);
        }
    }
    
    private Machine insert(Machine machine) {
        String sql = "INSERT INTO machines (name, type, plate, vin, model, manufacturer, norm_fuel, hourly_cost, " +
                    "telematics_id, status, last_maintenance_date, next_maintenance_date, total_hours, total_fuel, " +
                    "description, is_active, created_at, updated_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, machine.getName());
            ps.setString(2, machine.getType());
            ps.setString(3, machine.getPlate());
            ps.setString(4, machine.getVin());
            ps.setString(5, machine.getModel());
            ps.setString(6, machine.getManufacturer());
            ps.setObject(7, machine.getNormFuel());
            ps.setObject(8, machine.getHourlyCost());
            ps.setString(9, machine.getTelematicsId());
            ps.setString(10, machine.getStatus().name());
            ps.setObject(11, machine.getLastMaintenanceDate());
            ps.setObject(12, machine.getNextMaintenanceDate());
            ps.setInt(13, machine.getTotalHours());
            ps.setObject(14, machine.getTotalFuel());
            ps.setString(15, machine.getDescription());
            ps.setBoolean(16, machine.isActive());
            ps.setObject(17, machine.getCreatedAt());
            ps.setObject(18, machine.getUpdatedAt());
            return ps;
        }, keyHolder);
        
        machine.setId(keyHolder.getKey().longValue());
        return machine;
    }
    
    private Machine update(Machine machine) {
        String sql = "UPDATE machines SET name = ?, type = ?, plate = ?, vin = ?, model = ?, manufacturer = ?, " +
                    "norm_fuel = ?, hourly_cost = ?, telematics_id = ?, status = ?, last_maintenance_date = ?, " +
                    "next_maintenance_date = ?, total_hours = ?, total_fuel = ?, description = ?, is_active = ?, " +
                    "updated_at = ? WHERE id = ?";
        
        jdbcTemplate.update(sql,
                machine.getName(),
                machine.getType(),
                machine.getPlate(),
                machine.getVin(),
                machine.getModel(),
                machine.getManufacturer(),
                machine.getNormFuel(),
                machine.getHourlyCost(),
                machine.getTelematicsId(),
                machine.getStatus().name(),
                machine.getLastMaintenanceDate(),
                machine.getNextMaintenanceDate(),
                machine.getTotalHours(),
                machine.getTotalFuel(),
                machine.getDescription(),
                machine.isActive(),
                LocalDateTime.now(),
                machine.getId());
        
        return machine;
    }
    
    public void deleteById(Long id) {
        String sql = "DELETE FROM machines WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    
    public void softDeleteById(Long id) {
        String sql = "UPDATE machines SET is_active = false, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, LocalDateTime.now(), id);
    }
    
    public void updateTotalHours(Long machineId, Integer hours) {
        String sql = "UPDATE machines SET total_hours = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, hours, LocalDateTime.now(), machineId);
    }
    
    public void updateTotalFuel(Long machineId, BigDecimal fuel) {
        String sql = "UPDATE machines SET total_fuel = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, fuel, LocalDateTime.now(), machineId);
    }
}
