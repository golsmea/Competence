package com.example.competence.auth;

import com.example.competence.Entity.Personne;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Personne register(RegisterRequestDto dto){
        Personne personne = new Personne();
        personne.setUsername(dto.getUsername());
        personne.setRoles(List.of(new Role(2L, "USER")));
        String password = passwordEncoder.encode(dto.getPassword());
        personne.setPassword(password);
        return this.repository.save(utilisateur);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Personne personne = repository.findByUsername(username);
        if (personne == null){
            throw new UsernameNotFoundException("Aucun utilisateur ne possède l'username "+username);
        }
        return new User(personne.getUsername(), personne.getPassword(), personne.getRoles());
    }

}