package com.romeo.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.romeo.domain.User;
import com.romeo.repository.UserRepositoryCustom;
import com.romeo.util.CAU;

public class UserRepositoryImpl implements UserRepositoryCustom  {
	

	@Autowired
	protected MongoTemplate mongoTemplate;
		
	
 
	@Override
	public List<User> findAllByFilters(Map<String, Object> params) {
		 Sort sort = new Sort(Direction.DESC,"creationDate");
		 int pageNumber = 0;
		  int pageSize = 10;		  
		  Pageable pageable = new PageRequest(pageNumber, pageSize);
		 Criteria criteria = null;
		  
	 User user = (User) params.get("user");
		  if(user.getGender().equalsIgnoreCase("Male")){
			    criteria = Criteria
					  .where("gender")
					  .is("Female");
		  }else{
			    criteria = Criteria
					  .where("gender")
					  .is("Male");
		  }
		  
		return findAll(criteria, pageable, sort, User.class);
	}
	
 
	
	protected Query getQuery(Criteria criteria, Pageable pageable, Sort sort) {
		Query query = new Query();
		if (criteria != null) {
			query = query.addCriteria(criteria);
		}
		if (pageable != null) {
			query = query.with(pageable);
		}
		if (sort != null) {
			query = query.with(sort);
		}
		return query;
	}
	
	
	protected List<User> findAll(Criteria criteria, Pageable pageable, Sort sort,Class<User> clazz) {
		Query query = getQuery(criteria, pageable, sort);
		List<User> result = mongoTemplate.find(query, clazz);
		if (!CAU.isNonEmptyList(result)) {
			return null;
		}
		return result;
	}
 
}
