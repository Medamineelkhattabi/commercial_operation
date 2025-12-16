package com.asustec.gestionvente.controllers;

import com.asustec.gestionvente.dto.ProduitCatalogueDTO;
import com.asustec.gestionvente.dto.ProduitPrixDTO;
import com.asustec.gestionvente.services.CatalogueService;
import com.asustec.gestionvente.services.PdfService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Controller
public class CommandeController {

    private final CatalogueService catalogueService;
    private final PdfService pdfService;

    public CommandeController(CatalogueService catalogueService,
                              PdfService pdfService) {
        this.catalogueService = catalogueService;
        this.pdfService = pdfService;
    }

    // ➤ page formulaire de commande
    @GetMapping("/commande/nouvelle/{codeProduit}")
    public String nouvelleCommande(@PathVariable String codeProduit, Model model) {

        Optional<ProduitCatalogueDTO> opt = catalogueService.getCatalogueProduits()
                .stream()
                .filter(p -> p.getCodeProduit().equals(codeProduit))
                .findFirst();

        if (opt.isEmpty()) {
            return "redirect:/produits";
        }

        ProduitCatalogueDTO produitCatalogue = opt.get();

        // Récupérer le prix
        ProduitPrixDTO prixDTO = catalogueService.getProduitPrix(codeProduit);

        model.addAttribute("produit", produitCatalogue);
        model.addAttribute("prix", prixDTO.getPrixUnitaire());  // ➤ ajouter le prix

        model.addAttribute("quantite", 1);

        return "commande-form";
    }


    @PostMapping("/commande/valider")
    public String validerCommande(@RequestParam String codeProduit,
                                  @RequestParam int quantite,
                                  Model model) {

        // 🔥 1) Mise à jour du stock via le microservice Stock
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8082/api/stock/produits-stock/reduire/"
                + codeProduit + "/" + quantite;

        try {
            restTemplate.put(url, null);
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la mise à jour du stock : " + e.getMessage());
            return "commande-error";  // tu peux créer une page erreur
        }

        // 🔥 2) Retourner les infos à la vue
        model.addAttribute("codeProduit", codeProduit);
        model.addAttribute("quantite", quantite);

        return "commande-success";
    }


    // ➤ génération et téléchargement de la facture PDF
    @GetMapping("/commande/facture/{codeProduit}/{quantite}")
    public ResponseEntity<byte[]> genererFacture(
            @PathVariable String codeProduit,
            @PathVariable int quantite) {

        // 1. Récupérer les infos du produit (DTO prix)
        ProduitPrixDTO produit = catalogueService.getProduitPrix(codeProduit);

        // 2. Générer un PDF en mémoire
        byte[] pdfBytes = pdfService.genererFacturePDF(produit, quantite);

        // 3. Retourner le PDF au navigateur
        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition",
                        "attachment; filename=facture-" + codeProduit + ".pdf")
                .body(pdfBytes);
    }
}
