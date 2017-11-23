package com.distributepipeline.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.distributepipeline.domain.JobIdDetails;
import com.distributepipeline.domain.ModelToSocket;
import com.distributepipeline.domain.Trigger;
import com.distributepipeline.domain.WorkFlow;

@Configuration
@EnableKafka
public class JobManagerProducerConfig {

	@Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;
	
	//kafka producer bean having string as key and trigger object as value
	 @Bean
	    public ProducerFactory<String, Trigger> producerFactory() {
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
	    public KafkaTemplate<String, Trigger> kafkaReportTemplate() {
	        return new KafkaTemplate<>(producerFactory());
	    }
	  
	    @Bean
	    public ProducerFactory<String, JobIdDetails> producerJobIdDetailsFactory() {
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
	    public KafkaTemplate<String, JobIdDetails> kafkaJobIdDetailsTemplate() {
	        return new KafkaTemplate<>(producerJobIdDetailsFactory());
	    }
	  
	    
	    // kafka producer bean having string as key and value
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
	    public KafkaTemplate<String, String> kafkaStringTemplate() {
	        return new KafkaTemplate<>(producerStringFactory());
	    }
	
	    @Bean
	    public ProducerFactory<String, ModelToSocket> producerModelToSocketFactory() {
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
	    public KafkaTemplate<String, ModelToSocket> kafkaModelToSocketTemplate() {
	        return new KafkaTemplate<>(producerModelToSocketFactory());
	    }
	  
	    
	   
}
