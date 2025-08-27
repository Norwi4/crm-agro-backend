package dev.agro.crmagrobackend.controller;

import dev.agro.crmagrobackend.dto.AuthRequest;
import dev.agro.crmagrobackend.dto.AuthResponse;
import dev.agro.crmagrobackend.dto.ChangePasswordRequest;
import dev.agro.crmagrobackend.dto.UserRegistrationRequest;
import dev.agro.crmagrobackend.model.User;
import dev.agro.crmagrobackend.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication and user management endpoints")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user and return JWT token")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        String token = authService.authenticateUser(authRequest.getUsername(), authRequest.getPassword());
        
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setType("Bearer");
        response.setMessage("Login successful");
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/register")
    @Operation(summary = "User registration", description = "Register a new user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody UserRegistrationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(request.getPassword());
        user.setRole(request.getRole());
        user.setPhone(request.getPhone());
        
        User savedUser = authService.registerUser(user, request.getPassword());
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("userId", savedUser.getId());
        response.put("username", savedUser.getUsername());
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/refresh")
    @Operation(summary = "Refresh token", description = "Refresh JWT token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestParam String refreshToken) {
        String newToken = authService.refreshToken(refreshToken);
        
        AuthResponse response = new AuthResponse();
        response.setToken(newToken);
        response.setType("Bearer");
        response.setMessage("Token refreshed successfully");
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/change-password")
    @Operation(summary = "Change password", description = "Change user password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Map<String, Object>> changePassword(
            @Valid @RequestBody ChangePasswordRequest request,
            HttpServletRequest httpRequest) {
        
        String token = httpRequest.getHeader("Authorization").substring(7);
        User currentUser = authService.getCurrentUser(token);
        
        boolean success = authService.changePassword(
            currentUser.getId(), 
            request.getOldPassword(), 
            request.getNewPassword()
        );
        
        Map<String, Object> response = new HashMap<>();
        if (success) {
            response.put("message", "Password changed successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Failed to change password. Check old password.");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/me")
    @Operation(summary = "Get current user", description = "Get information about current authenticated user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> getCurrentUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        User user = authService.getCurrentUser(token);
        return ResponseEntity.ok(user);
    }
    
    @PostMapping("/logout")
    @Operation(summary = "User logout", description = "Logout user (client should discard token)")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Map<String, Object>> logout() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Logout successful");
        return ResponseEntity.ok(response);
    }
}
