package com.example.competence.auth.roles;

import com.example.competence.Entity.Personne;
import com.example.competence.Repository.PersonneRepository;
import com.example.competence.auth.AuthService;
import com.example.competence.auth.dto.RegisterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RoleConfiguration {

    public static Role ADMIN = null;
    public static Role USER = null;

    @Autowired
    private void init(RoleRepository repository, AuthService service, PersonneRepository utilisateurRepository){
        ADMIN = repository.save(new Role(1L, "ADMIN", "Administrateur", "Administrateur du site"));
        USER = repository.save(new Role(2L, "USER", "Utilisateur", "Utilisateur du site"));
        service.register(new RegisterRequestDto("user","azerty"));
        Personne admin = service.register(new RegisterRequestDto("admin", "admin"));
        admin.setRoles(List.of(new Role(1L, "ADMIN","Administrateur", "Administrateur du site")));
        utilisateurRepository.save(admin);
    }
}