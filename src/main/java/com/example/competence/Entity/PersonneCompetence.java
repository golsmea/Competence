package com.example.competence.Entity;

import com.example.competence.Enums.NiveauCompetence;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "personne_competence")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonneCompetence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_personne")
    private Personne personne;

    @ManyToOne
    @JoinColumn(name = "id_competence")
    private Competence competence;

    @Enumerated(EnumType.STRING)
    @Column(name = "niveau")
    private NiveauCompetence niveau;

    public static void setNiveau(NiveauCompetence niveau) {
        niveau = niveau;
    }
}
