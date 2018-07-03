package com.romeo.service;

import com.romeo.domain.GeoLocation; 
 
public interface GeoLocationService {
 
	 
	GeoLocation findGeoLocationById(String geoLocationId);
	 
	GeoLocation createMetaLocations(GeoLocation geoLocation);
	   
 
	void addMetaLocations(GeoLocation geoLocation, String taggedBeatId);
	 

	void removeBeatMetaLocations(GeoLocation geoLocation, String taggedBeatId);
 
 
 
}
