package com.distributedworkflowengine.jobscheduler.config;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

// Redis Configuration

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
private static final Logger log = Logger.getLogger(CacheConfig.class);

// Constructs a new JedisConnectionFactory instance with default settings
@Bean
public JedisConnectionFactory redisConnectionFactory() {
  JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
//  redisConnectionFactory.setHostName("172.23.238.159");
  redisConnectionFactory.setHostName("${REDIS_URL}");
  redisConnectionFactory.setPort(6379);
  return redisConnectionFactory;
}

//Constructs a RedisTemplate 
@Bean
public RedisTemplate<String, String> redisTemplate() {
  RedisTemplate<String, String> rt = new RedisTemplate<>();
  rt.setConnectionFactory(redisConnectionFactory());
  rt.setKeySerializer(new StringRedisSerializer());
  rt.setValueSerializer(new StringRedisSerializer());
  return rt;
}

//Constructs an objectMapper
@Bean
public ObjectMapper objectMapper() {
    return new ObjectMapper();
}

//Constructs a RedisCacheManager
@Bean
public RedisCacheManager cacheManager() {
  RedisCacheManager rcm = new RedisCacheManager(redisTemplate());
  rcm.setDefaultExpiration(300);
  rcm.setTransactionAware(true);
  return rcm;
}

}
