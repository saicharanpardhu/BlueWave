package com.sr.report.messenger;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sr.report.model.ReportModel;
import com.sr.report.repository.*;
 

@Service
public class ReportingServiceConsumer {
	
	 
	  private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		
	    return latch;
	}

	@Autowired
	private ReportModelCRUDRepository reportModelCRUDRepository;
	
    @KafkaListener(topics="${kafka.topic.projectModeltopic}",
    		containerFactory = "projectModelKafkaListenerContainerFactory")
   public void processMessage(ReportModel report) {
    	
        
        latch.countDown();
        reportModelCRUDRepository.save(report);
    	System.out.println(report.toString());
        
        
   }
    
   
}
