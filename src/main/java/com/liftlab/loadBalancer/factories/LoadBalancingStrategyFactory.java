package com.liftlab.loadBalancer.factories;

import com.liftlab.loadBalancer.exceptions.LoadBalancerException;
import com.liftlab.loadBalancer.models.LoadBalancingStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoadBalancingStrategyFactory {

    private final Map<String, LoadBalancingStrategy> strategyMap;

    public LoadBalancingStrategyFactory(Map<String, LoadBalancingStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    public LoadBalancingStrategy getStrategy(String algorithm) {
        LoadBalancingStrategy strategy = strategyMap.get(algorithm.toLowerCase());
        if(strategy == null)
            throw new LoadBalancerException("Invalid algorithm specified.");
        return strategy;
    }
}
