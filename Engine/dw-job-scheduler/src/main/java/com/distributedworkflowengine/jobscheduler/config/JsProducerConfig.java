package com.distributedworkflowengine.jobscheduler.config;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.distributedworkflowengine.jobscheduler.domain.*;

//Kafka Configuration for Producer

@Configuration
public class JsProducerConfig {
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;
	
	//kafka producer bean having string as key and TaskToScheduler as value
	   @Bean
	   public ProducerFactory<String, TaskToScheduler> producerjobFactory1() {
	       Map<String, Object> configProps = new HashMap<>();
	       configProps.put(
	         ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
	         bootstrapServer);
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
	   
	   
	   @Bean
	   public ProducerFactory<String, ReportModel> producerReportFactory() {
	       Map<String, Object> configProps = new HashMap<>();
	       configProps.put(
	         ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
	         bootstrapServer);
	       configProps.put(
	         ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
	         StringSerializer.class);
	       configProps.put(
	         ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
	         JsonSerializer.class);
	       return new DefaultKafkaProducerFactory<>(configProps);
	}
	   @Bean
	   public KafkaTemplate<String, ReportModel> kafkaReportTemplate() {
	       return new KafkaTemplate<>(producerReportFactory());
	   }
	   @Bean
	   public ProducerFactory<String, String> producerStringFactory1() {
	       Map<String, Object> configProps = new HashMap<>();
	       configProps.put(
	         ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
	         bootstrapServer);
	       configProps.put(
	         ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
	         StringSerializer.class);
	       configProps.put(
	         ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
	         JsonSerializer.class);
	       return new DefaultKafkaProducerFactory<>(configProps);
	}
	   @Bean
	   public KafkaTemplate<String, String> kafkaTemplate1() {
	       return new KafkaTemplate<>(producerStringFactory1());
	   }
	   
	   @Bean
	   public ProducerFactory<String, User> producerUserFactory1() {
	       Map<String, Object> configProps = new HashMap<>();
	       configProps.put(
	         ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
	         bootstrapServer);
	       configProps.put(
	         ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
	         StringSerializer.class);
	       configProps.put(
	         ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
	         JsonSerializer.class);
	       return new DefaultKafkaProducerFactory<>(configProps);
	}
	   @Bean
	   public KafkaTemplate<String, User> kafkaUserTemplate() {
	       return new KafkaTemplate<>(producerUserFactory1());
	   }
	   
	}
