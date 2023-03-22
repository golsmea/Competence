package com.example.competence.controler;

import com.example.competence.Entity.Personne;
import com.example.competence.Entity.PersonneCompetence;
import com.example.competence.Services.PersonneCompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personneCompetence")
public class PersonneCompetenceController {

    @Autowired
    private PersonneCompetenceService personneCompetenceService;

    public PersonneCompetenceController(PersonneCompetenceService personneCompetenceService) {
        this.personneCompetenceService = personneCompetenceService;
    }

 
    @GetMapping("/")
    public Iterable<PersonneCompetence> findAll() {
        return personneCompetenceService.findAll();
    }


    @GetMapping("/{id}")
    public PersonneCompetence findById(Long id) {
        return personneCompetenceService.findById(id);
    }

    @PutMapping("{idPersonne}/updateNiveau/{idCompetence}")
    public PersonneCompetence updateNiveau(@PathVariable Long idPersonne, @PathVariable Long idCompetence, @RequestBody int niveau) {
        return personneCompetenceService.updateNiveau(idPersonne, idCompetence, niveau);
    }


    @DeleteMapping("/{id}")
    public void deleteById(Long id) {
        personneCompetenceService.deleteById(id);
    }


    @PutMapping("{idPersonne}/addCompetence/{idCompetence}")
    public PersonneCompetence ajouterCompetence(@PathVariable Long idPersonne, @PathVariable Long idCompetence, @RequestBody int niveau) {
        return personneCompetenceService.ajouterCompetence(idPersonne, idCompetence, niveau);
    }

    @PutMapping("{idPersonne}/removeCompetence/{idCompetence}")
    public void supprimerCompetence(@PathVariable Long idPersonne, @PathVariable Long idCompetence) {
        personneCompetenceService.supprimerCompetence(idPersonne, idCompetence);
    }

    @GetMapping("{idPersonne}/competences")
    public Iterable<PersonneCompetence> findByPersonneId(@PathVariable Long idPersonne) {
        return personneCompetenceService.findByPersonneId(idPersonne);
    }

    @GetMapping("competences/{idCompetence}")
    public List<Personne> getPersonnesWithNiveauSup5(@PathVariable Long idCompetence) {
        return personneCompetenceService.getPersonnesWithNiveauSup5( idCompetence);
    }

    @GetMapping("personne/{idPersonne}")
    public int getNiveauGlobal(@PathVariable Long idPersonne) {
        return personneCompetenceService.getNiveauGlobal(idPersonne);
    }



    @GetMapping("/equipe/{idEquipe}/bestNiveauGlobal")
    public Personne getPersonnesEquipeBestNiveauGlobal(@PathVariable Long idEquipe) {
        return personneCompetenceService.getPersonnesEquipeBestNiveauGlobal(idEquipe);
    }



}
