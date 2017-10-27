//package com.sr.messenger;
//
//import static org.junit.Assert.*;
//import org.junit.Test;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.sql.Timestamp;
//import java.util.concurrent.TimeUnit;
//import org.junit.ClassRule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.test.rule.KafkaEmbedded;
//import org.springframework.test.context.junit4.SpringRunner;
//import com.sr.messenger.*;
//import com.sr.domain.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class EngineServiceConsumerTest {
//	@Autowired
//	private EngineServiceProducer sender;
//	@Autowired
//	private EngineServiceConsumer receiver;
//	@ClassRule
//	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, "report-service");
//	@Test
//    public void testReceive() throws Exception {
//		
//		EngineModel report = new EngineModel(new Long(16), new Timestamp(System.currentTimeMillis()),"akshaydv", "generic message","fatal");
//	    sender.sendMessage( report);
//
//	    receiver.getLatch().await(3000, TimeUnit.MILLISECONDS);
//	    assertThat(receiver.getLatch().getCount()).isEqualTo(0);
//	  }
//
//}