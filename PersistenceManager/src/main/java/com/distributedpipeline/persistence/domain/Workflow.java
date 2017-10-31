package com.distributedpipeline.persistence.domain;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="PERSISTENCEMODEL")
@Entity
public class PersistenceModel {
	
	@Id
	private long id;
	private ArrayList<Long> sequence;
	private String userid;
	
	/*---------------- getters and setters of fields ----------------- */
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public ArrayList<Long> getSequence() {
		return sequence;
	}
	public void setSequence(ArrayList<Long> sequence) {
		this.sequence = sequence;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	/*------------------ constructors based on field ------------------- */
	public PersistenceModel(long id, ArrayList<Long> sequence, String userid) {
		super();
		this.id = id;
		this.sequence = sequence;
		this.userid = userid;
	}
	
	
	/*------------------- default constructor -------------------------- */ 
	public PersistenceModel()
	{
		
	}
	
	
	
	
}
