package com.distributedpipeline.userpersistence.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.distributedpipeline.userpersistence.domain.Role;
import com.distributedpipeline.userpersistence.domain.User;
import com.distributedpipeline.userpersistence.service.UserService;
import com.distributedpipeline.userpersistence.repository.*;
import java.util.Set;
import java.util.HashSet;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v0.1/userinfo")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	Rolerepo rolerepo;
	
	@Autowired
	UserRepo userrepo;
	
	@PostMapping(value="/user" , consumes = "application/json")
	public ResponseEntity addUser(@Valid @RequestBody User UserDetail)
	{
		if(UserDetail.getUserName() == null | UserDetail.getLastName() == null | UserDetail.getEmail() == null ) {
			
			return new ResponseEntity<String> ("one or more field is empty",HttpStatus.CONFLICT);
			
		}
		else {
			String email = UserDetail.getEmail()  ;
			
			
			if(Checkmail(email)== true) {
				User uniqueEmail = userService.findByEmail(email);
				User uniqueUserName = userrepo.findByUserName(UserDetail.getUserName());
				
				if(uniqueEmail == null) {
					
//					Role role = rolerepo.findByRole("USER");
//					Set<Role> roles = new HashSet<Role>();
// 			    	roles.add(role);
//					UserDetail.setRoles(roles);
					userService.Signup(UserDetail);
					return new ResponseEntity<String> ("Your profile is successfully added, Thank you",HttpStatus.OK);

					

				}else {
					return new ResponseEntity<String>("email and/or username is already in use", HttpStatus.CONFLICT);
					
				}
				
			
			}
			else {
				return new ResponseEntity<String>("invalid email address", HttpStatus.CONFLICT);
				
			}
		}
		
		
	}
	
	private boolean Checkmail(String email) {
		String emailValidator = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(emailValidator);
		
	}

}
