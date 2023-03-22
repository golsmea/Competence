package com.example.competence.controler;

import com.example.competence.Entity.Competence;
import com.example.competence.Services.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/competences")
public class CompetenceController {

    @Autowired
    private CompetenceService competenceService;

    public CompetenceController(CompetenceService competenceService) {
        this.competenceService = competenceService;
    }

    @GetMapping("{id}")
    public Optional<Competence> findById(Long id) {
        return Optional.ofNullable(competenceService.findById(id));
    }

    @GetMapping("/")
    public Iterable<Competence> findAll() {
        return competenceService.findAll();
    }

    @Secured("MANAGER")
    @PostMapping("/")
    public Competence create(Competence competence) {
        return competenceService.createCompetence(competence);
    }

    @PutMapping
    public Competence update(@RequestBody Competence competence) {
        return competenceService.update(competence);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        competenceService.deleteById(id);
    }

}
