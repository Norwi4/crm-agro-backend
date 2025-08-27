package dev.agro.crmagrobackend.repository;

import dev.agro.crmagrobackend.model.User;
import dev.agro.crmagrobackend.model.enums.UserRole;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    
    private final JdbcTemplate jdbcTemplate;
    
    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setPasswordHash(rs.getString("password_hash"));
        user.setRole(UserRole.valueOf(rs.getString("role")));
        user.setEmployeeId(rs.getLong("employee_id"));
        user.setActive(rs.getBoolean("is_active"));
        user.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
        user.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
        return user;
    };
    
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<User> findAll() {
        String sql = "SELECT * FROM users ORDER BY id";
        return jdbcTemplate.query(sql, userRowMapper);
    }
    
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper, id);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }
    
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper, username);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }
    
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper, email);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }
    
    public Optional<User> findByUsernameOrEmail(String username, String email) {
        String sql = "SELECT * FROM users WHERE username = ? OR email = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper, username, email);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }
    
    public List<User> findByRole(UserRole role) {
        String sql = "SELECT * FROM users WHERE role = ? ORDER BY id";
        return jdbcTemplate.query(sql, userRowMapper, role.name());
    }
    
    public List<User> findByIsActive(boolean isActive) {
        String sql = "SELECT * FROM users WHERE is_active = ? ORDER BY id";
        return jdbcTemplate.query(sql, userRowMapper, isActive);
    }
    
    public List<User> findActiveByRole(UserRole role) {
        String sql = "SELECT * FROM users WHERE role = ? AND is_active = true ORDER BY id";
        return jdbcTemplate.query(sql, userRowMapper, role.name());
    }
    
    public Optional<User> findByEmployeeId(Long employeeId) {
        String sql = "SELECT * FROM users WHERE employee_id = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper, employeeId);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }
    
    public boolean existsByUsername(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }
    
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }
    
    public long countActiveByRole(UserRole role) {
        String sql = "SELECT COUNT(*) FROM users WHERE role = ? AND is_active = true";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, role.name());
        return count != null ? count : 0;
    }
    
    public User save(User user) {
        if (user.getId() == null) {
            return insert(user);
        } else {
            return update(user);
        }
    }
    
    private User insert(User user) {
        String sql = "INSERT INTO users (username, email, phone, password_hash, role, employee_id, is_active, created_at, updated_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getPasswordHash());
            ps.setString(5, user.getRole().name());
            ps.setObject(6, user.getEmployeeId());
            ps.setBoolean(7, user.isActive());
            ps.setObject(8, user.getCreatedAt());
            ps.setObject(9, user.getUpdatedAt());
            return ps;
        }, keyHolder);
        
        user.setId(keyHolder.getKey().longValue());
        return user;
    }
    
    private User update(User user) {
        String sql = "UPDATE users SET username = ?, email = ?, phone = ?, password_hash = ?, role = ?, " +
                    "employee_id = ?, is_active = ?, updated_at = ? WHERE id = ?";
        
        jdbcTemplate.update(sql,
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getPasswordHash(),
                user.getRole().name(),
                user.getEmployeeId(),
                user.isActive(),
                LocalDateTime.now(),
                user.getId());
        
        return user;
    }
    
    public void deleteById(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    
    public void softDeleteById(Long id) {
        String sql = "UPDATE users SET is_active = false, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, LocalDateTime.now(), id);
    }
}
