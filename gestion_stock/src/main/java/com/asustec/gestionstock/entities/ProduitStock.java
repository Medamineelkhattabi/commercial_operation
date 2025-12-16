package com.asustec.gestionstock.entities;

import jakarta.persistence.*;

@Entity
public class ProduitStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codeProduit;

    private int quantiteDisponible;

    // Constructeurs
    public ProduitStock() {}

    public ProduitStock(String codeProduit, int quantiteDisponible) {
        this.codeProduit = codeProduit;
        this.quantiteDisponible = quantiteDisponible;
    }

    // getters - setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodeProduit() { return codeProduit; }
    public void setCodeProduit(String codeProduit) { this.codeProduit = codeProduit; }

    public int getQuantiteDisponible() { return quantiteDisponible; }
    public void setQuantiteDisponible(int quantiteDisponible) { this.quantiteDisponible = quantiteDisponible; }
}
