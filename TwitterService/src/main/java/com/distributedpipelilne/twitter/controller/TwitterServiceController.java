package com.distributedpipelilne.twitter.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
@Controller

public class TwitterServiceController {
	
	//load balancing of different instance created
	
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	public void getEmployee() throws RestClientException, IOException {
		
		ServiceInstance serviceInstance=loadBalancer.choose("employee-producer");
		
	}

}
