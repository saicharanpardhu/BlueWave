package com.distributedpipeline.userpersistence.utility;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
 
//<!----Aspect for the logger -->
@Aspect 
@Component
public class MethodLogger { 
	private static Logger logger = LogManager.getLogger("MethodLogger.class");
	
	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
	    long start = System.currentTimeMillis();
	 
	    Object proceed = joinPoint.proceed();
	 
	    long executionTime = System.currentTimeMillis() - start;
	 
	    logger.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
	    return proceed;
	}
}