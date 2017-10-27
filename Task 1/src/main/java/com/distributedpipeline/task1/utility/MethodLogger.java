package com.distributedpipeline.task1.utility;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.distributedpipeline.task1.messenger.TaskProducer;
 

@Aspect 
@Component
public class MethodLogger {
	@Autowired
	TaskProducer taskProducer;
	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
	    long start = System.currentTimeMillis();
	 
	    Object proceed = joinPoint.proceed();
	 
	    long executionTime = System.currentTimeMillis() - start;
	 
	    taskProducer.sendReport(joinPoint.getSignature() + " executed in " + executionTime + "ms");
	    return proceed;
	}
}