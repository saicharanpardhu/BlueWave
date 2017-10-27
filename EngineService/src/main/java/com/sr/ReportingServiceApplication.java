package com.sr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;


//<-- Driver Class -->

@SpringBootApplication

//@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})

@EnableDiscoveryClient
@EnableZuulServer
public class ReportingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportingServiceApplication.class, args);
	}
	 
}
