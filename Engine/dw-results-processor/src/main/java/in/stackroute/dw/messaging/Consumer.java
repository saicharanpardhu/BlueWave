package in.stackroute.dw.messaging;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import in.stackroute.dw.domain.Model;
import in.stackroute.dw.service.ResultService;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.kafka.annotation.KafkaListener;

@Service
public class Consumer {
	
	@Autowired
	Producer producer;
	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	private Model model;
	@Autowired
	ResultService resultService;
	
	public Model getModel() {
		return model;
	}


	public void setModel(Model model) {
		this.model = model;
	}


	public String getId() {
		return Id;
	}


	public void setId(String id) {
		Id = id;
	}

	private String Id;
	
	
	
	@KafkaListener(topics = "git", 
			  containerFactory = "engineKafkaListenerContainerFactory")
		public void reportlistener(Model model) throws IOException {
//		System.out.println("Dsfdsfsdfsdfsdfdsfsd");
		resultService.updateRedis(model);
//		model = model1;
//		Id = model1.getJobId();	
//		System.out.println(model.getJobId());
		producer.sendMessage();
		producer.sendMessageApi();
		latch.countDown();
		
		
		
		}
	


}