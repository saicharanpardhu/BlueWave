package com.sr.stomp.messenger;

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

import com.sr.stomp.domain.ConsoleModel;
import com.sr.stomp.domain.JobIdDetails;
import com.sr.stomp.domain.Message;
import com.sr.stomp.domain.SocketModel;

//<!----Consumer Config -->
@Configuration
@EnableKafka
public class SocketServiceConsumerConfig {
    
	@Bean
    public ConsumerFactory<String, Message> consumerMessageFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
          ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          "172.23.238.159:9092");
        props.put(
          ConsumerConfig.GROUP_ID_CONFIG, 
          "something22");
        props.put(
          ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
          StringDeserializer.class);
        props.put(
          ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
          JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props,
        		new StringDeserializer(),
        		new JsonDeserializer<>(Message.class));
    }
 
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Message> 
      kafkaMessageListenerContainerFactory() {
    
        ConcurrentKafkaListenerContainerFactory<String, Message> factory
          = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerMessageFactory());
        return factory;
    }
    
    @Bean
    public ConsumerFactory<String, JobIdDetails> consumerJobIdFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
          ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          "172.23.238.159:9092");
        props.put(
          ConsumerConfig.GROUP_ID_CONFIG, 
          "something22");
        props.put(
          ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
          StringDeserializer.class);
        props.put(
          ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
          JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props,
        		new StringDeserializer(),
        		new JsonDeserializer<>(JobIdDetails.class));
    }
 
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, JobIdDetails> 
      kafkaJobIdListenerContainerFactory() {
    
        ConcurrentKafkaListenerContainerFactory<String, JobIdDetails> factory
          = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerJobIdFactory());
        return factory;
    }
    
    @Bean
    public ConsumerFactory<String, ConsoleModel> consumerConsoleMessageFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
          ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          "172.23.238.159:9092");
        props.put(
          ConsumerConfig.GROUP_ID_CONFIG, 
          "something22");
        props.put(
          ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
          StringDeserializer.class);
        props.put(
          ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
          JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props,
        		new StringDeserializer(),
        		new JsonDeserializer<>(ConsoleModel.class));
    }
 
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ConsoleModel> 
      kafkaConsoleListenerContainerFactory() {
    
        ConcurrentKafkaListenerContainerFactory<String, ConsoleModel> factory
          = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerConsoleMessageFactory());
        return factory;
    }
	
	@Bean
    public ConsumerFactory<String, SocketModel> consumerSocketFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
          ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          "172.23.238.159:9092");
        props.put(
          ConsumerConfig.GROUP_ID_CONFIG, 
          "something22");
        props.put(
          ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
          StringDeserializer.class);
        props.put(
          ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
          JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props,
          		new StringDeserializer(),
        		new JsonDeserializer<>(SocketModel.class));
    
    }
 
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SocketModel> 
      kafkaSocketMessageListenerContainerFactory() {
    
        ConcurrentKafkaListenerContainerFactory<String, SocketModel> factory
          = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerSocketFactory());
        return factory;
    }
	
	@Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
          ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          "172.23.238.159:9092");
        props.put(
          ConsumerConfig.GROUP_ID_CONFIG, 
          "something22");
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