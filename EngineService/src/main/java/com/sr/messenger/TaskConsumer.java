
package com.sr.messenger;

import org.springframework.stereotype.Service;

import com.sr.domain.EngineModel;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;

@Service
public class TaskConsumer {
    
      private CountDownLatch latch = new CountDownLatch(1);

      public CountDownLatch getLatch() {
        return latch;
      }
//    @Autowired
//    TaskProducer taskProducer;
      
    @KafkaListener(topics = "task-response2", 
              containerFactory = "kafkaListenerContainerFactory")
            public void reportlistener(String message) {
                System.out.println(message); 
//                taskProducer.sendMessage(message.toUpperCase());
                latch.countDown();
            } 
    @KafkaListener(topics = "persistence45",
			containerFactory = "engineKafkaListenerContainerFactory")
			public void engineListener(EngineModel engine) {
//			System.out.println("sdfsdfsdfds");
			    System.out.println(engine);
			    latch.countDown();
			} 
}
    
    

//package com.sr.messenger;
//
//import java.util.concurrent.CountDownLatch;
//
//import org.springframework.kafka.annotation.KafkaListener;
//
//
//public class TaskConsumer {
//
//	private CountDownLatch latch = new CountDownLatch(1);
//
//	  public CountDownLatch getLatch() {
//	    return latch;
//	  }
//	
//	@KafkaListener(topics = "task-response1",
//			containerFactory = "kafkaListenerContainerFactory")
//	public void reportlistener(String response) {
//		System.out.println("sdfsdfsdfds");
//	    System.out.println(response);
//	    latch.countDown();
//	} 
//}
