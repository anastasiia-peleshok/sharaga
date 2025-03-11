package com.example.sharagasystem.mapper;

import com.example.sharagasystem.repository.RoleRepository;
import com.example.sharagasystem.security.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapperHelper {
    private final RoleRepository roleRepository;

    public RoleMapperHelper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getOrCreateRole(String roleName) {
        if (roleName == null) {
            return null;
        }

        return roleRepository.findByRoleName(Role.RoleName.valueOf(roleName))
                .orElseGet(() -> {
                    Role newRole = new Role(Role.RoleName.valueOf(roleName));
                    return roleRepository.save(newRole);
                });
    }
}
