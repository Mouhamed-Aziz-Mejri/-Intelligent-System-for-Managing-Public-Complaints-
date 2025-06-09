package com.example.projet_java.Controller;

import com.example.projet_java.Service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@PreAuthorize("hasAuthority('Citoyen')or hasAuthority('Repair_Team')or hasAuthority('Municipality_Staff')")
@RestController
@RequestMapping("/api/v1/user")

public class UserController {

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from User");
    }



}
