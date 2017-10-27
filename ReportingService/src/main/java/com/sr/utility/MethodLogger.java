package com.sr.utility;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
 

@Aspect
public class MethodLogger {
	private static Logger logger = LogManager.getLogger(MethodLogger.class);
  @Around("execution(* *(..)) && @annotation(Loggable)")
  public Object around(ProceedingJoinPoint point) throws Throwable {
    long start = System.currentTimeMillis();
    Object result = point.proceed();
    logger.info( 
    		MethodSignature.class.cast(point.getSignature()).getMethod().getName() + 
    	      point.getArgs() + 
    	      result + 
    	      (System.currentTimeMillis() - start)
    );
    return result;
  }
}