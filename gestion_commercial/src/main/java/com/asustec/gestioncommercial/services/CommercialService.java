package com.asustec.gestioncommercial.services;

import com.asustec.gestioncommercial.entities.CommandeGlobale;
import com.asustec.gestioncommercial.entities.ProduitPrix;
import com.asustec.gestioncommercial.repositories.CommandeGlobaleRepository;
import com.asustec.gestioncommercial.repositories.ProduitPrixRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommercialService {

    private final ProduitPrixRepository produitRepo;
    private final CommandeGlobaleRepository commandeRepo;

    // Constructeur pour injection de dépendances
    public CommercialService(ProduitPrixRepository produitRepo,
                             CommandeGlobaleRepository commandeRepo) {
        this.produitRepo = produitRepo;
        this.commandeRepo = commandeRepo;
    }

    public List<ProduitPrix> getAllProduits() {
        return produitRepo.findAll();
    }

    public ProduitPrix saveProduit(ProduitPrix p) {
        return produitRepo.save(p);
    }

    public CommandeGlobale saveCommande(CommandeGlobale c) {
        return commandeRepo.save(c);
    }
}
