package com.example.competence.Services;

import com.example.competence.Entity.Equipe;
import com.example.competence.Entity.Personne;
import com.example.competence.Repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.competence.Services.PersonneService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    private final PersonneService personneService;

    public EquipeService(EquipeRepository equipeRepository, PersonneService personneService) {
        this.equipeRepository = equipeRepository;
        this.personneService = personneService;
    }

    // CRUD operations
    public Equipe create(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    public Equipe findById(Long id) {
        return equipeRepository.findById(id).get();
    }

    public Iterable<Equipe> findAll() {
        return equipeRepository.findAll();
    }

    public Equipe update(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    public void deleteById(Long id) {
        equipeRepository.deleteById(id);
    }

    public void ajouterMembre(Long idEquipe, Long idPersonne){
        Personne personne = this.personneService.findById(idPersonne);
        Equipe equipe = this.findById(idEquipe);
        equipe.getMembres().add(personne);
        this.update(equipe);
    }

    public void supprimerMembre(Long idEquipe, Long idPersonne){
        Equipe equipe = this.findById(idEquipe);
        equipe.getMembres().removeIf(personne-> Objects.equals(personne.getId(), idPersonne));
        this.update(equipe);
    }

    public Set<Personne> getMembresByPersonne(Long idPersonne){
        Personne personne = this.personneService.findById(idPersonne);
        return personne.getEquipe().getMembres();

    }

}
