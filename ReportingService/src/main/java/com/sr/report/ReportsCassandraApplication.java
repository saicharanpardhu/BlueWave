package com.sr.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ReportsCassandraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportsCassandraApplication.class, args);
	}
}
