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

        Order order = orderService.createOrder(idProduit, quantity, authentication);

        return ResponseEntity.ok("Payment affecté ✅ - Order ID: " + order.getId());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<Order> getAll(){
        return orderService.getAll();
    }
}
