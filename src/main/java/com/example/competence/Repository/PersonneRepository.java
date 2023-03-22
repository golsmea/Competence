package com.example.competence.Repository;

import com.example.competence.Entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long>{
    Personne findByUsername(String username);
}
