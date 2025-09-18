package com.yassine.orderservice.entity;

import com.yassine.orderservice.models.Client;
import com.yassine.orderservice.models.Produit;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String id;

    private String idUser;
    private String username;
    private String adresse;
    @Transient
    private Client client;

    private String idProduit;
    private String libelle;
    private Double prixUnit;
    private String description;
    @Transient
    private Produit produit;

    private int quantiteAchat;
    private double prixTotal;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Order(){}

    public Order(String idUser, String username, String adresse, String idProduit, String libelle, Double prixUnit, String description, int quantiteAchat) {
        this.idUser = idUser;
        this.username = username;
        this.adresse = adresse;
        this.idProduit = idProduit;
        this.libelle = libelle;
        this.prixUnit = prixUnit;
        this.description = description;
        this.quantiteAchat = quantiteAchat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getPrixUnit() {
        return prixUnit;
    }

    public void setPrixUnit(Double prixUnit) {
        this.prixUnit = prixUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantiteAchat() {
        return quantiteAchat;
    }

    public void setQuantiteAchat(int quantiteAchat) {
        this.quantiteAchat = quantiteAchat;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", idUser='" + idUser + '\'' +
                ", username='" + username + '\'' +
                ", adresse='" + adresse + '\'' +
                ", idProduit='" + idProduit + '\'' +
                ", libelle='" + libelle + '\'' +
                ", prixUnit=" + prixUnit +
                ", description='" + description + '\'' +
                ", quantiteAchat=" + quantiteAchat +
                ", prixTotal=" + prixTotal +
                ", createdAt=" + createdAt +
                '}';
    }
}
