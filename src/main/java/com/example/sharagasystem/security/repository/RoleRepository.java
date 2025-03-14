package com.example.sharagasystem.security.repository;

import com.example.sharagasystem.security.model.Role;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    @Query("""
           SELECT r
           FROM Role r
           WHERE r.roleName = :name
           """)
    Optional<Role> findByName(Role.RoleName name);
}
