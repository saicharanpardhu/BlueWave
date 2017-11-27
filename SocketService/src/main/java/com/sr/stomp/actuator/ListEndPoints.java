package com.sr.stomp.actuator;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;
import java.util.List;  

import org.springframework.beans.factory.annotation.Autowired; 

@Component
public class ListEndPoints extends AbstractEndpoint<List<Endpoint>>{

	private List<Endpoint> endpoints;

    @Autowired
    public ListEndPoints(List<Endpoint> endpoints) {
        super("showEndpoints");
        this.endpoints = endpoints;
    }

    public List<Endpoint> invoke() {
        return this.endpoints;
    }
}