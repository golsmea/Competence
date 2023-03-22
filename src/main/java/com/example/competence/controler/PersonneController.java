package com.example.competence.controler;

import com.example.competence.Entity.Personne;
import com.example.competence.Entity.PersonneCompetence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.example.competence.Services.PersonneService;

import java.util.Optional;

@RestController
@RequestMapping("/personnes")
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }

    @GetMapping("{id}")
    public Optional<Personne> findById(Long id) {
        return Optional.ofNullable(personneService.findById(id));
    }

    @GetMapping("/")
    public Iterable<Personne> findAll() {
        return personneService.findAll();
    }

    @Secured("MANAGER")
    @PostMapping("/")
    public Personne create(Personne personne) {
        return personneService.createPersonne(personne);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        personneService.deleteById(id);
    }

    @PutMapping
    public Personne update(@RequestBody Personne personne) {
        return personneService.update(personne);
    }






}
