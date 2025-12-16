package com.asustec.gestionstock.repositories;

import com.asustec.gestionstock.entities.ProduitStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitStockRepository extends JpaRepository<ProduitStock, Long> {
    ProduitStock findByCodeProduit(String codeProduit);
}
