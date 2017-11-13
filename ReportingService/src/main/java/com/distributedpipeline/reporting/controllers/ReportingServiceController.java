package com.distributedpipeline.reporting.controllers;
 
import java.sql.Timestamp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.ResponseBody;

import com.distributedpipeline.reporting.domain.Report;
import com.distributedpipeline.reporting.messenger.ReportingServiceProducer;
import com.distributedpipeline.reporting.services.ReportingService;
import com.distributedpipeline.reporting.utility.MethodLogger;
import com.jcabi.aspects.Loggable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

//<!----Controller-->

@Controller    
@RequestMapping(path="/v1.0/reportingservice/report")
@Api(value="Reporting Service", description="Reporting microservice of the Distributed Pipeline Project.")
public class ReportingServiceController {
	@Autowired
	private ReportingService reportService;  
	@Autowired 
	ReportingServiceProducer producer;
	//<--- Fetcher Methods --->   
		@ApiOperation(value = "Get a report by ID",response = Report.class)
		@ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved report"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
		@GetMapping(path="/{id:^[0-9]*$}") 
		@Loggable
		public @ResponseBody ResponseEntity<?> fetchReportByID (@PathVariable("id") Long id) { 
			try {  
				return new ResponseEntity<Report>(reportService.getReportById(id), HttpStatus.OK);
			}
			catch(Exception e) {  
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		} 
		
		public ResponseEntity<?> fetchReportByID_aroundBody (Long id) { 
			try {  
				return new ResponseEntity<Report>(reportService.getReportById(id), HttpStatus.OK);
			}
			catch(Exception e) {  
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		} 
		
		@ApiOperation(value = "Get a report by a userid",response = Iterable.class)
		@ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved report"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
		@GetMapping(path="/{name:[a-zA-Z]+}") 
		public @ResponseBody ResponseEntity<?> getReportByName (@PathVariable("name") String user) {  
			try {
				return new ResponseEntity<Iterable<Report>>(reportService.getReportByUser(user), HttpStatus.OK);
			}
			catch(Exception e) { 
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		} 
		@ApiOperation(value = "Get a report from a year",response = Iterable.class)
		@ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved report"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
		@GetMapping(path="/year/{year}")
		public @ResponseBody ResponseEntity<?> getReportByYear (@PathVariable("year") Integer year) {  
			try { 
				return new ResponseEntity<Iterable<Report>>(reportService.getReportByYear(year), HttpStatus.OK);
			}
			catch(Exception e) { 
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} 
		
	//<--- Save Method ---> 
		@ApiOperation(value = "Save a report",response = Report.class)
		@ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved report"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
		@PostMapping 
		public @ResponseBody ResponseEntity<?> saveNewReport (@RequestBody Report report){

			try {  
				report.setTimestamp(new Timestamp(System.currentTimeMillis()));
				Report savedReport = reportService.saveReport(report); 
				return new ResponseEntity<Report>(reportService.saveReport(report), HttpStatus.OK);
			}
			catch(Exception e) { 
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}
		
	//<--- Get All Methods --->  
		@ApiOperation(value = "Get all reports",response = Iterable.class)
		@ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved report"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
		@GetMapping
		public @ResponseBody ResponseEntity<?> getAllReports() { 
			try {
				return new ResponseEntity<Iterable<Report>>(reportService.getAllReports(), HttpStatus.OK);
			}
			catch(Exception e) { 
				return  new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}	
}
