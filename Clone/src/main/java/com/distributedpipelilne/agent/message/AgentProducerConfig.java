package com.distributedpipelilne.agent.message;


import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.distributedpipelilne.agent.model.Input;
import com.distributedpipelilne.agent.model.Output;
import com.distributedpipelilne.agent.model.ReportModel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
//<!----ReportingService Producer Config -->
@Configuration
@EnableKafka
public class AgentProducerConfig {
    
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;
	@Bean
    public ProducerFactory<String, Output> producerFactory() {
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
    public KafkaTemplate<String, Output> kafkaReportTemplate() {
        return new KafkaTemplate<>(producerFactory());
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
    public KafkaTemplate<String, String> kafkaStringTemplate() {
        return new KafkaTemplate<>(producerStringFactory());
    }
    
    @Bean
    public ProducerFactory<String, ReportModel> producerReportingFactory() {
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
    public KafkaTemplate<String, ReportModel> kafkaReportingTemplate() {
        return new KafkaTemplate<>(producerReportingFactory());
    }
}
