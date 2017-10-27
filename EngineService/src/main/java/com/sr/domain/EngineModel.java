package com.sr.domain;

import java.util.ArrayList;

public class EngineModel {
	

	private long id;
	private ArrayList<Long> sequence;
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
	/*---------------- getters and setters of fields ----------------- */
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	
	/*------------------- default constructor -------------------------- */ 
	public EngineModel()
	{
	}
	public ArrayList<Long> getSequence() {
		return sequence;
	}
	public void setSequence(ArrayList<Long> sequence) {
		this.sequence = sequence;
	}
	public EngineModel(long id, ArrayList<Long> sequence, String userId) {
		super();
		this.id = id;
		this.sequence = sequence;
		this.userId = userId;
	}
	
}
