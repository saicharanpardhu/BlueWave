package com.distributedpipeline.persistence;


import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
import com.distributedpipeline.persistence.domain.TaskLibrary;
import com.distributedpipeline.persistence.domain.Tasks;
import com.distributedpipeline.persistence.domain.Workflow;
import com.distributedpipeline.persistence.service.PersistenceService;

import junit.framework.TestCase; 

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersistenceManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersistenceControllerTest extends TestCase {
	
	@Mock
	private PersistenceService persistenceservice;
	
	@LocalServerPort
	private int port;
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	
	  /*------------------ Model for task---------------------------------*/
      String[] canViewUser = {"Anonymous"};
	  String[] canExecuteUser = {"Anonymous1"};
	  String[] depends_on = {};
	  String[] input = {"https://github.com/eugenp/tutorials/tree/master/spring-boot"};
	  String[] output = {};
	  
	  Tasks task = new Tasks("clone","completed",depends_on,input,output);
	  
	  /*------------------Model for Workflow -----------------------------*/
	  Workflow workflow;
	  HashMap<String,Tasks> taskMap = new HashMap<String,Tasks>();
      @Before
      public void setUp() throws Exception {
    	  workflow=new Workflow("demo","stackroute",canViewUser,canExecuteUser,"done",taskMap);
    	  }
      
      
      String taskName = "package";
  	  String inputType= "null";
  	  String outputType= "null";
   	  String taskCommand= "mvn-package";
   	  TaskLibrary tasklibrary= new TaskLibrary(taskName,inputType, outputType,taskCommand);
      
      
      
      private String createURLWithPort(String uri) {
	              return "http://localhost:" + port + uri;
      }          
	  @After
	  public void tearDown() throws Exception {
	  }
	
	  @Test
	  public void testSaveWorkflow() throws Exception {  
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<Workflow> entity = new HttpEntity<Workflow>(workflow, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	                createURLWithPort("v1.0/persistence/workflow"),
	                HttpMethod.POST, entity, String.class); 
	        assertNotNull(response);
	        String actual = response.getBody();
	        assertEquals("Workflow with the same Workflow name already exists",actual);
	    }
	 
	  @Test
	  public void testGetWorkFlowByName() throws Exception {   
	        HttpEntity<Workflow> entity = new HttpEntity<Workflow>(workflow, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	                createURLWithPort("v1.0/persistence/workflow/demo"),
	                HttpMethod.GET, entity, String.class);
	        assertNotNull(response);
	    }
	 @Test
	  public void testGetWorkFlow() throws Exception {   
		 HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	                createURLWithPort("v1.0/persistence/workflow"),
	                HttpMethod.GET, entity, String.class);
	        assertNotNull(response);	
	    }
	 @Test
     public void testUpdateWorkflow() throws Exception {
       HttpEntity<Workflow> entity = new HttpEntity<Workflow>(workflow, headers);
       ResponseEntity<String> response = restTemplate.exchange(
               createURLWithPort("/v1.0/persistence/workflow"),
               HttpMethod.PUT, entity, String.class);
       assertNotNull(response);
       String actual = response.getBody();
       assertEquals("workflow updated",actual);
 }
	 
	 @Test
     public void testdeleteWorkflow() throws Exception {
         HttpEntity<Workflow> entity = new HttpEntity<Workflow>(workflow, headers);
         ResponseEntity<String> response = restTemplate.exchange(
                 createURLWithPort("/v1.0/persistence/workflow/demo"),
                 HttpMethod.DELETE, entity, String.class);
         assertNotNull(response);
         String actual = response.getBody();
         assertEquals("Deleted succesfully",actual);
   }
	 
	 @Test
	  public void testgetTaskLibrary() throws Exception {   
		 HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	                createURLWithPort("v1.0/persistence/task"),
	                HttpMethod.GET, entity, String.class);
	        assertNotNull(response);	
	    }

	 @Test
	  public void testgetTaskLibraryByName() throws Exception {   
	        HttpEntity<Workflow> entity = new HttpEntity<Workflow>(null, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	                createURLWithPort("v1.0/persistence/task/clone"),
	                HttpMethod.GET, entity, String.class);
	        assertNotNull(response);
	    }
	 
	 @Test
	  public void testaddTaskLibrary() throws Exception {  
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<TaskLibrary> entity = new HttpEntity<TaskLibrary>(tasklibrary, headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	                createURLWithPort("v1.0/persistence/task"),
	                HttpMethod.POST, entity, String.class); 
	        assertNotNull(response);
	        String actual = response.getBody();
	        assertNotNull(response);
	    }
	 
	 @Test
     public void testupdateTaskLibrary() throws Exception {
       HttpEntity<TaskLibrary> entity = new HttpEntity<TaskLibrary>(tasklibrary, headers);
       ResponseEntity<String> response = restTemplate.exchange(
               createURLWithPort("/v1.0/persistence/task"),
               HttpMethod.PUT, entity, String.class);
       assertNotNull(response);
      
 }
	 @Test
     public void testdeleteTaskLibrary() throws Exception {
         HttpEntity<TaskLibrary> entity = new HttpEntity<TaskLibrary>(tasklibrary, headers);
         ResponseEntity<String> response = restTemplate.exchange(
                 createURLWithPort("/v1.0/persistence/task/package"),
                 HttpMethod.DELETE, entity, String.class);
         assertNotNull(response);
         String actual = response.getBody();
         assertEquals("Deleted succesfully",actual);
   }
	 
	 
     @Test
     public void testDeleteUser() throws Exception {
         HttpEntity<Workflow> entity = new HttpEntity<Workflow>(workflow, headers);
         ResponseEntity<String> response = restTemplate.exchange(
                 createURLWithPort("/v1.0/persistence/workflow/demo"),
                 HttpMethod.DELETE, entity, String.class);
         assertNotNull(response);
         String actual = response.getBody();
         assertEquals("Deleted succesfully",actual);
   }

	
	

}