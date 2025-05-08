package com.example.projet_java.Config;

import com.example.projet_java.Service.JwtService;
import com.example.projet_java.Service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    public JwtAuthenticationFilter(JwtService jwtService , UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request , HttpServletResponse response , FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Authorization Header: " + authHeader);

        if (!StringUtils.hasText(authHeader) || !authHeader.startsWith("Bearer ")) {
            System.out.println("Authorization header is missing or malformed. Skipping authentication.");
            filterChain.doFilter(request , response);
            return;
        }

        jwt = authHeader.substring(7);
        System.out.println("Extracted JWT: " + jwt);

        username = jwtService.extractUserName(jwt);
        System.out.println("Extracted Username from JWT: " + username);

        if (!StringUtils.isEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("No existing authentication found. Validating token...");

            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(username);
            System.out.println("Loaded UserDetails: " + userDetails.getUsername());

            if (jwtService.isTokenValid(jwt , userDetails)) {
                System.out.println("Token is valid. Setting security context.");

                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken
                        (userDetails , null , userDetails.getAuthorities());

                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            } else {
                System.out.println("Invalid token. Authentication not set.");
            }
        } else {
            System.out.println("Username is empty or authentication already exists.");
        }

        filterChain.doFilter(request , response);
    }
}
