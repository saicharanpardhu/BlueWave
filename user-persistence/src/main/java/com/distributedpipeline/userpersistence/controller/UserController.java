package com.distributedpipeline.userpersistence.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;


import com.distributedpipeline.userpersistence.domain.Role;
import com.distributedpipeline.userpersistence.domain.User;
import com.distributedpipeline.userpersistence.service.UserService;
import com.distributedpipeline.userpersistence.repository.*;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

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
				
				if(uniqueEmail == null&&uniqueUserName == null) {
					
					Role role = new Role("USER");
					Set<Role> roles = new HashSet<Role>();
 			    	roles.add(role);
					UserDetail.setRoles(roles);
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
	
	@RequestMapping(value="/user/userName/{userName}", method = RequestMethod.GET)
	public ResponseEntity<?> getWorkFlowOfUser(@PathVariable("userName") String owner){
		return new ResponseEntity<User>(userService.findByUserName(owner), HttpStatus.OK);
	}
	
	@PutMapping(value="/user/updateuser" , consumes = "application/json")
	public ResponseEntity updateUser(@Valid @RequestBody User UserDetail)
	{
		if(UserDetail.getUserName() == null | UserDetail.getLastName() == null | UserDetail.getEmail() == null ) {
			
			return new ResponseEntity<String> ("one or more field is empty",HttpStatus.CONFLICT);
			
		}
		else {
//					Role role = rolerepo.findByRole("USER");
//					Set<Role> roles = new HashSet<Role>();
// 			    	roles.add(role);
//					UserDetail.setRoles(roles);
					
				/*	User user=userService.findByUserName(UserDetail.getUserName());
					
					if(UserDetail.getEmail()!=null )
					{
						if(Checkmail(UserDetail.getEmail()))
							user.setEmail(UserDetail.getEmail());
						else return new ResponseEntity<String> ("Invalid email",HttpStatus.OK);
					}
					
					if(UserDetail.getLastName()!=null)
						user.setLastName(UserDetail.getLastName());
					
					if(UserDetail.getFirstName()!=null)
						user.setFirstName(UserDetail.getFirstName());
				*/
			
					userService.Signup(UserDetail);
					return new ResponseEntity<String> ("Your profile is successfully updated, Thank you",HttpStatus.OK);							
			
		}
		
		
	}
	
	private boolean Checkmail(String email) {
		String emailValidator = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(emailValidator);
		
	}

}
