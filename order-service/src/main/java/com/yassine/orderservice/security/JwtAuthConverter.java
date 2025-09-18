package com.yassine.orderservice.security;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter=new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractResourceRoles(jwt).stream()
        ).collect(Collectors.toSet());
        return new JwtAuthenticationToken(jwt, authorities,jwt.getClaim("preferred_username"));
    }

    private Collection<GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String , Object> realmAccess;
        Collection<String> roles;
        if(jwt.getClaim("realm_access")==null){
            return Set.of();
        }
        realmAccess = jwt.getClaim("realm_access");
        roles = (Collection<String>) realmAccess.get("roles");
        return roles.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toSet());
    }

}

/*
{
        "exp": 1757503893,
        "iat": 1757503593,
        "jti": "2ef89980-6053-445d-b90b-0aa50fac6ba0",
        "iss": "http://localhost:8080/realms/myrealm",
        "aud": "account",
        "sub": "21abf96b-93bc-46ac-bcb4-367c2963187d",
        "typ": "Bearer",
        "azp": "springboot-app",
        "session_state": "e0a92eec-da97-48be-a814-28fdc4308328",
        "acr": "1",
        "allowed-origins": [
        "http://localhost:8081"
        ],
        "realm_access": {
        "roles": [
        "default-roles-myrealm",
        "offline_access",
        "ADMIN",
        "uma_authorization"
        ]
        },
        "resource_access": {
        "account": {
        "roles": [
        "manage-account",
        "manage-account-links",
        "view-profile"
        ]
        }
        },
        "scope": "profile email",
        "sid": "e0a92eec-da97-48be-a814-28fdc4308328",
        "email_verified": false,
        "preferred_username": "yassine",
        "email": "yassine@test.com"
        }
        */
