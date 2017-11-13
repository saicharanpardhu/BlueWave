package com.distributedpipelilne.cloneagent.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.distributedpipelilne.cloneagent.message.CloneAgentProducer;
import com.distributedpipelilne.cloneagent.service.AgentService;

@RestController
@RequestMapping(value = "/v1.0")
public class CloneAgentController {
	
	@Autowired
	CloneAgentProducer agentProducer;
	
	
	@RequestMapping(value="/produce")
	public void produce()
	{
		agentProducer.sendMessage();
	}
	
	
	
	
	
	
	
	
	
	

}
