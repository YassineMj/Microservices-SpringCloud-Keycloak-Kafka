package com.yassine.orderservice.openFeign;

import com.yassine.orderservice.models.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-service")
public interface ClientRest {

    @GetMapping("/api/admin/get-client-username/{username}")
    Client getClient(@PathVariable String username);
}
