package com.distributedpipelilne.buildAgent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * Main Driver class for the Agent
 *
 */

@SpringBootApplication
public class BuildAgent {

	public static void main(String[] args) {
		SpringApplication.run(BuildAgent.class, args);
	}
}