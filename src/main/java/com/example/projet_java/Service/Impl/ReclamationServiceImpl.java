package com.example.projet_java.Service.Impl;

import com.example.projet_java.Model.Reclamation;
import com.example.projet_java.Model.ReclamationCategory;
import com.example.projet_java.Model.Statut;
import com.example.projet_java.Repository.ReclamationRepository;
import com.example.projet_java.Service.ReclamationService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReclamationServiceImpl implements ReclamationService {

    private  final ReclamationRepository reclamationRepository;
    public ReclamationServiceImpl(ReclamationRepository reclamationRepository) {
        this.reclamationRepository = reclamationRepository;
    }
@Override
    public  String addReclamation(Reclamation reclamation) {
        if (reclamation == null || reclamation.getNom().isEmpty()) {
            return "Reclamation cannot be null or empty";
        }else if (reclamation.getLocalisation() == null || reclamation.getLocalisation().isEmpty()) {
            return "Reclamation localisation cannot be null or empty";
        }else if (reclamationRepository.existsByNom(reclamation.getNom())) {
            return "Reclamation with name " + reclamation.getNom() + " already exists.";
        }
    if (reclamation.getDateCreation() == null || reclamation.getDateCreation().isEmpty()) {
        reclamation.setDateCreation(LocalDate.now().toString());
    }
    String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
    String currentUserRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
            .findFirst()
            .map(Object::toString)
            .orElse("USER");
    reclamation.setOwnerName(currentUsername);
    reclamation.setStatut(Statut.En_Attente);
    reclamationRepository.save(reclamation);
        return "Reclamation  " + reclamation.getNom() +" has been added successfully.";
    }

    @Override
    public Optional<List<Reclamation>> getOwnReclamations() {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<List<Reclamation>> reclamations = reclamationRepository.findByOwnerName(currentUsername);
        if (reclamations.isEmpty() || reclamations.get().isEmpty()) {
            throw new RuntimeException("No reclamations found for owner: " + currentUsername);
        }
        return reclamations;
    }

    @Override
    public Reclamation getReclamationById(Long id) {
        return reclamationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reclamation not found with id: " + id));
    }

    @Override
    public String updateReclamation(String nom , Reclamation reclamation) {
        if (reclamation == null || reclamation.getNom().isEmpty()) {
            throw new RuntimeException("Reclamation cannot be null or empty");
        }
        Reclamation existingReclamation = (reclamationRepository.findByNom(nom)
                .orElseThrow(() -> new RuntimeException("Reclamation not found with nom: " + nom)));
        existingReclamation.setNom(reclamation.getNom());
        existingReclamation.setDescription(reclamation.getDescription());
        existingReclamation.setStatut(Statut.En_Attente);
        reclamationRepository.save(existingReclamation);
        return "Reclamation updated successfully";
    }
    @Override
    public String deleteReclamation(Long id) {

        if (!reclamationRepository.existsById(id)) {
            throw new RuntimeException("Reclamation not found with id: " + id);
        }
        reclamationRepository.deleteById(id);
        return "Reclamation with id " + id + " has been deleted successfully.";
    }

    @Override
    public Reclamation getReclamationByNom(String nom) {
          Optional<Reclamation> existeRecl =reclamationRepository.findByNom(nom) ;
          if (existeRecl.isEmpty()){
                throw new RuntimeException("Reclamation not found with name: " + nom);
          }
            return existeRecl.get();
    }

    @Override
    public Optional<List<Reclamation>> getReclamationByCategorie(ReclamationCategory categorie) {
        Optional<List<Reclamation>> reclamations = reclamationRepository.findByCategorie(categorie);
        if (reclamations.isEmpty() || reclamations.get().isEmpty()) {
            throw new RuntimeException("No reclamations found for category: " + categorie);
        }
        return reclamations;
    }

    @Override
    public String UpdateOwnReclamation(String nom, Reclamation reclamation) {
        if (reclamation == null || reclamation.getNom() == null || reclamation.getNom().isEmpty()) {
            throw new RuntimeException("Reclamation cannot be null or empty");
        }
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Reclamation existingReclamation = reclamationRepository.findByNom(nom)
                .orElseThrow(() -> new RuntimeException("Reclamation not found with nom: " + nom));
        if (!existingReclamation.getOwnerName().equals(currentUsername)) {
            throw new RuntimeException("You are not authorized to update this reclamation.");
        }
        existingReclamation.setDescription(reclamation.getDescription());
        existingReclamation.setLocalisation(reclamation.getLocalisation());
        existingReclamation.setStatut(Statut.En_Attente);
        reclamationRepository.save(existingReclamation);
        return "Reclamation updated successfully";
    }
    @Override
    public String deleteOwnReclamation(Long id) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Reclamation> optionalReclamation = reclamationRepository.findById(id);
        if (optionalReclamation.isEmpty()) {
            throw new RuntimeException("Reclamation not found with id: " + id);
        }

        Reclamation reclamation = optionalReclamation.get();

        // Vérification de la propriété de la réclamation
        if (!reclamation.getOwnerName().equals(currentUsername)) {
            throw new RuntimeException("You are not authorized to delete this reclamation.");
        }

        reclamationRepository.deleteById(id);

        return "Reclamation with id " + id + " has been deleted successfully.";
    }

    @Override
    public List<Reclamation>getAllReclamations() {
        List<Reclamation> reclamations = reclamationRepository.findAll();
if(reclamations.isEmpty()) {
            throw new RuntimeException("No reclamations found");
        }
        return reclamations;
    }
}
