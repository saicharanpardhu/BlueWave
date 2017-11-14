package com.sr.stomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * This is the socket main Application
 *
 */
@SpringBootApplication
public class App  extends SpringBootServletInitializer 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }
}
