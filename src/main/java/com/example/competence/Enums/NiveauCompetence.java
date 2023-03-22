package com.example.competence.Enums;

public enum NiveauCompetence {
    AUCUNE_CONNAISSANCE(0),
    NOM_COMPETENCE(1),
    NOM_DEFINITION(2),
    NOM_FONCTIONNEMENT(3),
    UTILISATION_BASIQUE(4),
    UTILISATION_INTERMEDIAIRE(5),
    UTILISATION_AVANCEE(6),
    UTILISATION_EXPERTE(7),
    MAITRISE(8),
    ENSEIGNEMENT(9),
    CREATION(10);

    private final int niveau;

    NiveauCompetence(int niveau) {
        this.niveau = niveau;
    }

    public int getNiveau() {
        return niveau;
    }
}
