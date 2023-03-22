package com.example.competence.Services;

import com.example.competence.Repository.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.competence.Entity.Competence;

@Service
public class CompetenceService {
    @Autowired
    private CompetenceRepository competenceRepository;

    public CompetenceService(CompetenceRepository competenceRepository) {
        this.competenceRepository = competenceRepository;
    }

    // CRUD operations
    public Competence createCompetence(Competence competence) {
        return competenceRepository.save(competence);
    }

    public Competence findById(Long id) {
        return competenceRepository.findById(id).get();
    }

    public Iterable<Competence> findAll() {
        return competenceRepository.findAll();
    }

    public Competence update(Competence competence) {
        return competenceRepository.save(competence);
    }

    public void deleteById(Long id) {
        competenceRepository.deleteById(id);
    }
}
