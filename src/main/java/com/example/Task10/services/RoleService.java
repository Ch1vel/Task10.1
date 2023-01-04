package com.example.Task10.services;

import com.example.Task10.models.Role;
import com.example.Task10.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements RoleServiceInterface{

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> showRoles() {
        return roleRepository.findAll();
    }
}
