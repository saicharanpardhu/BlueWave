package com.distributedpipeline.engine;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EngineApplicationStartup 
implements ApplicationListener<ApplicationReadyEvent> {
 
  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
 
    //<<-------Get State from Redis------->>
	  
    return;
  }
 
} 