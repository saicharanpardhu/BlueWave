package com.distributedworkflowengine.stateinit.messenger;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.distributedworkflowengine.stateinit.domain.*;

import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

//<!----Consumer Config -->
@Configuration
@EnableKafka
public class EngineConsumerConfig {
      
	@Bean
    public ConsumerFactory<String, Trigger> triggerConsumerFactory() {
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
                "something5");
        return new DefaultKafkaConsumerFactory<>(
        	      props,
        	      new StringDeserializer(), 
        	      new JsonDeserializer<>(Trigger.class));
    }
 
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Trigger> 
	triggerKafkaListenerContainerFactory() {
	 
	    ConcurrentKafkaListenerContainerFactory<String, Trigger> factory
	      = new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(triggerConsumerFactory());
	    return factory;
	}
//	
//	@Bean
//    public ConsumerFactory<String, TaskResponse> taskResponseConsumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(
//          ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
//          "172.23.238.158:9092");
//        
//        props.put(
//          ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
//          StringDeserializer.class);
//        props.put(
//          ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
//          JsonDeserializer.class);
//        props.put(
//                ConsumerConfig.GROUP_ID_CONFIG, 
//                "something5");
//        return new DefaultKafkaConsumerFactory<>(
//        	      props,
//        	      new StringDeserializer(), 
//        	      new JsonDeserializer<>(TaskResponse.class));
//    }
// 
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, TaskResponse> 
//	taskResponseKafkaListenerContainerFactory() {
//	 
//	    ConcurrentKafkaListenerContainerFactory<String, TaskResponse> factory
//	      = new ConcurrentKafkaListenerContainerFactory<>();
//	    factory.setConsumerFactory(taskResponseConsumerFactory());
//	    return factory;
//	}

	@Bean
    public ConsumerFactory<String, Task> taskConsumerFactory() {
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
                "something5");
        return new DefaultKafkaConsumerFactory<>(
        	      props,
        	      new StringDeserializer(), 
        	      new JsonDeserializer<>(Task.class));
    }
 
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Task> 
	taskKafkaListenerContainerFactory() {
	 
	    ConcurrentKafkaListenerContainerFactory<String, Task> factory
	      = new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(taskConsumerFactory());
	    return factory;
	}
	
	@Bean
    public ConsumerFactory<String, WorkFlow> workflowFactory() {
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
                "something5");
        return new DefaultKafkaConsumerFactory<>(
        	      props,
        	      new StringDeserializer(), 
        	      new JsonDeserializer<>(WorkFlow.class));
    }
 
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, WorkFlow> 
	workflowKafkaListenerContainerFactory() {
	 
	    ConcurrentKafkaListenerContainerFactory<String, WorkFlow> factory
	      = new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(workflowFactory());
	    return factory;
	}
	@Bean
    public ConsumerFactory<String, TaskLibrary> TaskLibraryFactory() {
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
                "something5");
        return new DefaultKafkaConsumerFactory<>(
        	      props,
        	      new StringDeserializer(), 
        	      new JsonDeserializer<>(TaskLibrary.class));
    }
 
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, TaskLibrary> 
	TaskLibraryKafkaListenerContainerFactory() {
	 
	    ConcurrentKafkaListenerContainerFactory<String, TaskLibrary> factory
	      = new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(TaskLibraryFactory());
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