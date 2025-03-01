package com.example.sharagasystem.repository;

import com.example.sharagasystem.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    public Optional<Role> findByRoleName(Role.RoleName role);
}
