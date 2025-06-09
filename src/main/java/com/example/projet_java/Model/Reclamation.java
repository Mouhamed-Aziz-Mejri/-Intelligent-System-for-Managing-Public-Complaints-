package com.example.projet_java.Model;

import jakarta.persistence.*;
import lombok.Data;


import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "reclamation")
public class Reclamation {

    // Getters and setters
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")

    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "localisation")
    private String localisation;

    @Column(name = "description")
    private String description;

    @Column(name = "statut")
    private Statut statut;

    @Column(name = "dateCreation")
    private String dateCreation;

    @Column(name = "ownerName")
    private  String ownerName;

    @Column(name="Categorie")
    private ReclamationCategory categorie;

    public ReclamationCategory getCategorie() {
        return categorie;
    }
    public void setCategorie(ReclamationCategory categorie) {
        this.categorie = categorie;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Reclamation() {
    }

    public Reclamation(String nom, String localisation, String description, Statut statut, String dateCreation, String ownerName, ReclamationCategory categorie) {
        this.nom = nom;
        this.localisation = localisation;
        this.description = description;
        this.statut = statut;
        this.dateCreation = dateCreation;
        this.ownerName=ownerName;
        this.categorie = categorie;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getLocalisation() {
        return localisation;
    }

    public String getDescription() {
        return description;
    }

    public Statut getStatut() {
        return statut;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }
}
