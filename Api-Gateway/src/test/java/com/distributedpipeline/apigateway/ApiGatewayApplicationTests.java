package com.distributedpipeline.apigateway;



import static org.junit.Assert.*;


import static org.junit.Assert.*;
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







@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApiGatewayApplicationTests {
	
	@Autowired
	EurekaDiscoveryClient eurekaClient;
	
	@LocalServerPort
	int port;
	
	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void contextLoads() {
			
	}
	
	@Test
	public void ignoredPatternMissing() {
		ResponseEntity<String> result = this.restTemplate.getForEntity("http://localhost:"+this.port +"/missing", String.class);
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}
	
	@Test
	public void forwardedPatternGoodWithPath() {
		ResponseEntity<String> result = this.restTemplate.getForEntity("http://localhost:"+this.port +"/info", String.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}


}

