package com.distributedworkflowengine.jobscheduler.message;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.distributedworkflowengine.jobscheduler.domain.*;


@Configuration
public class JsProducerConfig {
		 
	   @Bean
	   public ProducerFactory<String, TaskToScheduler> producerjobFactory1() {
	       Map<String, Object> configProps = new HashMap<>();
	       configProps.put(
	         ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
	         "172.23.238.158:9092");
	       configProps.put(
	         ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
	         StringSerializer.class);
	       configProps.put(
	         ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
	         JsonSerializer.class);
	       return new DefaultKafkaProducerFactory<>(configProps);
	}
	   @Bean
	   public KafkaTemplate<String, TaskToScheduler> kafkaTemplate() {
	       return new KafkaTemplate<>(producerjobFactory1());
	   }
	 
//	    @Bean
//	    public KafkaTemplate<String, String> kafkaTemplate1() {
//	        return new KafkaTemplate<>(producerStringFactory());
//	    }
//	    @Bean
//	    public KafkaTemplate<String, Integer> kafkaTemplate2() {
//	        return new KafkaTemplate<>(producerIntFactory());
//	    }
	}
