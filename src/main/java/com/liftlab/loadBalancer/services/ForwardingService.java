package com.liftlab.loadBalancer.services;

import com.liftlab.loadBalancer.exceptions.ForwardingException;
import com.liftlab.loadBalancer.models.BackendServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ForwardingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForwardingService.class);

    private final HttpService httpService;

    public ForwardingService(HttpService httpService) {
        this.httpService = httpService;
    }

    public CompletableFuture<String> forwardRequest(BackendServer server, HttpMethod method, Object requestBody) {
        return httpService.sendRequest(server.getUri(), method, requestBody)
                .exceptionally(ex -> {
                    server.setHealthy(false);
                    throw new ForwardingException("Failed to forward the request to: " + server.getUri());
                });
    }
}
