package com.romeo.repository;

import java.util.List;
import java.util.Map;

import com.romeo.domain.User;

public interface UserRepositoryCustom{
	List<User> findAllByFilters(Map<String, Object> map);
 
}