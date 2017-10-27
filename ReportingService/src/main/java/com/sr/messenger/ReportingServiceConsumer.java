package com.sr.messenger; 
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import com.sr.domain.*;

@Service
public class ReportingServiceConsumer {
	
	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	
	
//	@KafkaListener(topics = "testrun4", 
//			  containerFactory = "reportKafkaListenerContainerFactory")
//			public void reportlistener(Report report) {
//			    System.out.println(report.getMessage());
//			    latch.countDown();
//			} 
 
}