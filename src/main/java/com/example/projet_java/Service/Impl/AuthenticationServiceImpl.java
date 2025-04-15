package com.example.projet_java.Service.Impl;


import com.example.projet_java.Model.User;
import com.example.projet_java.Repository.UserRepository;
import com.example.projet_java.Service.AuthenticationService;
import com.example.projet_java.Service.JwtService;
import com.example.projet_java.dto.JwtAuthenticationResponse;
import com.example.projet_java.dto.RefreshTokenRequest;
import com.example.projet_java.dto.SignInRequest;
import com.example.projet_java.dto.SignUpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(JwtService jwtService , AuthenticationManager authenticationManager , UserRepository userRepository , PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signUp(SignUpRequest request) {
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(request.getRole());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);

    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName() , request.getPassword()));
        var user = userRepository.findByUsername(request.getUserName())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>() , user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(jwt);
        return jwtAuthenticationResponse;


    }


    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest request) {
        String userName = jwtService.extractUserName(request.getToken());
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
        if (jwtService.isTokenValid(request.getToken() , user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(request.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}
