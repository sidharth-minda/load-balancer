package com.liftlab.loadBalancer.controllers;

import com.liftlab.loadBalancer.services.LoadBalancerConfigurationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoadBalancerConfigurationControllerTest {

    @InjectMocks
    private LoadBalancerConfigurationController configurationController;

    @Mock
    private LoadBalancerConfigurationService configurationService;

    @Test
    void testUpdateConfiguration() {
        String algorithm = "round-robin";
        String expectedResponse = "Load balancing algorithm switched to: round-robin";

        when(configurationService.updateConfiguration(algorithm)).thenReturn(expectedResponse);

        ResponseEntity<String> response = configurationController.updateConfiguration(algorithm);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(configurationService).updateConfiguration(algorithm);
    }
}

