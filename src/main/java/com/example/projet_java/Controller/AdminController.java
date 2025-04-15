package com.example.projet_java.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@PreAuthorize("hasRole('Admin')")
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/hello")
    public ResponseEntity<String>sayHello(){
        return ResponseEntity.ok("Hello from admin");
    }

}
