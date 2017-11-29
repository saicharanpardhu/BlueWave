package com.distributepipeline.task.message;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;


import com.distributepipeline.task.model.Output;
import com.distributepipeline.task.model.ReportModel;


@Configuration
public class DemoProducerConfig {


  

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;
    @Bean
    public ProducerFactory<String, Output> producerOutputFactory() {
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
    public KafkaTemplate<String, Output> kafkaOutputTemplate() {
        return new KafkaTemplate<>(producerOutputFactory());
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
