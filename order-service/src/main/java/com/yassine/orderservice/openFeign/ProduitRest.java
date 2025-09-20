package com.yassine.orderservice.openFeign;

import com.yassine.orderservice.models.Produit;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produit-service")
public interface ProduitRest {

    @GetMapping("/api/produits/{id}")
    @CircuitBreaker(name = "produitServiceCB", fallbackMethod = "getDefaultProduit")
    Produit getProduit(@PathVariable String id);

    default Produit getDefaultProduit(String id, Throwable throwable){
        return new Produit("defaultProduit",0.0,"defaultDescription",0);
    }

}
