package com.example.competence.auth.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findById(Long id) {
        return roleRepository.findById(id).get();
    }

    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role create(Role role) {
        return roleRepository.save(role);
    }

    public Role update(Role role) {
        return roleRepository.save(role);
    }

    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

}
