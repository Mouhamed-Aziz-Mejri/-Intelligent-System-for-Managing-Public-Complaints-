package com.example.projet_java;

import com.example.projet_java.Model.Role;
import com.example.projet_java.Model.User;
import com.example.projet_java.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjetJavaApplication implements  CommandLineRunner  {
    @Autowired
    private  UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(ProjetJavaApplication.class , args);
    }
    public  void run(String... args) throws Exception {
        User admin =userRepository.findByRole(Role.Admin);
        if (admin == null) {
            User user = new User();
            user.setFirstName("admin");
            user.setLastName("12");
            user.setUsername("Admin123");
            user.setPassword(new BCryptPasswordEncoder().encode("admin123"));
            user.setRole(Role.Admin);
            userRepository.save(user);
        }
    }
}
