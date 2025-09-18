package com.yassine.clientservice.repository;

import com.yassine.clientservice.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<Client> findByUsername(String username);
}
