package com.asustec.gestionvente.services;

import com.asustec.gestionvente.dto.ProduitPrixDTO;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] genererFacturePDF(ProduitPrixDTO produit, int quantite) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            Document document = new Document();
            PdfWriter.getInstance(document, baos);

            document.open();
            document.add(new Paragraph("FACTURE - GENERATION AUTOMATIQUE"));
            document.add(new Paragraph("-----------------------------------------------------"));
            document.add(new Paragraph("Produit : " + produit.getLibelle()));
            document.add(new Paragraph("Code : " + produit.getCode()));
            document.add(new Paragraph("Prix unitaire : " + produit.getPrixUnitaire() + " DH"));
            document.add(new Paragraph("Quantité : " + quantite));
            document.add(new Paragraph("-----------------------------------------------------"));
            document.add(new Paragraph("Total : " 
                    + (produit.getPrixUnitaire() * quantite) + " DH"));
            document.close();

            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erreur PDF : " + e.getMessage(), e);
        }
    }
}
