package com.promineotech.mechanicApi.service;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.promineotech.mechanicApi.entity.Credentials;
import com.promineotech.mechanicApi.entity.User;
import com.promineotech.mechanicApi.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User register(Credentials credentials) throws AuthenticationException {
		User user = new User();
		user.setUsername(credentials.getUsername());
		user.setHash(BCrypt.hashpw(credentials.getPassword(), BCrypt.gensalt()));
		try {
			userRepository.save(user);
			return user;
		} catch(DataIntegrityViolationException e) {
			throw new AuthenticationException("Username unavailable.");
		}
		
	}
	
	public User login(Credentials credentials) throws AuthenticationException{
		User foundUser = userRepository.findByUsername(credentials.getUsername());
		if(foundUser != null && BCrypt.checkpw(credentials.getPassword(), foundUser.getHash())){
			return foundUser;
		}
		throw new AuthenticationException("Invalid username or password.");
	}

}
