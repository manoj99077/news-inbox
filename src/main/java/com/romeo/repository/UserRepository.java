package com.romeo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.romeo.domain.User;

 
public interface UserRepository  extends MongoRepository<User, String>,UserRepositoryCustom{
	
	Page<User> findAll(Pageable pageable);

	 
	 
 
 	
 
}