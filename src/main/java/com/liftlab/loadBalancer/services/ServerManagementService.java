package com.liftlab.loadBalancer.services;

import com.liftlab.loadBalancer.exceptions.LoadBalancerException;
import com.liftlab.loadBalancer.models.BackendServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ServerManagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerManagementService.class);

    private final Map<String, BackendServer> serverMap;

    public ServerManagementService() {
        this.serverMap = new ConcurrentHashMap<>();
    }

    public boolean addServer(String uri) {
        validateUri(uri);
        BackendServer server = new BackendServer(uri);
        serverMap.put(server.getId(), server);
        return true;
    }

    public void removeServer(String serverId) {
        if (serverId == null || serverId.isEmpty() || !serverMap.containsKey(serverId)) {
            throw new LoadBalancerException("Invalid server ID.");
        }
        serverMap.remove(serverId);
    }

    public List<BackendServer> getServers(Boolean healthy) {
        return serverMap.values()
                .stream()
                .filter(server -> healthy == null || server.isHealthy() == healthy)
                .toList();
    }

    private void validateUri(String uri) {
        if (uri == null || uri.isEmpty()) {
            throw new LoadBalancerException("Invalid server URI.");
        }
        boolean serverExists = serverMap.values()
                .stream()
                .anyMatch(server -> server.getUri().equalsIgnoreCase(uri));
        if (serverExists) {
            throw new LoadBalancerException("Server URI is already registered.");
        }
    }
}

