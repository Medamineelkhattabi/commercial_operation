package com.asustec.gestioncommercial.entities;

import jakarta.persistence.*;

@Entity
public class ProduitPrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String libelle;

    private String description;

    private double prixUnitaire;

    // Constructeurs
    public ProduitPrix() {
    }

    public ProduitPrix(String code, String libelle, String description, double prixUnitaire) {
        this.code = code;
        this.libelle = libelle;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
}
