package com.yassine.clientservice.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Document(collection = "clients")
public class Client {

    @Id
    private String id;

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @Size(max = 255)
    private String adresse;

    public Client() {}

    public Client(String username, String email, String adrs) {
        this.username = username;
        this.email = email;
        this.adresse = adrs;
    }


    // getters / setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAdrs() { return adresse; }
    public void setAdrs(String adrs) { this.adresse = adrs; }
}
