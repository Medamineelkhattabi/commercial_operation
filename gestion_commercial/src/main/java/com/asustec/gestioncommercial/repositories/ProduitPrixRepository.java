package com.asustec.gestioncommercial.repositories;

import com.asustec.gestioncommercial.entities.ProduitPrix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitPrixRepository extends JpaRepository<ProduitPrix, Long> {
    ProduitPrix findByCode(String code);
}
