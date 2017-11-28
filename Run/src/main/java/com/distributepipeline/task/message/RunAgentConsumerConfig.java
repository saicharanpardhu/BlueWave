package com.distributepipeline.task.message;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.distributepipeline.task.domain.InputModel;


@Configuration
@EnableKafka
public class RunAgentConsumerConfig {
	@Bean
    public ConsumerFactory<String, InputModel> triggerConsumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
      "${KAFKA_URL}:9092");
    
    props.put(
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
      StringDeserializer.class);
    props.put(
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
      JsonDeserializer.class);
    props.put(
            ConsumerConfig.GROUP_ID_CONFIG, 
            "something53");
    return new DefaultKafkaConsumerFactory<>(props,
    		new StringDeserializer(),
    		new JsonDeserializer<>(InputModel.class));
}

@Bean
public ConcurrentKafkaListenerContainerFactory<String, InputModel> 
reportKafkaListenerContainerFactory() {
 
    ConcurrentKafkaListenerContainerFactory<String, InputModel> factory
      = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(triggerConsumerFactory());
    return factory;
}



@Bean
public ConsumerFactory<String, String> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
      "${KAFKA_URL}:9092");
    props.put(
      ConsumerConfig.GROUP_ID_CONFIG, 
      "something22");
    props.put(
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
      StringDeserializer.class);
    props.put(
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
      StringDeserializer.class);
    return new DefaultKafkaConsumerFactory<>(props);
}

@Bean
public ConcurrentKafkaListenerContainerFactory<String, String> 
  kafkaListenerContainerFactory() {

    ConcurrentKafkaListenerContainerFactory<String, String> factory
      = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
}
}