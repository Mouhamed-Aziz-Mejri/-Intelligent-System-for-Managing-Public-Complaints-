package com.example.projet_java.Service;

import com.example.projet_java.Model.Role;
import com.example.projet_java.Model.User;

import java.util.List;

public interface AdminService {

    List<User> getAllUser();


    User getUserById(Long id);

    User getUserByUsername(String username) ;

    List<User>getAllUsersByRole(Role roleName) ;

}
