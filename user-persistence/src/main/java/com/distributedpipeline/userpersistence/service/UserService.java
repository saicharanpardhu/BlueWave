package com.distributedpipeline.userpersistence.service;

import com.distributedpipeline.userpersistence.domain.User;

public interface UserService {
	
	public User Signup(User user);
	public void Login();
	public User findByEmail(String email);
//	public User findByUsername(String username);
	
	
	

}
