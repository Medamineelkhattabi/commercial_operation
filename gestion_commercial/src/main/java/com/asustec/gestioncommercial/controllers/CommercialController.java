package com.asustec.gestioncommercial.controllers;

import com.asustec.gestioncommercial.entities.CommandeGlobale;
import com.asustec.gestioncommercial.entities.ProduitPrix;
import com.asustec.gestioncommercial.services.CommercialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commercial")
@CrossOrigin("*")
public class CommercialController {

    private final CommercialService service;

    // Constructeur pour injection
    public CommercialController(CommercialService service) {
        this.service = service;
    }

    // GET : Liste des produits
    @GetMapping("/produits")
    public List<ProduitPrix> getProduits() {
        return service.getAllProduits();
    }

    // POST : Ajouter un produit
    @PostMapping("/produits")
    public ProduitPrix addProduit(@RequestBody ProduitPrix produit) {
        return service.saveProduit(produit);
    }

    // POST : Enregistrer une commande globale
    @PostMapping("/commandes")
    public CommandeGlobale saveCommande(@RequestBody CommandeGlobale commande) {
        return service.saveCommande(commande);
    }
}
