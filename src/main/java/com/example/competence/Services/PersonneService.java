package com.example.competence.Services;

import com.example.competence.Entity.Competence;
import com.example.competence.Entity.Personne;
import com.example.competence.Entity.PersonneCompetence;
import com.example.competence.Enums.NiveauCompetence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.competence.Repository.PersonneRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class PersonneService {

    @Autowired
    private PersonneRepository personneRepository;

    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    // CRUD operations

    public Personne createPersonne(Personne personne) {
        return personneRepository.save(personne);
    }

    public Personne findById(Long id) {
        return personneRepository.findById(id).get();
    }

    public Iterable<Personne> findAll() {
        return personneRepository.findAll();
    }

    public Personne update(Personne personne) {
        return personneRepository.save(personne);
    }

    public void deleteById(Long id) {
        personneRepository.deleteById(id);
    }







}
