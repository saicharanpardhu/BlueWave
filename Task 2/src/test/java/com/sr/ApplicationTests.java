package com.sr;

import java.sql.Timestamp; 

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;  

import junit.framework.TestCase; 

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests extends TestCase {
	
	@LocalServerPort
	private int port;
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	String message;    
    
	@Before
    public void setUp() throws Exception {
		message = "Hello World";
		
    }
	
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/v1.0/reportingservice" + uri;
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testSaveReport() throws Exception {  
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(message, headers);
        System.out.println(createURLWithPort("/report"));
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/report"),
                HttpMethod.POST, entity, String.class); 
        assertNotNull(response); 
    } 

}