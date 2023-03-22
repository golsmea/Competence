package com.example.competence.Entity;

import com.example.competence.Enums.NiveauCompetence;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "competences")
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_competence", nullable = false)
    private Long id;

    @Column(name = "nom_competence", nullable = false, length = 100)
    private String nom;

    @Column(name = "description_competence", nullable = false, length = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "niveau_minimum", nullable = false)
    private NiveauCompetence niveauMinimum;

    @ManyToMany
    @JoinTable(
            name = "competence_prerequis",
            joinColumns = @JoinColumn(name = "id_competence"),
            inverseJoinColumns = @JoinColumn(name = "id_competence_prerequis")
    )
    private List<Competence> prerequis;
}
