package com.distributedpipeline.userpersistence.utility;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType; 
import java.lang.annotation.RetentionPolicy;

//<!----Custom Annotation for automatically sending logs -->
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {
 
}