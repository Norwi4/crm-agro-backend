package dev.agro.crmagrobackend.repository;

import dev.agro.crmagrobackend.model.Employee;
import dev.agro.crmagrobackend.model.enums.UserRole;
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
public class EmployeeRepository {
    
    private final JdbcTemplate jdbcTemplate;
    
    private final RowMapper<Employee> employeeRowMapper = (rs, rowNum) -> {
        Employee employee = new Employee();
        employee.setId(rs.getLong("id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setMiddleName(rs.getString("middle_name"));
        employee.setRole(UserRole.valueOf(rs.getString("role")));
        employee.setTeam(rs.getString("team"));
        employee.setRate(rs.getBigDecimal("rate"));
        employee.setPhone(rs.getString("phone"));
        employee.setEmail(rs.getString("email"));
        employee.setHireDate(rs.getObject("hire_date", LocalDate.class));
        employee.setActive(rs.getBoolean("is_active"));
        employee.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
        employee.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
        return employee;
    };
    
    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees ORDER BY id";
        return jdbcTemplate.query(sql, employeeRowMapper);
    }
    
    public Optional<Employee> findById(Long id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        List<Employee> employees = jdbcTemplate.query(sql, employeeRowMapper, id);
        return employees.isEmpty() ? Optional.empty() : Optional.of(employees.get(0));
    }
    
    public List<Employee> findByRole(UserRole role) {
        String sql = "SELECT * FROM employees WHERE role = ? ORDER BY id";
        return jdbcTemplate.query(sql, employeeRowMapper, role.name());
    }
    
    public List<Employee> findByIsActive(boolean isActive) {
        String sql = "SELECT * FROM employees WHERE is_active = ? ORDER BY id";
        return jdbcTemplate.query(sql, employeeRowMapper, isActive);
    }
    
    public List<Employee> findByTeam(String team) {
        String sql = "SELECT * FROM employees WHERE team = ? ORDER BY id";
        return jdbcTemplate.query(sql, employeeRowMapper, team);
    }
    
    public List<Employee> findByNameContaining(String name) {
        String sql = "SELECT * FROM employees WHERE LOWER(first_name) LIKE LOWER(?) OR LOWER(last_name) LIKE LOWER(?) ORDER BY id";
        String searchPattern = "%" + name + "%";
        return jdbcTemplate.query(sql, employeeRowMapper, searchPattern, searchPattern);
    }
    
    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            return insert(employee);
        } else {
            return update(employee);
        }
    }
    
    private Employee insert(Employee employee) {
        String sql = "INSERT INTO employees (first_name, last_name, middle_name, role, team, rate, phone, email, hire_date, is_active, created_at, updated_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getMiddleName());
            ps.setString(4, employee.getRole().name());
            ps.setString(5, employee.getTeam());
            ps.setObject(6, employee.getRate());
            ps.setString(7, employee.getPhone());
            ps.setString(8, employee.getEmail());
            ps.setObject(9, employee.getHireDate());
            ps.setBoolean(10, employee.isActive());
            ps.setObject(11, employee.getCreatedAt());
            ps.setObject(12, employee.getUpdatedAt());
            return ps;
        }, keyHolder);
        
        employee.setId(keyHolder.getKey().longValue());
        return employee;
    }
    
    private Employee update(Employee employee) {
        String sql = "UPDATE employees SET first_name = ?, last_name = ?, middle_name = ?, role = ?, team = ?, " +
                    "rate = ?, phone = ?, email = ?, hire_date = ?, is_active = ?, updated_at = ? WHERE id = ?";
        
        jdbcTemplate.update(sql,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getMiddleName(),
                employee.getRole().name(),
                employee.getTeam(),
                employee.getRate(),
                employee.getPhone(),
                employee.getEmail(),
                employee.getHireDate(),
                employee.isActive(),
                LocalDateTime.now(),
                employee.getId());
        
        return employee;
    }
    
    public void deleteById(Long id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    
    public void softDeleteById(Long id) {
        String sql = "UPDATE employees SET is_active = false, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, LocalDateTime.now(), id);
    }
}
