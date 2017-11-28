package com.distributedpipelilne.agent.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.distributedpipelilne.agent.message.AgentProducer;
import com.distributedpipelilne.agent.service.AgentService;

@RestController
@RequestMapping(value = "/v1.0")
public class AgentController {
	
	@Autowired
	AgentProducer agentProducer;
	
	
//	@RequestMapping(value="/produce")
//	public void produce()
//	{
//		agentProducer.sendMessage();
//	}
	
	
	
	
	
	
	
	
	
	

}
