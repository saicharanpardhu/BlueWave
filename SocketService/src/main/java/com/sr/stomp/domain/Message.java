package com.sr.stomp.domain;
 
public class Message {

    private String output;
    private String userName;
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
    
    public Message() {}
	public Message(String output, String userName) {
		super();
		this.output = output;
		this.userName = userName;
	}
    
     
}