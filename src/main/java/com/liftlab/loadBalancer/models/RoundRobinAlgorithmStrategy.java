package com.liftlab.loadBalancer.models;

import com.liftlab.loadBalancer.exceptions.LoadBalancerException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Primary
@Component("round-robin")
public class RoundRobinAlgorithmStrategy implements LoadBalancingStrategy{

    private final AtomicInteger currentIndex = new AtomicInteger(0);

    @Override
    public BackendServer selectServer(List<BackendServer> serverList) {
        if(serverList.isEmpty()) {
            throw new LoadBalancerException("No servers available for load balancing.");
        }
        return serverList.get(currentIndex.getAndUpdate(index -> (index + 1) % serverList.size()));
    }
}
