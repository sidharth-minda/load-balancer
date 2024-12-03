package com.liftlab.loadBalancer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@Service
public class HttpService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpService.class);

    private final HttpClient httpClient;

    public HttpService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public CompletableFuture<String> sendRequest(String url, HttpMethod method, Object requestBody) {
        try {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json");
            if (requestBody != null) {
                requestBuilder.method(method.name(), HttpRequest.BodyPublishers.ofString(requestBody.toString()));
            } else {
                requestBuilder.method(method.name(), HttpRequest.BodyPublishers.noBody());
            }
            HttpRequest request = requestBuilder.build();
            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(response -> {
                        if (response.statusCode() >= 200 && response.statusCode() < 300) {
                            return response.body();
                        } else {
                            LOGGER.error("Server responded with error: {} - {}", response.statusCode(), response.body());
                            throw new RuntimeException("Server failed to respond with error code: " + response.statusCode());
                        }
                    })
                    .exceptionally(e -> {
                        LOGGER.error("Request failed: {}", e.getMessage(), e);
                        throw new RuntimeException("Request failed: " + e.getMessage(), e);
                    });

        } catch (Exception e) {
            LOGGER.error("Unexpected error while sending request: {}", e.getMessage(), e);
            CompletableFuture<String> failedFuture = new CompletableFuture<>();
            failedFuture.completeExceptionally(new RuntimeException("Unexpected error during the request"));
            return failedFuture;
        }
    }
}


