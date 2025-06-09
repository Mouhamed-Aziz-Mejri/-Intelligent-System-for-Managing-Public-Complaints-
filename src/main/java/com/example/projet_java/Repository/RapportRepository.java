package com.example.projet_java.Repository;

import com.example.projet_java.Model.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RapportRepository extends JpaRepository<Rapport, Long> {
    boolean existsByNom(String nom);

    List<Rapport> findByDate(LocalDate localDate);

    Optional<Rapport> findByNom(String nomRapport);
}
