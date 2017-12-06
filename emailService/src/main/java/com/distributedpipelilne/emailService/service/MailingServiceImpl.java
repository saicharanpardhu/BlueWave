package com.distributedpipelilne.emailService.service;
//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import java.lang.annotation.Repeatable;

import org.springframework.mail.SimpleMailMessage;



//finma

@Service
public class MailingServiceImpl {

@Autowired
MailSender mailSender;
	
	public String emailID;
	public String status;
	
	public void put(String emailID,String status) {
		 System.out.println(emailID);
		this.emailID = emailID;
		this.status=status;
		sendmail();
		}
	
	
    public String sendmail() {
    	SimpleMailMessage message = new SimpleMailMessage();
      
    	
		message.setText(this.status);
  
//    	message.setText("build status will show");

    	message.setTo(this.emailID);
    	message.setFrom(" dashboard.stackroute@gmail.com");
	  try {
	      mailSender.send(message);
	      return "{\"message\": \"OK\"}";
	  } catch (Exception e) {
	      e.printStackTrace();
	      return "{\"message\": \"Error\"}";
	  }
			
		}

}
	
	
