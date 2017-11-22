package com.distributedworkflowengine.jobscheduler.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

import com.distributedworkflowengine.jobscheduler.domain.User;


//Kafka Configuration for Consumer

@Configuration
@EnableKafka
public class JsConsumerConfig {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;
	    @Bean
		   public ConsumerFactory<String,User> UserConsumerFactory() {
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
		               "something2334454454");
		       return new DefaultKafkaConsumerFactory<>(
		                 props,
		                 new StringDeserializer(),
		                 new JsonDeserializer<>(User.class));
		       
		   }

		    @Bean
		    public ConcurrentKafkaListenerContainerFactory<String, User> 
		      UserKafkaListenerContainerFactory() {
		     
		        ConcurrentKafkaListenerContainerFactory<String, User> factory
		          = new ConcurrentKafkaListenerContainerFactory<>();
		        factory.setConsumerFactory(UserConsumerFactory());
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
	         "something");
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
