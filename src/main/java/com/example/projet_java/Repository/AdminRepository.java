package com.example.projet_java.Repository;

import com.example.projet_java.Model.Role;
import com.example.projet_java.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository   extends JpaRepository<User, Long> {


    List<User> findByRole(Role roleName);
}
