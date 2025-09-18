package com.yassine.orderservice.openFeign;

import com.yassine.orderservice.models.Produit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produit-service")
public interface ProduitRest {

    @GetMapping("/api/produits/{id}")
    Produit getProduit(@PathVariable String id);
}
