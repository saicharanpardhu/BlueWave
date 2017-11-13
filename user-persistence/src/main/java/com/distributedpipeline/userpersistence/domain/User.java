package com.distributedpipeline.userpersistence.domain;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.distributedpipeline.userpersistence.domain.*;;


@Entity
public class User {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private long id;
	 private String firstName;
	 private String lastName;
	 private String userName;
	 
	private String email;
	 private String password;
	 
	 @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	 @JoinTable(joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "project_id"))
	 private Set<Projects> projects;
	 
	   @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	    @JoinTable(joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
	    private Set<Role> roles;
	   
	   
	  public User() {
		super();
	}
	  
	public User(String firstName, String lastName, String userName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		Role role = new Role(1,"USER");
		Set<Role> roles = new HashSet<Role>();
	    	roles.add(role);
	    	this.roles=roles;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Projects> getProjects() {
		return projects;
	}
	public void setProjects(Set<Projects> projects) {
		this.projects = projects;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	 

}
