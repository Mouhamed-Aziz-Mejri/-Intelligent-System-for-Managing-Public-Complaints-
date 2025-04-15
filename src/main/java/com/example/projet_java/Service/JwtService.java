package com.example.projet_java.Service;

import com.example.projet_java.Model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public interface JwtService {
String generateToken(UserDetails userDetails);


String extractUserName(String token);

boolean isTokenValid(String token,UserDetails userDetails);

    Object generateRefreshToken(Map<String,Object> extraClaims, User user);
}
