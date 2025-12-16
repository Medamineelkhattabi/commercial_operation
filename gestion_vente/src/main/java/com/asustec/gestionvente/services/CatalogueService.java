package com.asustec.gestionvente.services;

import com.asustec.gestionvente.dto.ProduitCatalogueDTO;
import com.asustec.gestionvente.dto.ProduitPrixDTO;
import com.asustec.gestionvente.dto.ProduitStockDTO;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CatalogueService {

    private final RestTemplate restTemplate = new RestTemplate();

    // URL des microservices	
    private final String COMMERCIAL_URL = "http://localhost:8081/api/commercial/produits";
    private final String STOCK_URL = "http://localhost:8082/api/stock/produits";

    // ------------------------------------------------------------------------------------
    // 1) Récupération du catalogue fusionné Commercial + Stock
    // ------------------------------------------------------------------------------------
    public List<ProduitCatalogueDTO> getCatalogueProduits() {

        ProduitPrixDTO[] produitsPrix =
                restTemplate.getForObject(COMMERCIAL_URL, ProduitPrixDTO[].class);

        ProduitStockDTO[] produitsStock =
                restTemplate.getForObject(STOCK_URL, ProduitStockDTO[].class);

        List<ProduitCatalogueDTO> catalogue = new ArrayList<>();

        for(ProduitPrixDTO prix : produitsPrix) {

            int qte = 0;

            for (ProduitStockDTO st : produitsStock) {
                if (st.getCodeProduit().equals(prix.getCode())) {
                    qte = st.getQuantiteDisponible();
                }
            }

            catalogue.add(new ProduitCatalogueDTO(
                    prix.getCode(),
                    prix.getLibelle(),
                    prix.getPrixUnitaire(),
                    qte
            ));
        }

        return catalogue;
    }

    // ------------------------------------------------------------------------------------
    // 2) Récupération d’un produit précis (prix uniquement)
    // ------------------------------------------------------------------------------------
    public ProduitPrixDTO getProduitPrix(String code) {

        ProduitPrixDTO[] produitsPrix =
                restTemplate.getForObject(COMMERCIAL_URL, ProduitPrixDTO[].class);

        for (ProduitPrixDTO p : produitsPrix) {
            if (p.getCode().equals(code)) {
                return p;
            }
        }

        return null;
    }
}
