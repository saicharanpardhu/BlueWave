package com.sr.report.controller;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.sr.report.model.ReportModel;
import com.sr.report.repository.*;

@RestController
@CrossOrigin(origins = "*")
public class ReportManagerController {
	@Autowired
	private ReportModelCRUDRepository reportModelCRUDRepository;
	
	/*
	perform checkout
	*/
	@GetMapping(value="/checkout/{jobId}")
	public ResponseEntity<List<ReportModel>> performCheckout(@PathVariable String jobId ) {
		List<ReportModel> list =  reportModelCRUDRepository.findByJobId(jobId);
		
		return new ResponseEntity<List<ReportModel>> (list, HttpStatus.CREATED);
	}
	

	
	
	
}
