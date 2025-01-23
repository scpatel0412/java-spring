package com.example.demo.auth;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;
    private final String jwtSecret =
        "e9eEc7EXV1oxQ8uOU1oXyOlS8Wz7Fpqy3ka6Q7QpH9g="; // Replace with your secret key

    public JwtAuthFilter(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws IOException, ServletException {
        System.out.println("Request URI: " + request.getRequestURI());

        // Skip authentication for /auth/** routes
        if (
            request.getRequestURI().startsWith("/auth/register") ||
            request.getRequestURI().startsWith("/auth/login")
        ) {
            System.out.println("Skipping JWT filter for /auth/** route");
            filterChain.doFilter(request, response);
            return;
        }

        if (request.getRequestURI().startsWith("/auth/login")) {
            System.out.println("Skipping JWT filter for /auth/** route");
            filterChain.doFilter(request, response);
            return;
        }

        if (request.getRequestURI().startsWith("/api/user-roles/roles")) {
            System.out.println("Skipping JWT filter for /api/user-roles route");
            filterChain.doFilter(request, response);
            return;
        }

        if (request.getRequestURI().startsWith("/")) {
            System.out.println("Skipping JWT filter for /auth/** route");
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");

        System.out.println("Authorization Header: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("No Bearer token in header");
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        System.out.println("hello world \n" + jwt);
        final Claims username = extractUsername(jwt);
        System.out.println("hello world username \n" + username);
        if (
            username != null &&
            SecurityContextHolder.getContext().getAuthentication() == null
        ) {
            UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(
                    username.getSubject()
                );

            if (validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        null
                    );
                authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response); // Continue the filter chain
    }

    private Claims extractUsername(String token) {
        try {
            return Jwts
                .parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    private boolean validateToken(String token, UserDetails userDetails) {
        final Claims username = extractUsername(token);
        // System.out.print();
        return (
            username.getSubject().equals(userDetails.getUsername()) &&
            !isTokenExpired(token)
        );
    }

    private boolean isTokenExpired(String token) {
        Claims claims = Jwts
            .parserBuilder()
            .setSigningKey(jwtSecret)
            .build()
            .parseClaimsJws(token)
            .getBody();
        return claims.getExpiration().before(new Date());
    }
}
