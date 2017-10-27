//package com.sr;
//
//import java.sql.Timestamp; 
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.context.embedded.LocalServerPort;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner; 
//import com.sr.domain.EngineModel; 
//
//import junit.framework.TestCase; 
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ReportingServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class ReportingServiceApplicationTests extends TestCase {
//	
//	@LocalServerPort
//	private int port;
//	TestRestTemplate restTemplate = new TestRestTemplate();
//	HttpHeaders headers = new HttpHeaders();
//	EngineModel report;    
//    
////	@Before
////    public void setUp() throws Exception {
////		report = new EngineModel(new Long(5), new Timestamp(System.currentTimeMillis()),"testUser", "Test Message","fatal");
////		
////    }
//	
//    private String createURLWithPort(String uri) {
//        return "http://localhost:" + port + "/v1.0/reportingservice" + uri;
//    }
//    
//    @After
//    public void tearDown() throws Exception {
//    }
//    
//    @Test
//    public void testSaveReport() throws Exception {  
//    	HttpHeaders headers = new HttpHeaders();
//    	headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<EngineModel> entity = new HttpEntity<EngineModel>(report, headers);
//        System.out.println(createURLWithPort("/report"));
//        ResponseEntity<EngineModel> response = restTemplate.exchange(
//                createURLWithPort("/report"),
//                HttpMethod.POST, entity, EngineModel.class); 
//        assertNotNull(response); 
//    }
//    
//    @Test
//    public void testGetReportById() throws Exception {   
//        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//        ResponseEntity<EngineModel> response = restTemplate.exchange(
//                createURLWithPort("/report/5"),
//                HttpMethod.GET, entity, EngineModel.class);
//        EngineModel actual = response.getBody();
//        assertEquals(actual.getId(),report.getId());	
//    }
//    
//    @Test
//    public void testGetReportByYear() throws Exception {   
//    	HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//        ResponseEntity<Iterable> response = restTemplate.exchange(
//                createURLWithPort("/report/year/2017"),
//                HttpMethod.GET, entity, Iterable.class); 
//        assertNotNull(response);	
//    }
//    
//    @Test
//    public void testGetReportByUserid() throws Exception {    
//    	HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//        ResponseEntity<Iterable> response = restTemplate.exchange(
//                createURLWithPort("/report/akshaydv"),
//                HttpMethod.GET, entity, Iterable.class); 
//        assertNotNull(response);
//    }
//     
//    
//    @Test
//    public void testList() throws Exception {
//        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//        ResponseEntity<String> response = restTemplate.exchange(
//                createURLWithPort("/report"),
//                HttpMethod.GET, entity, String.class);
//        assertNotNull(response);
//    }  
//
//}