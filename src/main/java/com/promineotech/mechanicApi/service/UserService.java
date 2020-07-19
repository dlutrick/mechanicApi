package com.promineotech.mechanicApi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.mechanicApi.entity.User;
import com.promineotech.mechanicApi.repository.UserRepository;

@Service
public class UserService {
	
	private static final Logger logger = LogManager.getLogger(UserService.class);
	
	@Autowired
	private UserRepository repo;
	
	public User createUser(User user) {
		return repo.save(user);
	}
	
	public User getUser(Long id) {
		return repo.findOne(id);
	}
	
	public Iterable<User> getUsers(){
		return repo.findAll();
	}
	
	public User updateUser(User user, Long id) throws Exception {
		try {
			User oldUser = repo.findOne(id);
			oldUser.setUsername(user.getUsername());
			oldUser.setEmail(user.getEmail());
			oldUser.setAddress(user.getAddress());
			oldUser.setPhoneNumber(user.getPhoneNumber());
			return repo.save(oldUser);
		} catch(Exception e) {
			logger.error("Exception occurred while trying to update user: " + id, e);
			throw new Exception("Unable to update user.");
		}
	}
	
	public void deleteUser(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch(Exception e) {
			logger.error("Exception occurred while trying to delete user: " + id, e);
			throw new Exception("Unable to delete user.");
		}
	}

}
