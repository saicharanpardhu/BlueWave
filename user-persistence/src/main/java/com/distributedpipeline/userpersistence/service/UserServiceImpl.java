package com.distributedpipeline.userpersistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distributedpipeline.userpersistence.utility.LogExecutionTime;
import com.distributedpipeline.userpersistence.domain.User;
import com.distributedpipeline.userpersistence.repository.UserRepo;




@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	UserRepo userRepo;

	@Override
	@LogExecutionTime
	public User Signup(User user) {
		
		// TODO Auto-generated method stub
		userRepo.save(user);
		return user;
		
		
	}

	@Override
	@LogExecutionTime
	public void Login() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@LogExecutionTime
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email);
		
	}

//	@Override
//	public User FindByUsername(String username) {
//		// TODO Auto-generated method stub
//		
//		return userRepo.findByUsername(username);
//	}

//	@Override
//	public User FindByUsername(String username) {
//		// TODO Auto-generated method stub
//		return userRepo.findByUsername(username);
//	}

}
