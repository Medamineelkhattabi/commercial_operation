package com.asustec.gestionvente.controllers;

import com.asustec.gestionvente.dto.ProduitCatalogueDTO;
import com.asustec.gestionvente.services.CatalogueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CatalogueController {

    private final CatalogueService catalogueService;

    public CatalogueController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @GetMapping("/produits")
    public String afficherProduits(Model model) {

        List<ProduitCatalogueDTO> produits = catalogueService.getCatalogueProduits();
        model.addAttribute("produits", produits);

        return "produits";   // => templates/produits.html
    }
}
