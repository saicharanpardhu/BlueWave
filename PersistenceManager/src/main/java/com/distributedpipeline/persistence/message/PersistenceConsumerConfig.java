package com.distributedpipeline.persistence.message;

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

import com.distributedpipeline.persistence.domain.Report;

@Configuration
@EnableKafka

public class PersistenceConsumerConfig {
      
	@Bean
    public ConsumerFactory<String, Report> reportconsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
          ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          "172.23.238.170:9092");
        
        props.put(
          ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
          StringDeserializer.class);
        props.put(
          ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
          JsonDeserializer.class);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG, 
                "something");
        return new DefaultKafkaConsumerFactory<>(
        	      props,
        	      new StringDeserializer(), 
        	      new JsonDeserializer<>(Report.class));
    }
 
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Report> 
	  reportKafkaListenerContainerFactory() {
	 
	    ConcurrentKafkaListenerContainerFactory<String, Report> factory
	      = new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(reportconsumerFactory());
	    return factory;
	}
	@Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
          ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          "localhost:9092");
        props.put(
          ConsumerConfig.GROUP_ID_CONFIG, 
          "something");
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