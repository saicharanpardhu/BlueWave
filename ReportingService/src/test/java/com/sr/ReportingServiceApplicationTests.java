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
import com.sr.domain.Report; 

import junit.framework.TestCase; 

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReportingServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingServiceApplicationTests extends TestCase {
	
	@LocalServerPort
	private int port;
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	Report report;    
    
	@Before
    public void setUp() throws Exception {
		report = new Report(new Long(16), new Timestamp(System.currentTimeMillis()),"testUser", "Test Message","fatal");
		
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
        HttpEntity<Report> entity = new HttpEntity<Report>(report, headers);
        System.out.println(createURLWithPort("/report"));
        ResponseEntity<Report> response = restTemplate.exchange(
                createURLWithPort("/report"),
                HttpMethod.POST, entity, Report.class); 
        assertNotNull(response);
        Report actual = response.getBody();
        System.out.println(actual.getId() + " " + actual.getMessage());
    }
    
    @Test
    public void testGetReportById() throws Exception {  
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);  
        HttpEntity<String> testEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<Report> response = restTemplate.exchange(
                createURLWithPort("/report/5"),
                HttpMethod.GET, testEntity, Report.class);  
        Report actual = response.getBody();
        assertEquals(actual,report);	
    }
     
    
    @Test
    public void testList() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/report"),
                HttpMethod.GET, entity, String.class);
        assertNotNull(response);
    } 
	

}