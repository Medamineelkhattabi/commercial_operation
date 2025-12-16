package com.asustec.gestionstock.services;

import com.asustec.gestionstock.entities.ProduitStock;
import com.asustec.gestionstock.repositories.ProduitStockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final ProduitStockRepository stockRepo;

    public StockService(ProduitStockRepository stockRepo) {
        this.stockRepo = stockRepo;
    }

    public List<ProduitStock> getAllStocks() {
        return stockRepo.findAll();
    }

    public ProduitStock getByCode(String code) {
        return stockRepo.findByCodeProduit(code);
    }

    public ProduitStock saveStock(ProduitStock stock) {
        return stockRepo.save(stock);
    }

    public void decrementStock(String code, int qte) {
        ProduitStock ps = stockRepo.findByCodeProduit(code);
        if (ps != null && ps.getQuantiteDisponible() >= qte) {
            ps.setQuantiteDisponible(ps.getQuantiteDisponible() - qte);
            stockRepo.save(ps);
        }
    }
}
