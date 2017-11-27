package com.sr.stomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * This is the socket main Application
 *
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public static void main(final String[] args) {
		/* Main driver class */
		SpringApplication.run(Application.class, args);
	}
 
}
