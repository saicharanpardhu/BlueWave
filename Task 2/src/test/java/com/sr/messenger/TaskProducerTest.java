package com.sr.messenger;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;
import com.sr.messenger.*; 

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskProducerTest {
	@Autowired
	private TaskProducer sender;
	@Autowired
	private TaskConsumer receiver;
	@ClassRule
	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, "report-service");
	@Test
    public void testReceive() throws Exception {
		
		String message = "hello world";
	    sender.sendMessage( message); 
	    receiver.getLatch().await(3000, TimeUnit.MILLISECONDS);
	    assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	  }

}