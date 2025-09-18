package com.yassine.orderservice.service;

import com.yassine.orderservice.dto.DtoOrder;
import com.yassine.orderservice.entity.Order;
import com.yassine.orderservice.models.Client;
import com.yassine.orderservice.models.Produit;
import com.yassine.orderservice.openFeign.ClientRest;
import com.yassine.orderservice.openFeign.ProduitRest;
import com.yassine.orderservice.producer.Producer;
import com.yassine.orderservice.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRest clientRest;
    private final ProduitRest produitRest;

    private final Producer producer;

    public OrderService(OrderRepository orderRepository, ClientRest clientRest, ProduitRest produitRest, Producer producer) {
        this.orderRepository = orderRepository;
        this.clientRest = clientRest;
        this.produitRest = produitRest;
        this.producer = producer;
    }

    public Order createOrder(String idProduit, int quantityAchat, Authentication authentication) {
        Client client = clientRest.getClient(authentication.getName());
        Produit produit=produitRest.getProduit(idProduit);

        if (produit == null || client == null) {
            throw new RuntimeException("Produit ou Client introuvable !");
        }

        if (produit.getQuantity() < quantityAchat) {
            throw new RuntimeException("Stock insuffisant !");
        }

        // créer la commande
        Order order = new Order(
                client.getId(),
                client.getUsername(),
                client.getAdresse(),
                idProduit,
                produit.getLibelle(),
                produit.getPrix(),
                produit.getDescription(),
                quantityAchat
        );
        order.setId(UUID.randomUUID().toString());
        order.setPrixTotal(order.getPrixUnit() * quantityAchat);
        orderRepository.save(order);

        DtoOrder dtoOrder = new DtoOrder(order.getId(),"Order bien crée");
        producer.send(dtoOrder);

        return order;
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }
}
