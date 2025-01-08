package com.example.demo.auth;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.users.Users;
import com.example.demo.users.UsersRepository; 

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final UsersRepository usersRepository;

    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> userOptional = usersRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            // Use Spring Security's User class, mapping fields from Users to User
            return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
        }

        throw new UsernameNotFoundException("User not found: " + username);
    }
}
