package com.example.competence.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "equipe")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private Set<Personne> membres = new HashSet<>();

    public void addMembre(Personne personne) {
        membres.add(personne);
        personne.setEquipe(this);
    }

    public void removeMembre(Personne personne) {
        membres.remove(personne);
        personne.setEquipe(null);
    }

}
