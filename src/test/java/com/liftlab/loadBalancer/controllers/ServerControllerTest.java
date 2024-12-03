package com.liftlab.loadBalancer.controllers;

import com.liftlab.loadBalancer.models.BackendServer;
import com.liftlab.loadBalancer.models.ServerRequest;
import com.liftlab.loadBalancer.services.ServerManagementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServerControllerTest {

    @InjectMocks
    private ServerController serverController;

    @Mock
    private ServerManagementService serverManagementService;

    @Test
    void testGetServers() {
        List<BackendServer> mockServers = List.of(
                new BackendServer("http://localhost:8081"),
                new BackendServer("http://localhost:8082")
        );
        when(serverManagementService.getServers(null)).thenReturn(mockServers);

        ResponseEntity<List<BackendServer>> response = serverController.getServers(null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockServers, response.getBody());
        verify(serverManagementService).getServers(null);
    }

    @Test
    void testAddServer() {
        ServerRequest request = new ServerRequest("http://localhost:8083");

        ResponseEntity<Void> response = serverController.addServer(request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(serverManagementService).addServer(request.getUrl());
    }

    @Test
    void testRemoveServer() {
        String serverId = "server1";

        ResponseEntity<Void> response = serverController.removeServer(serverId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(serverManagementService).removeServer(serverId);
    }
}
