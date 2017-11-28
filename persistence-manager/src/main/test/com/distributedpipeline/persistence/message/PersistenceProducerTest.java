package com.distributedpipeline.persistence.message;
import static org.springframework.kafka.test.hamcrest.KafkaMatchers.hasValue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.distributedpipeline.persistence.domain.Tasks;
import com.distributedpipeline.persistence.domain.Workflow;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class PersistenceProducerTest {

  private static String SENDER_TOPIC = "sender.t";
  @Autowired
  PersistenceProducer sender;
  
  private KafkaMessageListenerContainer<String, String> container;
  private BlockingQueue<ConsumerRecord<String, String>> records;
  
  @Autowired
  private KafkaTemplate<String,Workflow> kafkaTemplate;
  @ClassRule
  public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, SENDER_TOPIC);
  @Before
  public void setUp() throws Exception {
    // set up the Kafka consumer properties
    Map<String, Object> consumerProperties =
        KafkaTestUtils.consumerProps("sender", "false", embeddedKafka);
    // create a Kafka consumer factory
    DefaultKafkaConsumerFactory<String, String> consumerFactory =
        new DefaultKafkaConsumerFactory<String, String>(consumerProperties);
    // set the topic that needs to be consumed
    ContainerProperties containerProperties = new ContainerProperties(SENDER_TOPIC);
    // create a Kafka MessageListenerContainer
    container = new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
    // create a thread safe queue to store the received message
    records = new LinkedBlockingQueue<>();
    // setup a Kafka message listener
    container.setupMessageListener(new MessageListener<String, String>() {
      @Override
      public void onMessage(ConsumerRecord<String, String> record) {
//        LOGGER.debug("test-listener received message='{}'", record.toString());
        records.add(record);
      }
    });
    // start the container and underlying message listener
    container.start();
    // wait until the container has the required number of assigned partitions
    ContainerTestUtils.waitForAssignment(container, embeddedKafka.getPartitionsPerTopic());
  }
  @After
  public void tearDown() {
    // stop the container
    container.stop();
  }
  @Test
  public void testSend() throws InterruptedException {
    // send the message
//    String greeting = "Hello Spring Kafka Sender!";
//    sender.send(SENDER_TOPIC, greeting);
   
      // AssertJ Condition to check the key
//    assertThat(received).has(key(null));
	  String[] canViewUser = {"Anonymous"};
	  String[] canExecuteUser = {"Anonymous1"};
	  String[] depends_on = {};
	  String[] input = {"https://github.com/eugenp/tutorials/tree/master/spring-boot"};
	  String[] output = {};
	  Tasks task = new Tasks("clone","completed",depends_on,input,output);
	  Map<String,Tasks> map = new HashMap<String,Tasks>();
	  map.put("git-clone", task);
	  Workflow workflow=new Workflow("demo","stackroute",canViewUser,canExecuteUser,"done",map);
      sender.sendMessage(workflow);
//     check that the message was received
      ConsumerRecord<String, String> received = records.poll(10, TimeUnit.SECONDS);
//     Hamcrest Matchers to check the value
      assertThat(received, hasValue(workflow));
  
  }
  
  private void assertThat(ConsumerRecord<String, String> received, Matcher<ConsumerRecord<?,Workflow>> hasValue) {
      
  }
  
}