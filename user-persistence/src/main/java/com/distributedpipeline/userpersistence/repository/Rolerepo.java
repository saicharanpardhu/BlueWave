package com.distributedpipeline.userpersistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.distributedpipeline.userpersistence.domain.Role;

@Repository
public interface Rolerepo extends CrudRepository<Role, Long>{
	Role findByRole(String role);
	
//	User findByUsername(String username);
}
