package com.example.projet_java.Controller;

import com.example.projet_java.Model.User;
import com.example.projet_java.Service.AuthenticationService;
import com.example.projet_java.dto.JwtAuthenticationResponse;
import com.example.projet_java.dto.RefreshTokenRequest;
import com.example.projet_java.dto.SignInRequest;
import com.example.projet_java.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private  final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) {
        try {

            return  ResponseEntity.ok(authenticationService.signUp(request));

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }}


    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) {

            if (request.getUserName()==null || request.getPassword()==null){
             return ResponseEntity.badRequest().body("Username or password cannot be null");
            }else {

            return  ResponseEntity.ok(authenticationService.signIn(request));

        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest request){
        return  ResponseEntity.ok(authenticationService.refreshToken(request));
    }
}
