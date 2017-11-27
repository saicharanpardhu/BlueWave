package com.distributedpipeline.persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
/**
 * Owner : Akshay vaibhav
 * Contributor : Prashant Maroti
 *  
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class PersistenceManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersistenceManagerApplication.class, args);
		
		}
}
