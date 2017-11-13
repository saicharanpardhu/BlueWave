package com.distributedpipeline.task1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distributedpipeline.task1.messenger.TaskProducer;
import com.distributedpipeline.task1.utility.LogExecutionTime;

//Service implementation

@Service
public class Task1ServiceImpl implements Task1Service{
	
	@Autowired
	TaskProducer taskProducer;
	
	@Override
	@LogExecutionTime
	public String executeTask1(){
		String message = "Hello World from task 1";
		taskProducer.sendMessage(message);
		return message;
	}
	
}
