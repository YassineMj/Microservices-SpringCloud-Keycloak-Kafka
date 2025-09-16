package com.yassine.clientservice.services;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class KeycloakUserService {
    private final Keycloak keycloak;
    private final String realm = "myrealm"; // Nom de realm


    public KeycloakUserService() {
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080")
                .realm("master") // realm admin
                .clientId("admin-cli")
                .username("yassine")   // admin keycloak
                .password("yassine")
                .build();
    }
    public String registerUser(String username, String email, String password) {
        // Créer un utilisateur
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEmail(email);
        user.setEnabled(true);
        // Mot de passe
        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setTemporary(false);
        cred.setType(CredentialRepresentation.PASSWORD);
        cred.setValue(password);
        user.setCredentials(Collections.singletonList(cred));
        // Création utilisateur
        var response = keycloak.realm(realm).users().create(user);
        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
        // Assigner rôles
        UserResource userResource = keycloak.realm(realm).users().get(userId);
        RoleRepresentation role = keycloak.realm(realm).roles().get("USER").toRepresentation();
        userResource.roles().realmLevel().add(List.of(role));

        return userId;

    }
}
