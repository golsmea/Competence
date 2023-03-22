package com.example.competence.Entity;

import com.example.competence.auth.roles.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personne")
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom", length = 50, nullable = false)
    private String nom;

    @Column(name = "prenom", length = 50, nullable = false)
    private String prenom;

    @Column(name = "manager", nullable = false)
    private Boolean manager;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_equipe", nullable = false)
    private Equipe equipe;

    @ManyToMany
    private List<Role> roles = new ArrayList<>();

}
