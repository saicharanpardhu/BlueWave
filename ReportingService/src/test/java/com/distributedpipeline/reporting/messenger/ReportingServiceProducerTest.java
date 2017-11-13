package com.distributedpipeline.reporting.messenger;

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

import com.distributedpipeline.reporting.domain.*;
import com.distributedpipeline.reporting.messenger.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportingServiceProducerTest {
	@Autowired
	private ReportingServiceProducer sender;
	@Autowired
	private ReportingServiceConsumer receiver;
	@ClassRule
	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, "report-service");
	@Test
    public void testReceive() throws Exception {
		
		Report report = new Report(new Long(16), new Timestamp(System.currentTimeMillis()),"akshaydv", "generic message","fatal");
	    sender.sendMessage( report);

	    receiver.getLatch().await(3000, TimeUnit.MILLISECONDS);
	    assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	  }

}