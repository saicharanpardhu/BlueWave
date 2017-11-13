package com.distributedpipeline.persistence.domain;


import java.sql.Timestamp; 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
/*---------------- model of reproting service ------------------ */
public class Report {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Column(name="timestamp",  columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp timestamp;
	
	@Column(name="userid")
	private String userid;
	
	@Column(name="message")
	private String message;
	
	@Column(name="severity")
	private String severity;
	
	/*------------------ constructors based on field ------------------- */
	public Report() {}
	
	public Report(Long id, Timestamp timestamp, String userid, String message, String severity) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.userid = userid;
		this.message = message;
		this.severity = severity;
	}
	
	/*---------------- getters and setters of fields ----------------- */
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
