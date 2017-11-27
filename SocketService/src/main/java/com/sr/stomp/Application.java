package com.sr.stomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
/**
 * This is the socket main Application
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class Application extends SpringBootServletInitializer {

	public static void main(final String[] args) {
		/* Main driver class */
		SpringApplication.run(Application.class, args);
	}
 
}