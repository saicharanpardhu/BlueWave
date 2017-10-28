package com.sr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sr.exceptions.ReportNotFoundException;
import com.sr.messenger.TaskProducer;

//Service implementation

@Service
public class Task1ServiceImpl implements Task1Service{
	
	@Autowired
	TaskProducer taskProducer;
	public String executeTask1() throws ReportNotFoundException{
		String message = "Hello World";
		taskProducer.sendMessage(message);
		return message;
	}
	
}
