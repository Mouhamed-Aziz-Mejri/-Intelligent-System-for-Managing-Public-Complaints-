package com.example.projet_java.Repository;

import com.example.projet_java.Model.Reclamation;
import com.example.projet_java.Model.ReclamationCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {

    boolean existsByNom(String nom);

    Optional<Reclamation> findByNom(String nom);

    Optional<List<Reclamation>> findByOwnerName(String ownerName);

    Optional<List<Reclamation>> findByCategorie(ReclamationCategory categorie);
}
