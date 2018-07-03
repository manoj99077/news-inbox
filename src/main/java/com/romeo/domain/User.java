package com.romeo.domain;
 

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "user")
public class User extends Entity implements Serializable{

	private static final long serialVersionUID = 5716284465192987938L;
	
  private String name;
  
  @NotNull
  @Email
  @Indexed(unique = true)
  private String email;
  //private String phoneNumber;  
  private String userName;
  private String password;
  private String accountType; 
  private String gender;
  private String Status; 
  
  
  @DBRef
  private GeoLocation geolocation;
  
  @DateTimeFormat(iso=ISO.DATE_TIME)
  private Date dob;
  
  
  
  public User(){}



public String getName() {
	return name;
}



public void setName(String name) {
	this.name = name;
}



public String getEmail() {
	return email;
}



public void setEmail(String email) {
	this.email = email;
}


/*
public String getPhoneNumber() {
	return phoneNumber;
}



public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}*/



public String getUserName() {
	return userName;
}



public void setUserName(String userName) {
	this.userName = userName;
}



public String getPassword() {
	return password;
}



public void setPassword(String password) {
	this.password = password;
}



public String getAccountType() {
	return accountType;
}



public void setAccountType(String accountType) {
	this.accountType = accountType;
}



public String getGender() {
	return gender;
}



public void setGender(String gender) {
	this.gender = gender;
}



public String getStatus() {
	return Status;
}



public void setStatus(String status) {
	Status = status;
}



public GeoLocation getGeolocation() {
	return geolocation;
}



public void setGeolocation(GeoLocation geolocation) {
	this.geolocation = geolocation;
}



public Date getDob() {
	return dob;
}



public void setDob(Date dob) {
	this.dob = dob;
}
    
  

 
}
