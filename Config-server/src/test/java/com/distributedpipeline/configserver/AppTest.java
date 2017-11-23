package com.distributedpipeline.configserver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.config.server.environment.EnvironmentController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppTest {

	
    @Autowired
    private EnvironmentController controller;
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }
    
    // Test case to check that config server starts properly

    @Test
    public void shouldStartConfigServer() {
        ResponseEntity<String> entity = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.port + "/application/default", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
    
    // Test case to check that config server works for appropriate URLs
    
    @Test
    public void shouldNotStartConfigServer() {
        ResponseEntity<String> entity = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.port + "/miss", String.class);
        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
    }

}
