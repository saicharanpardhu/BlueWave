package com.sr.domain;
 
import java.sql.Timestamp; 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Report {
	
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@ApiModelProperty(notes = "The database generated report ID")
    private Long id;
	
	@Column(name="timestamp")
	@ApiModelProperty(notes = "The project generated timestamp")
	private Timestamp timestamp;
	
	@Column(name="userid")
	@ApiModelProperty(notes = "Userid who generated the report")
	private String userid;
	
	@Column(name="message")
	@ApiModelProperty(notes = "The report message")
	private String message;
	
	@Column(name="severity")
	@ApiModelProperty(notes = "The report severity")
	private String severity;
	
	public Report() {}
	
	public Report(Long id, Timestamp timestamp, String userid, String message, String severity) {
		super();
		this.id = id;
		this.timestamp = new Timestamp(System.currentTimeMillis());
		this.userid = userid;
		this.message = message;
		this.severity = severity;
	}
	
	public Report(String userid, String message, String severity) {
		super(); 
		System.out.println("EHEREWR");
		this.timestamp = new Timestamp(System.currentTimeMillis());
		this.userid = userid;
		this.message = message;
		this.severity = severity;
	} 
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	} 
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	} 
	
	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
 
}
