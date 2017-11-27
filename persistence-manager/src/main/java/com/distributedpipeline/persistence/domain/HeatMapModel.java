package com.distributedpipeline.persistence.domain;

import java.util.List;
import java.util.Map;

public class HeatMapModel {
	
	private Map<String, List<WorkFlow_Frequency>> user;

	public HeatMapModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HeatMapModel(Map<String, List<WorkFlow_Frequency>> user) {
		super();
		this.user = user;
	}

	public Map<String, List<WorkFlow_Frequency>> getUser() {
		return user;
	}

	public void setUser(Map<String, List<WorkFlow_Frequency>> user) {
		this.user = user;
	}

}
