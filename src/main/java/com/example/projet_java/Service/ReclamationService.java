package com.example.projet_java.Service;

import com.example.projet_java.Model.Reclamation;
import com.example.projet_java.Model.ReclamationCategory;

import java.util.List;
import java.util.Optional;

public interface ReclamationService {
    List<Reclamation> getAllReclamations();
    String addReclamation(Reclamation reclamation);
    Optional<List<Reclamation> >getOwnReclamations();
    Reclamation getReclamationByNom(String nom);

    Optional<List<Reclamation>> getReclamationByCategorie(ReclamationCategory categorie);




    Reclamation getReclamationById(Long id);

    String updateReclamation(String nom, Reclamation reclamation);

    String UpdateOwnReclamation(String ownerName, Reclamation reclamation);

    String deleteReclamation(Long id);
    String deleteOwnReclamation(Long id);

}
