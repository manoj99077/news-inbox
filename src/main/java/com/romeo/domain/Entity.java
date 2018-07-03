package com.romeo.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties
@Document
public abstract class Entity implements Serializable{

	private static final long serialVersionUID = 3629443246292499452L;
	
	@Id
	private String id;
	
	@NotNull
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@Indexed
	private Date creationDate;
	
	@NotNull
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@Indexed
	private Date modificationDate; 
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	
	
}
