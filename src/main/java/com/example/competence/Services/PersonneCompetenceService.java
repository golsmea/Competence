package com.example.competence.Services;

import com.example.competence.Entity.Competence;
import com.example.competence.Entity.Personne;
import com.example.competence.Enums.NiveauCompetence;
import com.example.competence.Repository.PersonneCompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.competence.Entity.PersonneCompetence;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.example.competence.Enums.NiveauCompetence.UTILISATION_INTERMEDIAIRE;

@Service
public class PersonneCompetenceService {

    @Autowired
    private PersonneCompetenceRepository personneCompetenceRepository;
    private final PersonneService personneService;
    private final CompetenceService competenceService;

    public PersonneCompetenceService(PersonneCompetenceRepository personneCompetenceRepository, PersonneService personneService, CompetenceService competenceService) {
        this.personneCompetenceRepository = personneCompetenceRepository;
        this.personneService = personneService;
        this.competenceService = competenceService;
    }

    // CRUD operations
    public PersonneCompetence create(PersonneCompetence personneCompetence) {
        return personneCompetenceRepository.save(personneCompetence);
    }

    public PersonneCompetence findById(Long id) {
        return personneCompetenceRepository.findById(id).get();
    }

    public Iterable<PersonneCompetence> findAll() {
        return personneCompetenceRepository.findAll();
    }

    public PersonneCompetence update(PersonneCompetence personneCompetence) {
        return personneCompetenceRepository.save(personneCompetence);
    }

    public void deleteById(Long id) {
        personneCompetenceRepository.deleteById(id);
    }

    public PersonneCompetence updateNiveau(Long idPersonne, Long idCompetence, int niveau){
        PersonneCompetence personneCompetence = this.findById(idCompetence);
        personneCompetence.setNiveau(NiveauCompetence.values()[niveau]);
        return this.update(personneCompetence);
    }

    public List<PersonneCompetence> findByPersonneId(Long idPersonne){
        return personneCompetenceRepository.findByPersonneId(idPersonne);
    }

    public List<Competence> getCompetences(Long idPersonne) {
        List<PersonneCompetence> personneCompetences = this.findByPersonneId(idPersonne);
        List<Competence> competences = new ArrayList<>();
        for (PersonneCompetence personneCompetence : personneCompetences) {
            competences.add(personneCompetence.getCompetence());
        }
        return competences;
    }



    public List<Competence> findCompetencesWithPrerequisites(Long idPersonne) {
        // Récupération des compétences de la personne
        List<PersonneCompetence> personneCompetences = this.findByPersonneId(idPersonne);

        // Récupération de la liste des compétences associées aux PersonneCompetence
        List<Competence> competences = new ArrayList<>();
        for (PersonneCompetence personneCompetence : personneCompetences) {
            competences.add(personneCompetence.getCompetence());
        }

        // Récupération des compétences dont la personne a les prérequis
        List<Competence> competencesAvecPreRequis = new ArrayList<>();
        for (Competence competence : competences) {
            if (competence.getPrerequis() != null && !competence.getPrerequis().isEmpty()) {
                boolean prerequisOk = true;
                for (Competence prerequis : competence.getPrerequis()) {
                    boolean prerequisTrouve = false;
                    for (PersonneCompetence personneCompetence : personneCompetences) {
                        if (personneCompetence.getCompetence().equals(prerequis) && personneCompetence.getNiveau().ordinal() >= prerequis.getNiveauMinimum().ordinal()) {
                            prerequisTrouve = true;
                            break;
                        }
                    }
                    if (!prerequisTrouve) {
                        prerequisOk = false;
                        break;
                    }
                }
                if (prerequisOk) {
                    competencesAvecPreRequis.add(competence);
                }
            } else {
                competencesAvecPreRequis.add(competence);
            }
        }
        return competencesAvecPreRequis;
    }


    public PersonneCompetence ajouterCompetence(Long idPersonne, Long idCompetence, int niveau){
        PersonneCompetence personneCompetence = new PersonneCompetence();
        personneCompetence.setPersonne(this.personneService.findById(idPersonne));
        PersonneCompetence.setNiveau(NiveauCompetence.values()[niveau]);
        personneCompetence.setCompetence(this.competenceService.findById(idCompetence));
        return this.create(personneCompetence);
    }

    public void supprimerCompetence(Long idPersonne, Long idCompetence){
        this.deleteById(idCompetence);
    }


    public List<Personne> getPersonnesWithNiveauSup5(Long idCompetence) {
        List<Personne> personnes = new ArrayList<>();
        List<PersonneCompetence> personneCompetences = personneCompetenceRepository.findByCompetenceId(idCompetence);
        for (PersonneCompetence personneCompetence : personneCompetences) {
            if (personneCompetence.getNiveau().compareTo(UTILISATION_INTERMEDIAIRE) > 0) {
                personnes.add(personneCompetence.getPersonne());
            }
        }
        return personnes;
    }

    public int getNiveauGlobal(Long idPersonne) {
        List<PersonneCompetence> personneCompetences = this.findByPersonneId(idPersonne);
        int niveauGlobal = 0;
        for (PersonneCompetence personneCompetence : personneCompetences) {
            niveauGlobal += personneCompetence.getNiveau().ordinal();
        }
        return niveauGlobal;
    }

    public Personne getPersonnesEquipeBestNiveauGlobal(Long idPersonne) {
        List<Personne> personnes = new ArrayList<>();
        Personne personne = personneService.findById(idPersonne);
        Set<Personne> personnesEquipe = personne.getEquipe().getMembres();;
        int niveauGlobalMax = 0;
        Personne personneBestNiveauGlobal = null;
        for (Personne personneEquipe : personnesEquipe) {
            int niveauGlobal = this.getNiveauGlobal(personneEquipe.getId());
            if (niveauGlobal > niveauGlobalMax) {
                niveauGlobalMax = niveauGlobal;
                personneBestNiveauGlobal = personneEquipe;
            }
        }
        return personneBestNiveauGlobal;

    }


}
