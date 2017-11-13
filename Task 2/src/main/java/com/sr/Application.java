package com.sr;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
 


//<-- Driver Class -->

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableZuulServer
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	 
}
