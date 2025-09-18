package com.yassine.clientservice.services;


import com.yassine.clientservice.entity.Client;
import com.yassine.clientservice.repository.ClientRepository;
import com.yassine.clientservice.requests.AuthRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {
    private final ClientRepository repo;
    private final KeycloakUserService keycloakUserService;

    public ClientService(ClientRepository repo, KeycloakUserService keycloakUserService) {
        this.repo = repo;
        this.keycloakUserService = keycloakUserService;
    }

    public String create(AuthRequest client) {

        if (client.getEmail() != null && repo.existsByEmail(client.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé");
        }
        String userId=keycloakUserService.registerUser(client.getUsername(),client.getEmail(),client.getPassword());
        Client clientEntity = new Client();
        clientEntity.setId(userId);
        clientEntity.setUsername(client.getUsername());
        clientEntity.setEmail(client.getEmail());
        clientEntity.setadresse(client.getAdresse());
        repo.save(clientEntity);

        return clientEntity.getId();
    }

    public Optional<Client> findById(String id) {
        return repo.findById(id);
    }

    public Optional<Client> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    public List<Client> findAll() {
        return repo.findAll();
    }
}
