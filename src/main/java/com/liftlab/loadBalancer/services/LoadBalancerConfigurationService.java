package com.liftlab.loadBalancer.services;

import com.liftlab.loadBalancer.factories.LoadBalancingStrategyFactory;
import com.liftlab.loadBalancer.models.LoadBalancingStrategy;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class LoadBalancerConfigurationService {

    @Getter
    private LoadBalancingStrategy currentStrategy;
    private final LoadBalancingStrategyFactory strategyFactory;

    public LoadBalancerConfigurationService(LoadBalancingStrategyFactory strategyFactory, LoadBalancingStrategy defaultStrategy) {
        this.strategyFactory = strategyFactory;
        this.currentStrategy = defaultStrategy;
    }

    public String updateConfiguration(String algorithm) {
        currentStrategy = strategyFactory.getStrategy(algorithm);
        return "Load balancing algorithm switched to: " + algorithm;
    }

}

