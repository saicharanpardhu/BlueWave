package com.distributedpipeline.userpersistence.domain;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Projects {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String projectname;
	
	@ManyToMany(mappedBy = "projects" ,fetch=FetchType.LAZY)
    private Set<User> users;
	
	
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
	
	

}
