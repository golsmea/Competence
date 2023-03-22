package com.example.competence.controler;

import com.example.competence.Services.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.competence.Entity.Equipe;

import java.util.Optional;

@RestController
@RequestMapping("/equipes")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @GetMapping("{id}")
    public Optional<Equipe> findById(Long id) {
        return Optional.ofNullable(equipeService.findById(id));
    }

    @GetMapping("/")
    public Iterable<Equipe> findAll() {
        return equipeService.findAll();
    }

    @PostMapping("/")
    public Equipe create(Equipe equipe) {
        return equipeService.create(equipe);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        equipeService.deleteById(id);
    }

    @PutMapping
    public Equipe update(@RequestBody Equipe equipe) {
        return equipeService.update(equipe);
    }

    @PostMapping("{idEquipe}/membres/{idPersonne}")
    public void ajouterMembre(@PathVariable Long idEquipe, @PathVariable Long idPersonne) {
        equipeService.ajouterMembre(idEquipe, idPersonne);
    }

    @DeleteMapping("{idEquipe}/membres/{idPersonne}")
    public void supprimerMembre(@PathVariable Long idEquipe, @PathVariable Long idPersonne) {
        equipeService.supprimerMembre(idEquipe, idPersonne);
    }




}
