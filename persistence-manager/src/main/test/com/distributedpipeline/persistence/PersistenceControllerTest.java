package com.distributedpipeline.persistence;


import java.util.ArrayList;
import java.util.List;

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

import com.distributedpipeline.persistence.PersistenceManagerApplication;
import com.distributedpipeline.persistence.domain.Tasks;
import com.distributedpipeline.persistence.domain.Workflow;

import junit.framework.TestCase; 

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersistenceManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersistenceControllerTest extends TestCase {
	
	@LocalServerPort
	private int port;
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	Workflow workflow;
	
	String taskType = "null";
	String status= "null";
	String[] dependsOn = {"a","b"};
	String[] output = {"c"};
	String[] input = {"d"};
	
	Tasks task = new Tasks();
	

	List<Tasks> listTask = new ArrayList<Tasks>();
	
	@Before
    public void setUp() throws Exception {
		listTask.add(task);
		workflow = new Workflow();
		
    }
	private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/v1.0/persistence" + uri;
    }
	
	@After
    public void tearDown() throws Exception {
	}
	
	 @Test
	 public void testSaveReport() throws Exception {  
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<Workflow> entity = new HttpEntity<Workflow>(workflow, headers);
	        System.out.println(createURLWithPort("/workflow"));
	        ResponseEntity<Workflow> response = restTemplate.exchange(
	                createURLWithPort("/workflow/save"),
	                HttpMethod.POST, entity, Workflow.class); 
	        assertNotNull(response); 
	    }
	 
	 @Test
	  public void testGetWorkFlowByName() throws Exception {   
	        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	        ResponseEntity<Workflow> response = restTemplate.exchange(
	                createURLWithPort("/workflow/CAL4"),
	                HttpMethod.GET, entity, Workflow.class);
	        Workflow actual = response.getBody();
	        assertEquals(actual.getWorkFlowName(), workflow.getWorkFlowName());	
	    }
	 @Test
	  public void testGetWorkFlow() throws Exception {   
		 HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	                createURLWithPort("/workflow"),
	                HttpMethod.GET, entity, String.class);
	       // Workflow actual = response.getBody();
	        assertNotNull(response);	
	    }
	
	

}
