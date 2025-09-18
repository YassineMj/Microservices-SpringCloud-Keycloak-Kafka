package com.yassine.produitservice.service;

import com.yassine.produitservice.entity.Produit;
import com.yassine.produitservice.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Optional<Produit> getProduitById(String id) {
        return produitRepository.findById(id);
    }

    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(String id, Produit produitDetails) {
        return produitRepository.findById(id).map(produit -> {
            produit.setLibelle(produitDetails.getLibelle());
            produit.setPrix(produitDetails.getPrix());
            produit.setQuantity(produitDetails.getQuantity());
            produit.setDescription(produitDetails.getDescription());
            return produitRepository.save(produit);
        }).orElseThrow(() -> new RuntimeException("Produit not found with id " + id));
    }

    public void deleteProduit(String id) {
        produitRepository.deleteById(id);
    }
}