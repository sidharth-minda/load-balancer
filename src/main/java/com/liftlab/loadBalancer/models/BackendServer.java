package com.liftlab.loadBalancer.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class BackendServer {
    private String id;
    private String uri;
    private boolean isHealthy;

    public BackendServer(String uri) {
        this.uri = uri;
        this.id = UUID.randomUUID().toString();
        this.isHealthy = true;
    }
}
