package com.liftlab.loadBalancer.controllers;

import com.liftlab.loadBalancer.models.BackendServer;
import com.liftlab.loadBalancer.models.ServerRequest;
import com.liftlab.loadBalancer.services.ServerManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servers")
public class ServerController {

    private final ServerManagementService serverManagementService;

    public ServerController(ServerManagementService serverManagementService) {
        this.serverManagementService = serverManagementService;
    }

    @GetMapping
    public ResponseEntity<List<BackendServer>> getServers(@RequestParam(name = "isHealthy", required = false) Boolean healthy) {
        return ResponseEntity.ok(serverManagementService.getServers(healthy));
    }

    @PostMapping
    public ResponseEntity<Void> addServer(@RequestBody ServerRequest serverRequest) {
        serverManagementService.addServer(serverRequest.getUrl());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeServer(@PathVariable String id) {
        serverManagementService.removeServer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}


