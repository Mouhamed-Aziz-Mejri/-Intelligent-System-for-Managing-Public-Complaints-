package com.example.projet_java.Service;

import com.example.projet_java.Model.User;
import com.example.projet_java.dto.JwtAuthenticationResponse;
import com.example.projet_java.dto.RefreshTokenRequest;
import com.example.projet_java.dto.SignInRequest;
import com.example.projet_java.dto.SignUpRequest;

public interface AuthenticationService {
    User signUp(SignUpRequest request) ;



    JwtAuthenticationResponse signIn(SignInRequest request);


    JwtAuthenticationResponse refreshToken(RefreshTokenRequest request);
}
