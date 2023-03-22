package com.example.competence.Repository;

import com.example.competence.Entity.PersonneCompetence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonneCompetenceRepository extends JpaRepository<PersonneCompetence, Long>{

    List<PersonneCompetence> findByPersonneId(Long idPersonne);

    List<PersonneCompetence> findByCompetenceId(Long idCompetence);
}
