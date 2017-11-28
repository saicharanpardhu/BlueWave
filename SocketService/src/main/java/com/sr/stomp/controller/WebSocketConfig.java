package com.sr.stomp.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	/* Web Socket Configuration file */

	@Override
	public void configureMessageBroker(final MessageBrokerRegistry config) {
		/*
		 * Overriding and configuring the Socket messaging topic. All the socket topics
		 * need to be registered here.
		 */
		config.enableSimpleBroker("/topic", "/response", "/number", "/name","/workflow","/console");
		config.setApplicationDestinationPrefixes("/app");
	}

	/*
	 * Registering socket End points, setting CORS with Sock JS. This is the url
	 * used to opening the connection with Socket
	 */
	@Override
	public void registerStompEndpoints(final StompEndpointRegistry registry) {
		registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins("*").withSockJS();
	}

}