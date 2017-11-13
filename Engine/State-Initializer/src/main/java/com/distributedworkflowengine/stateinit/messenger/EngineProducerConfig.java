 
package com.distributedworkflowengine.stateinit.messenger;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.distributedworkflowengine.stateinit.domain.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

//<!----ReportingService Producer Config -->
@Configuration
public class EngineProducerConfig {
//	@Bean
//    public ProducerFactory<String, Report> producerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(
//          ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
//          "172.23.238.158:9092");
//        configProps.put(
//          ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
//          StringSerializer.class);
//        configProps.put(
//          ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
//          JsonSerializer.class);
//        
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
// 
//    @Bean
//    public KafkaTemplate<String, Report> kafkaReportTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//    @Bean
//    public ProducerFactory<String, Model> modelproducerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(
//          ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
//          "172.23.238.158:9092");
//        configProps.put(
//          ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
//          StringSerializer.class);
//        configProps.put(
//          ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
//          JsonSerializer.class);
//        
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
// 
//    @Bean
//    public KafkaTemplate<String, Model> kafkaModelTemplate() {
//        return new KafkaTemplate<>(modelproducerFactory());
//    }
//    
    @Bean
    public ProducerFactory<String, String> producerStringFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
          ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          "172.23.238.158:9092");
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
}
