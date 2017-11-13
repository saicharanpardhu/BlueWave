package com.distributedpipeline.apigateway;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.distributedpipeline.filters.ErrorFilter;
import com.distributedpipeline.filters.PostFilter;
import com.distributedpipeline.filters.PreFilter;
import com.distributedpipeline.filters.RouteFilter;



@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
//this the main class for api-gateway
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	//accept the request from the client
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	
	
	//gives back the response to the client that is been sent by the microservice	
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	//routes to given path of the micro service
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
}
