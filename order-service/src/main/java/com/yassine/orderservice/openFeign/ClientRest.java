package com.yassine.orderservice.openFeign;

import com.yassine.orderservice.models.Client;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-service")
public interface ClientRest {

    @GetMapping("/api/admin/get-client-username/{username}")
    @CircuitBreaker(name = "clientServiceCB", fallbackMethod = "getDefaultClient")
    Client getClient(@PathVariable String username);

    default Client getDefaultClient(String username, Throwable throwable){

        return new Client("defaultId","defaultUsername","defaultEmail","defaultAdresse");
    }
}
