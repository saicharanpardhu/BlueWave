package com.distributedpipeline.userpersistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.distributedpipeline.userpersistence.domain.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{
	User findByEmail(String email);
	
	User findByUserName(String userName);
}
