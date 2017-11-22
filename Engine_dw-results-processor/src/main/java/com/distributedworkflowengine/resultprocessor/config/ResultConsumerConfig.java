package com.distributedworkflowengine.resultprocessor.config;



import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.distributedworkflowengine.resultprocessor.domain.Model;

//import com.distributedpipeline.engineservice.domain.TaskModel;

@Configuration
@EnableKafka
public class ResultConsumerConfig {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;
	
    @Bean
   public ConsumerFactory<String, Model> reportconsumerFactory() {
       Map<String, Object> props = new HashMap<>();
       props.put(
         ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
         bootstrapServer);
       
       props.put(
         ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
         StringDeserializer.class);
       props.put(
         ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
         JsonDeserializer.class);
       props.put(
               ConsumerConfig.GROUP_ID_CONFIG,
               "something10");
       return new DefaultKafkaConsumerFactory<>(
                 props,
                 new StringDeserializer(),
                 new JsonDeserializer<>(Model.class));
   }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Model> 
      engineKafkaListenerContainerFactory() {
     
        ConcurrentKafkaListenerContainerFactory<String, Model> factory
          = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(reportconsumerFactory());
        return factory;
    }

	@Bean
   public ConsumerFactory<String, String> consumerFactory() {
       Map<String, Object> props = new HashMap<>();
       props.put(
         ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
         bootstrapServer);
       props.put(
         ConsumerConfig.GROUP_ID_CONFIG,
         "something22899");
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