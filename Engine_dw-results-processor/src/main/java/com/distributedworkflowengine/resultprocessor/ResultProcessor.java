package com.distributedworkflowengine.resultprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ResultProcessor 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(ResultProcessor.class, args);
    }
}
