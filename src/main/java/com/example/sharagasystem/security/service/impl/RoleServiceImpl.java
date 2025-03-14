package com.example.sharagasystem.security.service.impl;

import com.example.sharagasystem.exception.NotFoundException;
import com.example.sharagasystem.security.model.Role;
import com.example.sharagasystem.security.repository.RoleRepository;
import com.example.sharagasystem.security.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role findByName(Role.RoleName name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Role with name " + name + " not found"));
    }
}
