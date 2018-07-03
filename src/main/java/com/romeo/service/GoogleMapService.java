package com.romeo.service;

import java.util.Map;

import   com.romeo.domain.GeoLocation;
 
public interface GoogleMapService {

	public Map getGoogleLocations(String query);
	
	public Map resolveGooglePlaceById(String id);
	
	public GeoLocation convertFirstToGeolocation(Map map);
	 
}
