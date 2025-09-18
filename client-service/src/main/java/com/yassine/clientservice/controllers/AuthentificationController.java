package com.yassine.clientservice.controllers;

import com.yassine.clientservice.requests.AuthRequest;
import com.yassine.clientservice.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("mySession")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }

}
