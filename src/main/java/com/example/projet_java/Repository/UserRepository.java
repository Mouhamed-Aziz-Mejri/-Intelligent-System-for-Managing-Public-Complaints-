package com.example.projet_java.Repository;

import com.example.projet_java.Model.Role;
import com.example.projet_java.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    User findByRole(Role role);


}
