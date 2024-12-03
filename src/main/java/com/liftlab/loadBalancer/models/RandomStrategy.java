package com.liftlab.loadBalancer.models;

import com.liftlab.loadBalancer.exceptions.LoadBalancerException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component("random")
public class RandomStrategy implements LoadBalancingStrategy{

    private final Random random = new Random();

    @Override
    public BackendServer selectServer(List<BackendServer> serverList) {
        if (serverList.isEmpty()) {
            throw new LoadBalancerException("No servers available for load balancing.");
        }
        return serverList.get(random.nextInt(serverList.size()));
    }
}
