package com.liftlab.loadBalancer.exceptions;

public class ForwardingException extends RuntimeException {
    public ForwardingException(String message) {
        super(message);
    }
}
