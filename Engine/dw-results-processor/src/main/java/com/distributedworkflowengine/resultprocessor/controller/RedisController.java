package com.distributedworkflowengine.resultprocessor.controller;
//package in.stackroute.dw.controller;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
////import com.google.gson.Gson;
//
//import in.stackroute.dw.messaging.Producer;
//@RestController
//@RequestMapping(path="/v1.0/Engine")
//public class RedisController {
//    
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//    
//    @Autowired
//    private ObjectMapper objectMapper;
//    
//    @Autowired
//    Producer engine;
//    
//    @GetMapping("pro")
//    public ResponseEntity<String> pro()  
//    {
//        engine.sendMessage();
//        return new ResponseEntity<String>("sent",HttpStatus.OK);
//        
//    }
//      
//    
//}
