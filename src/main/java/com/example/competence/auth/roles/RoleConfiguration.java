package com.example.competence.auth.roles;

import kira.formation.spring.security.auth.AuthService;
import kira.formation.spring.security.auth.dto.RegisterRequestDto;
import kira.formation.spring.security.utilisateur.Utilisateur;
import kira.formation.spring.security.utilisateur.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RoleConfiguration {

    public static Role ADMIN = null;
    public static Role USER = null;

    @Autowired
    private void init(RoleRepository repository, AuthService service, UtilisateurRepository utilisateurRepository){
        ADMIN = repository.save(new Role(1L, "ADMIN"));
        USER = repository.save(new Role(2L, "USER"));
        service.register(new RegisterRequestDto("user","azerty"));
        Utilisateur admin = service.register(new RegisterRequestDto("admin", "admin"));
        admin.setRoles(List.of(new Role(1L, "ADMIN")));
        utilisateurRepository.save(admin);
    }
}