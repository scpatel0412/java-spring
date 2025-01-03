package com.example.demo.auth;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Mocked example: replace with real user lookup
        if ("testuser".equals(username)) {
            return new User(
                    "testuser",
                    "{noop}password", // No-op encoding for simplicity; replace with encoded passwords
                    Collections.emptyList() // Authorities
            );
        }

        throw new UsernameNotFoundException("User not found: " + username);
    }
}
