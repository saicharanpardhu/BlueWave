package in.stackroute.dw.messaging;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.stackroute.dw.domain.Agent;
import in.stackroute.dw.domain.Model;
import in.stackroute.dw.domain.Task;

import org.springframework.kafka.core.KafkaTemplate;


@Service
public class TaskSchedulerProducer {
	@Autowired
	TaskSchedulerConsumer consumer;
	@Autowired
	Agent agent;
	@Autowired
	private KafkaTemplate<String, Agent> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate2;
	 
	private Map<String,Task> mp;
	
	public void sendMessage(Agent agent) {
		
//	for ( Map.Entry<String, Model> entry : hash.entrySet()) {
//		    String key = entry.getKey();
//		    Tab tab = entry.getValue();
//		    // do something with key and/or tab
//		}
//		
	
		for (Map.Entry<String,Task> entry : consumer.getModel().getListOfTasks().entrySet()) 
//		for (Map.Entry<String,Task> entry : mp.entrySet()) 
		{
            System.out.println("Key1 = " + entry.getKey() +
                             ", Value = " + entry.getValue().getStatus()
                             );	
		
		//
//			kafkaTemplate.send(consumer.getTaskName(), consumer.getModel());   
	}
		kafkaTemplate.send("clone", agent);   

}
	public void sendMessage2() {
	
			for (Map.Entry<String,Task> entry : consumer.getModel().getListOfTasks().entrySet()) 
//			for (Map.Entry<String,Task> entry : mp.entrySet()) 
			{
	            System.out.println("Key = " + entry.getKey() +
	                             ", Value = " + entry.getValue().getStatus()
	                             );	
			
		}
			kafkaTemplate2.send("uppercase", "Crush Aaloo");   

	}
}