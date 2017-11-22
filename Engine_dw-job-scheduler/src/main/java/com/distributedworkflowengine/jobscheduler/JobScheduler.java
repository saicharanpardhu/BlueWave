package com.distributedworkflowengine.jobscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Job Scheduler
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class JobScheduler 
{
	 public static void main(String[] args) {
	        SpringApplication.run(JobScheduler.class, args);
	        
	        }
}
