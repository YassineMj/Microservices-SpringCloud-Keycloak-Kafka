package com.yassine.produitservice.repository;

import com.yassine.produitservice.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, String> {
}
