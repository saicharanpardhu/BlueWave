//package com.distributedpipeline.persistence.messenger;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertThat;
//
//import java.security.Timestamp;
//import java.util.concurrent.TimeUnit;
//
//import org.junit.ClassRule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.test.rule.KafkaEmbedded;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.distributedpipeline.persistence.domain.Report;
//import com.distributedpipeline.persistence.message.PersistenceConsumer;
//import com.distributedpipeline.persistence.message.PersistenceProducer;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class PersistenceProducerTest {
//	@Autowired
//	private PersistenceProducer sender;
//	@Autowired
//	private PersistenceConsumer receiver;
//	@ClassRule
//	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, "report-service");
//	@Test
//    public void testReceive() throws Exception {
//		
//		Report report = new Report();
//	    sender.sendMessage( report);
//
//	    receiver.getLatch().await(3000, TimeUnit.MILLISECONDS);
//	    assertThat(receiver.getLatch().getCount()).isEqualTo(0);
//	  }
//
//}
