package com.sr.stomp.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.sr.stomp.domain.Message;

/* Controller class mapping messages to a socket response topic */
@Controller
public class SocketController {

	private static final Logger logger = LogManager.getLogger("MethodLogger.class");

	/*
	 * Both of the mappings are for testing if the socket is working or not, not
	 * used in applicaiton
	 */

	/* Mapping Socket messages to another socket messaging topic */
	@MessageMapping("/topic/messages")
	@SendTo("/response")
	@CrossOrigin(origins = "*")
	public Message greeting(final Message message) throws Exception {
		logger.info("Received at socket: " + message.getOutput() + "!");
		return new Message();
	}
	
	@PostMapping("/topic/workflow")
	@SendTo("/workflow/a")
	@CrossOrigin(origins = "*")
	public Message jobId(final Message jobId) throws Exception {
		logger.info("Received at socket: " + jobId.getOutput() + "!");
		return jobId;
	}

	/* Mapping Http post requests to Socket topics for testing */

	@PostMapping("/response/snack")
	@SendTo("/response")
	@CrossOrigin(origins = "*")
	public Message greetingSnack(final Message message) throws Exception {
		logger.info("Received at socket: " + message + "!");
		return new Message();
	} 

}