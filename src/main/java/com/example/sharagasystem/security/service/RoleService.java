package com.example.sharagasystem.security.service;

import com.example.sharagasystem.security.model.Role;

public interface RoleService {
    Role findByName(Role.RoleName name);
}
