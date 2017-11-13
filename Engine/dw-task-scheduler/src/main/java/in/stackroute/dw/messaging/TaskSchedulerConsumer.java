package in.stackroute.dw.messaging;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.stackroute.dw.domain.Agent;
import in.stackroute.dw.domain.Model;
import in.stackroute.dw.domain.Task;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class TaskSchedulerConsumer {
	
	
	@Autowired
	TaskSchedulerProducer taskSchedulerProducer;
	@Autowired
	private KafkaTemplate<String, Agent> kafkaTemplate;
	@Autowired
	Agent agent;
	
	@Autowired
	Agent agent2;
	private Model model;
	private Map<String, Task> taskName;
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Map<String, Task> getTaskName() {
		return taskName;
	}



	public void setTaskName(Map<String, Task> taskName) {
		this.taskName = taskName;
	}

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
	    return latch;
	}
		
		
		
		@KafkaListener(topics = "JobScheToTaskSched2", 
				  containerFactory = "engineKafkaListenerContainerFactory")
				public void reportlistener(Model model1) throws JsonProcessingException {
			model=model1;

			for (Map.Entry<String,Task> entry : model1.getListOfTasks().entrySet()) 
			{
				agent.setJobId(model1.getJobId());
				agent.setTaskname(entry.getKey());
				agent.setInput(model1.getListOfTasks().get(entry.getKey()).getInput()[0]);
	           
				ObjectMapper mapperObj2 = new ObjectMapper();
//				System.out.println(mapperObj2.writeValueAsString(agent));
				
//				System.out.println("DAta"+entry.getKey());
				kafkaTemplate.send(entry.getKey(), agent);
				
				
			
		}
						
		    latch.countDown();
				}


}
