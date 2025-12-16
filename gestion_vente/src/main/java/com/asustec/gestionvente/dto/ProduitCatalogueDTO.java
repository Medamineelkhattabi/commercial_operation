package com.asustec.gestionvente.dto;

public class ProduitCatalogueDTO {

    private String codeProduit;
    private String nomProduit;
    private double prixProduit;
    private int quantiteExistante;

    public ProduitCatalogueDTO() {}

    public ProduitCatalogueDTO(String codeProduit, String nomProduit,
                               double prixProduit, int quantiteExistante) {
        this.codeProduit = codeProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.quantiteExistante = quantiteExistante;
    }

    public String getCodeProduit() { return codeProduit; }
    public void setCodeProduit(String codeProduit) { this.codeProduit = codeProduit; }

    public String getNomProduit() { return nomProduit; }
    public void setNomProduit(String nomProduit) { this.nomProduit = nomProduit; }

    public double getPrixProduit() { return prixProduit; }
    public void setPrixProduit(double prixProduit) { this.prixProduit = prixProduit; }

    public int getQuantiteExistante() { return quantiteExistante; }
    public void setQuantiteExistante(int quantiteExistante) {
        this.quantiteExistante = quantiteExistante;
    }
}
