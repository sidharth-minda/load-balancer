package com.liftlab.loadBalancer.models;

import java.util.List;

public interface LoadBalancingStrategy {
    BackendServer selectServer(List<BackendServer> serverList);
}
