package com.yassine.produitservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "produits")
public class Produit {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String libelle;
    private double prix;
    private int quantity;
    private String description;

    // Constructors
    public Produit() {}


    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}
