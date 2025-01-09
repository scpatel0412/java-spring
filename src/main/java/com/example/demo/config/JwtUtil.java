package com.example.demo.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private String SECRET_KEY = "e9eEc7EXV1oxQ8uOU1oXyOlS8Wz7Fpqy3ka6Q7QpH9g="; // Keep this safe and secure

    // Method to generate JWT token
    public String generateToken(String username) {
        return Jwts
            .builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(
                new Date(System.currentTimeMillis() + 1000 * 60 * 60)
            ) // 1 hour validity
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }

    // Method to extract username from JWT token
    public String extractUsername(String token) {
        return Jwts
            .parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    // Method to validate token
    public boolean validateToken(String token, String username) {
        return (
            username.equals(extractUsername(token)) && !isTokenExpired(token)
        );
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts
            .parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody()
            .getExpiration();
    }
}
