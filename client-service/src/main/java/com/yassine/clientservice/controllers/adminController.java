package com.yassine.clientservice.controllers;

import com.yassine.clientservice.model.Client;
import com.yassine.clientservice.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class adminController {

    private final ClientService service;

    public adminController(ClientService service) {
        this.service = service;
    }

    @GetMapping("clients")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Client>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("clients/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Client> getById(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
