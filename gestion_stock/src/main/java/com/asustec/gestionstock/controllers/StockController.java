package com.asustec.gestionstock.controllers;

import com.asustec.gestionstock.entities.ProduitStock;
import com.asustec.gestionstock.repositories.ProduitStockRepository;
import com.asustec.gestionstock.services.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@CrossOrigin("*")
public class StockController {

    private final StockService service;

    @Autowired
    private ProduitStockRepository produitStockRepository;

    public StockController(StockService service) {
        this.service = service;
    }

    @GetMapping("/produits")
    public List<ProduitStock> getStocks() {
        return service.getAllStocks();
    }

    @GetMapping("/produits/{code}")
    public ProduitStock getStockByCode(@PathVariable String code) {
        return service.getByCode(code);
    }

    @PostMapping("/produits")
    public ProduitStock addStock(@RequestBody ProduitStock stock) {
        return service.saveStock(stock);
    }

    @PutMapping("/produits/{code}/decrement/{qte}")
    public void decrement(@PathVariable String code, @PathVariable int qte) {
        service.decrementStock(code, qte);
    }

    // 🔥 Nouveau endpoint utilisé par ton microservice Commande
    @PutMapping("/produits-stock/reduire/{codeProduit}/{quantite}")
    public ResponseEntity<String> reduireStock(
            @PathVariable String codeProduit,
            @PathVariable int quantite) {

        ProduitStock produit = produitStockRepository.findByCodeProduit(codeProduit);

        if (produit == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produit introuvable");
        }

        if (produit.getQuantiteDisponible() < quantite) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Quantité insuffisante en stock");
        }

        // 🔥 Réduction réelle du stock
        produit.setQuantiteDisponible(
                produit.getQuantiteDisponible() - quantite
        );

        produitStockRepository.save(produit);

        return ResponseEntity.ok("Stock mis à jour");
    }

}
