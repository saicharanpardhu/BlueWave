package com.distributedworkflowengine.stateinit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StateInitializer {

	public static void main(String[] args) {
		SpringApplication.run(StateInitializer.class, args);
	}
}
