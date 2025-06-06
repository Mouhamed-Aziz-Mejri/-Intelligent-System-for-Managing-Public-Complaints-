package com.example.projet_java.Controller;

import com.example.projet_java.Model.Role;
import com.example.projet_java.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasAuthority('Admin')")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from admin");
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(adminService.getAllUser());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/getUserById")
    public ResponseEntity<?> getUserById(Long id) {
        try {
            return ResponseEntity.ok(adminService.getUserById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getUserByUsername")
    public ResponseEntity<?> getUserByUsername(String username) {
        try {
            return ResponseEntity.ok(adminService.getUserByUsername(username));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/getAllUsersByRole/{roleName}")
    public ResponseEntity<?> getAllUsersByRole(@PathVariable Role roleName) {
        try {
            return ResponseEntity.ok(adminService.getAllUsersByRole(roleName));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}


