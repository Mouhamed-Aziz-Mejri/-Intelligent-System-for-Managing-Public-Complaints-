package com.example.projet_java.Service;

import com.example.projet_java.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

    List<User>getAllUsers();

}
