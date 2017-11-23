package com.distributedworkflowengine.taskscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class taskScheduler 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(taskScheduler.class, args);
    }
}
