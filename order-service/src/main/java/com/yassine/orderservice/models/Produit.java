package com.yassine.orderservice.models;

public class Produit {
    private String libelle;
    private double prix;
    private String description;

    private int quantity;

    public Produit(){

    }

    public Produit(String libelle, double prix, String description, int quantity) {
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
        this.quantity = quantity;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "libelle='" + libelle + '\'' +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
