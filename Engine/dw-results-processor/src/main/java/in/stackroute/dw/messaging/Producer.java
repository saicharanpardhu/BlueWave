package in.stackroute.dw.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.stackroute.dw.domain.ModelSend;

import org.springframework.kafka.core.KafkaTemplate;


@Service
public class Producer {
	
	@Autowired
	Consumer consumer;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	 
	public void sendMessage() {
		System.out.println("sending to job");
		kafkaTemplate.send("StateToJobSche", "git");
	}
	public void sendMessageApi() {
		System.out.println("response to gateway");
		kafkaTemplate.send("report-topic", "task-completed");
	}
}