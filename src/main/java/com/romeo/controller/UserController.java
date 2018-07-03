package com.romeo.controller;
 
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.romeo.domain.User;
import com.romeo.repository.UserRepository;
import com.romeo.service.UserService;
import com.romeo.util.CAU;
 

@RestController
@RequestMapping("/user")
public class UserController {

	
	
  @Autowired
  private UserRepository userRepository;
  
 @Autowired
 UserService userService;
   
  @RequestMapping(method = RequestMethod.POST, value="/save")
  public @ResponseBody User save(@RequestBody User user){  
	  		user.setCreationDate(new Date());
	return  userRepository.save(user);
    
     
  }
  
  @RequestMapping(method = RequestMethod.POST, value="/login")
  public @ResponseBody User login(HttpServletRequest request,@RequestBody User user){  
	  		user.setCreationDate(new Date());
	  		
	  		HttpSession session = null;
	  		session = request.getSession(false);
	  		session.setAttribute("loggedInUser",user);
	return  userRepository.save(user);
    
     
  }
 

  @RequestMapping(method = RequestMethod.GET, value="/{id}")
  public User findOne(@PathVariable("id") String id){
	    return userRepository.findOne(id);
  }
  
  @RequestMapping(method = RequestMethod.GET, value="/findAll")
  public List<User> findAll(){
	    return userRepository.findAll();
  }
  
  @RequestMapping(method = RequestMethod.GET, value="/filterUser")
  public List<User> filterUser(){
	String  id = "5a6f65a72f7878cd11e45413";
	  User user =  userRepository.findOne(id);
	  return userService.filterUser(user);
	  
	   // return userRepository.findAll();
  }
  
  @RequestMapping(method = RequestMethod.POST, value="/delete/{id}")
  public Boolean deleteById(@PathVariable("id") String id){
	  userRepository.delete(id);
	    return true;
  }
}
