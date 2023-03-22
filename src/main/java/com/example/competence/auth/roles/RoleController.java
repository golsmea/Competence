package com.example.competence.auth.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping("{id}")
    public Role findById(Long id) {
        return roleService.findById(id);
    }

    @RequestMapping("/")
    public Iterable<Role> findAll() {
        return roleService.findAll();
    }

    @Secured("MANAGER")
    @RequestMapping("/")
    public Role create(Role role) {
        return roleService.create(role);
    }

    @Secured("MANAGER")
    @RequestMapping("{id}")
    public void deleteById(Long id) {
        roleService.deleteById(id);
    }

}
