package com.asustec.gestioncommercial.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CommandeGlobale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codePointVente;

    private double montantTotal;

    private LocalDateTime dateCommande = LocalDateTime.now();

    // Constructeurs
    public CommandeGlobale() {
    }

    public CommandeGlobale(String codePointVente, double montantTotal) {
        this.codePointVente = codePointVente;
        this.montantTotal = montantTotal;
        this.dateCommande = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodePointVente() {
        return codePointVente;
    }

    public void setCodePointVente(String codePointVente) {
        this.codePointVente = codePointVente;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }
}
