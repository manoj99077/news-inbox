 package com.romeo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romeo.domain.User;
import com.romeo.repository.UserRepository;
import com.romeo.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements  UserService {
	
	@Autowired
	protected UserRepository userRepository;


	@Override
	public List<User> filterUser(User user) {		  
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		return  userRepository.findAllByFilters(map);
		   	
	}

 
}


