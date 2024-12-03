package com.liftlab.loadBalancer.controllers;

import com.liftlab.loadBalancer.services.LoadBalancerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoadBalancerControllerTest {

    @InjectMocks
    private LoadBalancerController loadBalancerController;

    @Mock
    private LoadBalancerService loadBalancerService;

    @Test
    void testForwardRequest() {
        Object requestBody = new Object();
        CompletableFuture<String> mockResponse = CompletableFuture.completedFuture("Request forwarded successfully");
        when(loadBalancerService.forwardRequest(requestBody)).thenReturn(mockResponse);

        ResponseEntity<CompletableFuture<String>> response = loadBalancerController.forwardRequest(requestBody);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(loadBalancerService).forwardRequest(requestBody);
    }
}

