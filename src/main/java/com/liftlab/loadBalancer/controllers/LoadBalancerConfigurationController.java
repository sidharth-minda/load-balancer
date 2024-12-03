package com.liftlab.loadBalancer.controllers;

import com.liftlab.loadBalancer.services.LoadBalancerConfigurationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/load-balancer/configuration")
public class LoadBalancerConfigurationController {

    private final LoadBalancerConfigurationService configurationService;

    public LoadBalancerConfigurationController(LoadBalancerConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @PutMapping
    public ResponseEntity<String> updateConfiguration(@RequestParam String algorithm) {
        String responseMessage = configurationService.updateConfiguration(algorithm);
        return ResponseEntity.ok(responseMessage);
    }
}

