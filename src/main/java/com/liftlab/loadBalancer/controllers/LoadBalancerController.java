package com.liftlab.loadBalancer.controllers;

import com.liftlab.loadBalancer.services.LoadBalancerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/load-balancer")
public class LoadBalancerController {

    private final LoadBalancerService loadBalancerService;

    public LoadBalancerController(LoadBalancerService loadBalancerService) {
        this.loadBalancerService = loadBalancerService;
    }

    @PostMapping("/requests")
    public ResponseEntity<CompletableFuture<String>> forwardRequest(@RequestBody(required = false) Object requestBody) {
        CompletableFuture<String> response = loadBalancerService.forwardRequest(requestBody);
        return ResponseEntity.ok(response);
    }
}

