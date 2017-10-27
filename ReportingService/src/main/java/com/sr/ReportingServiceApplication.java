package com.sr;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

import com.sr.domain.Report;
import com.sr.messenger.ReportingServiceProducer;


//<-- Driver Class -->

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulServer
public class ReportingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportingServiceApplication.class, args);
	}
	 
}
