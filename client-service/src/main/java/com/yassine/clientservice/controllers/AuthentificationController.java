package com.yassine.clientservice.controllers;

import com.yassine.clientservice.model.Client;
import com.yassine.clientservice.requests.AuthRequest;
import com.yassine.clientservice.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("authentification")
public class AuthentificationController {

    private final ClientService service;

    public AuthentificationController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody AuthRequest client) {
        String saved = service.create(client);
        return ResponseEntity.ok(saved);
    }

}
