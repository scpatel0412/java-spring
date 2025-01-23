package com.example.demo.user_roles;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {
    Optional<UserRoles> findByRole(String role);
}
