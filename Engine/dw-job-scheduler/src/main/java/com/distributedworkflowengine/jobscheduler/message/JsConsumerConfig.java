package com.distributedworkflowengine.jobscheduler.message;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.distributedworkflowengine.jobscheduler.domain.Job;



@Configuration
@EnableKafka
public class JsConsumerConfig {
	
	 @Bean
	   public ConsumerFactory<String,Job> createConsumerFactory() {
	       Map<String, Object> props = new HashMap<>();
	       props.put(
	         ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
	         "172.23.238.158:9092");
	       
	       props.put(
	         ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
	         StringDeserializer.class);
	       props.put(
	         ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
	         JsonDeserializer.class);
	       props.put(
	               ConsumerConfig.GROUP_ID_CONFIG,
	               "something2344");
	       return new DefaultKafkaConsumerFactory<>(
	                 props,
	                 new StringDeserializer(),
	                 new JsonDeserializer<>(Job.class));
	       
	   }

	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, Job> 
	      reportKafkaListenerContainerFactory() {
	     
	        ConcurrentKafkaListenerContainerFactory<String, Job> factory
	          = new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(createConsumerFactory());
	        return factory;
	    }
	    @Bean
	   public ConsumerFactory<String, String> consumerFactory() {
	       Map<String, Object> props = new HashMap<>();
	       props.put(
	         ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
	         "172.23.238.158:9092");
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
