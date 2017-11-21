package com.distributedpipeline.persistence.config;

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

import com.distributedpipeline.persistence.domain.TaskLibrary;
import com.distributedpipeline.persistence.domain.Tasks;
import com.distributedpipeline.persistence.domain.Workflow;



@Configuration
public class PersistenceProducerConfig {
	
	@Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;
	
	@Bean
    public ProducerFactory<String, Workflow> producerFactory() {
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
    public ProducerFactory<String, Tasks> producerTasksFactory() {
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
    public ProducerFactory<String, TaskLibrary> producerTaskLibraryFactory() {
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
	   public ProducerFactory<String, String> producerStringFactory() {
	       Map<String, Object> configProps = new HashMap<>();
	       configProps.put(
	         ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
	         bootstrapServer);
	       configProps.put(
	         ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
	         StringSerializer.class);
	       configProps.put(
	         ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
	         StringSerializer.class);
	       
	       return new DefaultKafkaProducerFactory<>(configProps);
	   }
	@Bean
	   public ProducerFactory<String, Integer> producerIntFactory() {
	       Map<String, Object> configProps = new HashMap<>();
	       configProps.put(
	         ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
	         bootstrapServer);
	       configProps.put(
	         ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
	         StringSerializer.class);
	       configProps.put(
	         ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
	         IntegerSerializer.class);
	       
	       return new DefaultKafkaProducerFactory<>(configProps);
	   }
 
    @Bean
    public KafkaTemplate<String, Workflow> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
    @Bean
    public KafkaTemplate<String, Tasks> kafkaTemplate1() {
        return new KafkaTemplate<>(producerTasksFactory());
    }
    
    @Bean
    public KafkaTemplate<String, TaskLibrary> kafkaTemplaten() {
        return new KafkaTemplate<>(producerTaskLibraryFactory());
    }
}

