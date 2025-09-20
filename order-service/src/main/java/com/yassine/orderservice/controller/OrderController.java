package com.yassine.orderservice.controller;

import com.yassine.orderservice.entity.Order;
import com.yassine.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("create-order")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> createOrder(
            @RequestParam String idProduit,
            @RequestParam int quantity,
            Authentication authentication) {

        try {
            Order order = orderService.createOrder(idProduit, quantity, authentication);
            return ResponseEntity.ok("Order affecté - Order ID: " + order.getId());

        } catch (RuntimeException ex) {
            String message;

            switch (ex.getMessage()) {
                case "Produit ou Client introuvable !":
                    message = "Erreur : Produit ou Client introuvable !";
                    break;
                case "service tombé, essaye plus tard":
                    message = "Service indisponible, réessayez plus tard.";
                    break;
                case "Stock insuffisant !":
                    message = "Stock insuffisant pour cette commande.";
                    break;
                default:
                    message = "Erreur inattendue : " + ex.getMessage();
            }

            return ResponseEntity.badRequest().body(message);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<Order> getAll(){
        return orderService.getAll();
    }
}
