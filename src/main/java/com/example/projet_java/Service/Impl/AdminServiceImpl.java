package com.example.projet_java.Service.Impl;

import com.example.projet_java.Model.Role;
import com.example.projet_java.Model.User;
import com.example.projet_java.Repository.AdminRepository;
import com.example.projet_java.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<User> getAllUser() {
        if (adminRepository.findAll().isEmpty()){
            throw new RuntimeException("No users found");
        }
        return adminRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return  adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public User getUserByUsername(String username) {
        return adminRepository.findAll().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    @Override
    public List<User> getAllUsersByRole(Role roleName) {
        List<User> users = adminRepository.findByRole(roleName);


        if (users.isEmpty()) {
            throw new RuntimeException("No users found with role: " + roleName);
        }

        return users;
    }


}