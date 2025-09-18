package com.yassine.produitservice.controller;

import com.yassine.produitservice.entity.Produit;
import com.yassine.produitservice.service.ProduitService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    // GET all produits
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    // GET produit by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<Produit> getProduitById(@PathVariable String id) {
        return produitService.getProduitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create produit
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Produit createProduit(@RequestBody Produit produit) {
        return produitService.saveProduit(produit);
    }

    // PUT update produit
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Produit> updateProduit(@PathVariable String id, @RequestBody Produit produit) {
        try {
            return ResponseEntity.ok(produitService.updateProduit(id, produit));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE produit
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteProduit(@PathVariable String id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }
}
