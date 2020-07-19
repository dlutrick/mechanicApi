package com.promineotech.mechanicApi.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.mechanicApi.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	public User findByUsername(String username);

}
