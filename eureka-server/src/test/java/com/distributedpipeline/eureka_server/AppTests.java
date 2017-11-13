package com.distributedpipeline.eureka_server;


import static org.junit.Assert.*;

import javax.swing.text.html.parser.Entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppTests {
	
	 @LocalServerPort
	    private int port;

	    @Autowired
	    private TestRestTemplate testRestTemplate;

	    @Test
	    public void shouldStartEurekaServer() {
	        ResponseEntity<String> entity = this.testRestTemplate.getForEntity(
	                "http://localhost:" + this.port + "/eureka/apps", String.class);

	        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	    }
	    @Test
		public void notstartingServer() {
			ResponseEntity<String> entity = this.testRestTemplate.getForEntity("http://localhost:"+this.port +"/miss", String.class);
			assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
		}
}
