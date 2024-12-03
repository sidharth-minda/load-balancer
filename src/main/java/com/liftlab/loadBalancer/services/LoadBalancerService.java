package com.liftlab.loadBalancer.services;

import com.liftlab.loadBalancer.models.BackendServer;
import com.liftlab.loadBalancer.models.LoadBalancingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class LoadBalancerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadBalancerService.class);

    private final ServerManagementService serverManagementService;
    private final LoadBalancerConfigurationService configurationService;
    private final ForwardingService forwardingService;

    public LoadBalancerService(ServerManagementService serverManagementService, LoadBalancerConfigurationService configurationService, ForwardingService forwardingService) {
        this.serverManagementService = serverManagementService;
        this.configurationService = configurationService;
        this.forwardingService = forwardingService;
    }

    public CompletableFuture<String> forwardRequest(Object requestBody) {
        List<BackendServer> healthyServers = serverManagementService.getServers(true);
        LoadBalancingStrategy currentStrategy = configurationService.getCurrentStrategy();
        BackendServer backendServer = currentStrategy.selectServer(healthyServers);
        return forwardingService.forwardRequest(backendServer, HttpMethod.POST, requestBody);
    }
}
