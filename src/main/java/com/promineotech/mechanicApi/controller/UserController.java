package com.promineotech.mechanicApi.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.mechanicApi.entity.Credentials;
import com.promineotech.mechanicApi.entity.User;
import com.promineotech.mechanicApi.service.AuthService;
import com.promineotech.mechanicApi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<Object> register(@RequestBody Credentials credentials){
		try {
			return new ResponseEntity<Object>(authService.register(credentials), HttpStatus.CREATED);
		} catch(AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody Credentials credentials){
		try {
			return new ResponseEntity<Object>(authService.login(credentials), HttpStatus.OK);
		} catch(AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createUser(@RequestBody User user){
		return new ResponseEntity<Object>(service.createUser(user), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getUsers(){
		return new ResponseEntity<Object>(service.getUsers(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getUserById(@PathVariable Long id){
		return new ResponseEntity<Object>(service.getUser(id), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateUser(user, id), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Object>("Unable to update user.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUser(@PathVariable Long id){
		try {
			service.deleteUser(id);
			return new ResponseEntity<Object>("Successfully deleted user with id: " + id, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Object>("Unable to delete user.", HttpStatus.BAD_REQUEST);
		}
	}

}
