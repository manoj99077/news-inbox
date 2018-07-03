/**
 * 
 */
package com.romeo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.romeo.domain.GeoLocation;
import com.romeo.repository.GeoLocationRepository;
import com.romeo.service.GeoLocationService;
import com.romeo.service.GoogleMapService;
import com.romeo.util.CAU; 
 
@Service(value="geoLocationService")
public class GeoLocationServiceImpl implements GeoLocationService{
 
	@Autowired
	private GoogleMapService mapService;

	@Autowired
	private GeoLocationRepository geoLocationRepo;
 

	private Logger log = Logger.getLogger(GeoLocationServiceImpl.class);
 
	@Autowired private MongoTemplate mongoTemplate;
	   
 
	@Override
	public GeoLocation findGeoLocationById(String geoLocationId) {

		GeoLocation geoLocation = geoLocationRepo.findOne(geoLocationId);
		if (geoLocation != null) {
			return geoLocation;
		}

		return null; 
	}

	@Override
	public GeoLocation createMetaLocations(GeoLocation location) {
		String locationId = location.getId();
		if(!StringUtils.isEmpty(location.getCountry())){
			saveLocation(location, mapService.convertFirstToGeolocation(
					mapService.getGoogleLocations(cloneLocation(location,CAU.GEOLOCATION_COUNTRY).constructedAddress())),
					CAU.GEOLOCATION_COUNTRY);
		}
		if(!StringUtils.isEmpty(location.getState())){
			saveLocation(location, mapService.convertFirstToGeolocation(
					mapService.getGoogleLocations(cloneLocation(location,CAU.GEOLOCATION_STATE).constructedAddress())),
					CAU.GEOLOCATION_STATE);
		}
		if(!StringUtils.isEmpty(location.getCity())){
			saveLocation(location, mapService.convertFirstToGeolocation(
					mapService.getGoogleLocations(cloneLocation(location,CAU.GEOLOCATION_CITY).constructedAddress())),
					CAU.GEOLOCATION_CITY);
		}
		if(!StringUtils.isEmpty(location.getSubLocality1())){
			saveLocation(location, 
					mapService.convertFirstToGeolocation(mapService.getGoogleLocations(cloneLocation(location,CAU.GEOLOCATION_SUBLOCALITY1).constructedAddress())),
					CAU.GEOLOCATION_SUBLOCALITY1);
		}
		if(!StringUtils.isEmpty(location.getSubLocality2())){
			saveLocation(location, mapService.convertFirstToGeolocation(
					mapService.getGoogleLocations(cloneLocation(location,CAU.GEOLOCATION_SUBLOCALITY2).constructedAddress())),
					CAU.GEOLOCATION_SUBLOCALITY2);
		}
		if(!StringUtils.isEmpty(location.getSubLocality3())){
			saveLocation(location, mapService.convertFirstToGeolocation(
					mapService.getGoogleLocations(cloneLocation(location,CAU.GEOLOCATION_SUBLOCALITY3).constructedAddress())),
					CAU.GEOLOCATION_SUBLOCALITY3);
		}
		if(!StringUtils.isEmpty(location.getStreetNumber())){
			saveLocation(location, mapService.convertFirstToGeolocation(
					mapService.getGoogleLocations(cloneLocation(location,CAU.GEOLOCATION_STREET_NUMBER).constructedAddress())),
					CAU.GEOLOCATION_STREET_NUMBER);
		}
		if(!StringUtils.isEmpty(location.getStreet())){
			saveLocation(location, mapService.convertFirstToGeolocation(
					mapService.getGoogleLocations(cloneLocation(location,CAU.GEOLOCATION_STREET).constructedAddress())),
					CAU.GEOLOCATION_STREET);
		}
		removeDuplicate(location);
		GeoLocation geoLocationDB= geoLocationRepo.findByGooglePlaceId(location.getGooglePlaceId());
		if(geoLocationDB!=null) return geoLocationDB;
		//CAU.updateContinent(location);

		return geoLocationRepo.save(location);
	}
  
	private void removeDuplicate(GeoLocation location) {
		List<String> toRemove = new ArrayList<>();
		if(location.getMetaLocation().get(CAU.GEOLOCATION_STATE) !=null && location.getMetaLocation().get(CAU.GEOLOCATION_STATE).equals(location.getMetaLocation().get(CAU.GEOLOCATION_COUNTRY))){
			toRemove.add(CAU.GEOLOCATION_STATE);
		}
		if(location.getMetaLocation().get(CAU.GEOLOCATION_CITY) !=null && location.getMetaLocation().get(CAU.GEOLOCATION_CITY).equals(location.getMetaLocation().get(CAU.GEOLOCATION_STATE))){
			toRemove.add(CAU.GEOLOCATION_CITY);
		}
		if(location.getMetaLocation().get(CAU.GEOLOCATION_SUBLOCALITY1) !=null && location.getMetaLocation().get(CAU.GEOLOCATION_SUBLOCALITY1).equals(location.getMetaLocation().get(CAU.GEOLOCATION_CITY))){
			toRemove.add(CAU.GEOLOCATION_SUBLOCALITY1);
		}
		if(location.getMetaLocation().get(CAU.GEOLOCATION_SUBLOCALITY2) !=null && location.getMetaLocation().get(CAU.GEOLOCATION_SUBLOCALITY2).equals(location.getMetaLocation().get(CAU.GEOLOCATION_SUBLOCALITY1))){
			toRemove.add(CAU.GEOLOCATION_SUBLOCALITY2);
		}
		if(location.getMetaLocation().get(CAU.GEOLOCATION_SUBLOCALITY3) !=null && location.getMetaLocation().get(CAU.GEOLOCATION_SUBLOCALITY3).equals(location.getMetaLocation().get(CAU.GEOLOCATION_SUBLOCALITY2))){
			toRemove.add(CAU.GEOLOCATION_SUBLOCALITY3);
		}
		if(location.getMetaLocation().get(CAU.GEOLOCATION_STREET_NUMBER) !=null && location.getMetaLocation().get(CAU.GEOLOCATION_STREET_NUMBER).equals(location.getMetaLocation().get(CAU.GEOLOCATION_SUBLOCALITY3))){
			toRemove.add(CAU.GEOLOCATION_STREET_NUMBER);
		}
		if(location.getMetaLocation().get(CAU.GEOLOCATION_STREET) !=null && location.getMetaLocation().get(CAU.GEOLOCATION_STREET).equals(location.getMetaLocation().get(CAU.GEOLOCATION_STREET_NUMBER))){
			toRemove.add(CAU.GEOLOCATION_STREET);
		}
		for(String str :toRemove){
			location.getMetaLocation().remove(str);
		}
	}

	private void saveLocation(GeoLocation location, GeoLocation conLocation,String level) {
		if(conLocation!=null){
			GeoLocation geoLocationDB= geoLocationRepo.findByGooglePlaceId(conLocation.getGooglePlaceId());
			if(geoLocationDB!=null){
				location.getMetaLocation().put(level,geoLocationDB.getId());
				log.error("Existing location " + geoLocationDB.getId()+":"+geoLocationDB.getGooglePlaceId()+":"+geoLocationDB.getFormattedAddress());
			}
			else{
				conLocation.setCreatorId(location.getCreatorId());
				conLocation.setCreationDate(new Date());
				conLocation.setMetaLocation(location.getMetaLocation());
				location.getMetaLocation().put(level,geoLocationRepo.save(conLocation).getId());

				 


				log.error("Creating location " + conLocation.getId()+":"+conLocation.getGooglePlaceId()+":"+conLocation.getFormattedAddress());
			}
		}
	}	

	private GeoLocation cloneLocation(GeoLocation srcLocation,String level) {
		GeoLocation location = new GeoLocation();
		switch(level){
		case CAU.GEOLOCATION_STREET:location.setStreet(srcLocation.getStreet());
		case CAU.GEOLOCATION_STREET_NUMBER:location.setStreetNumber(srcLocation.getStreetNumber());
		case CAU.GEOLOCATION_SUBLOCALITY3:location.setSubLocality3(srcLocation.getSubLocality3());
		case CAU.GEOLOCATION_SUBLOCALITY2:location.setSubLocality2(srcLocation.getSubLocality2());
		case CAU.GEOLOCATION_SUBLOCALITY1:location.setSubLocality1(srcLocation.getSubLocality1());
		case CAU.GEOLOCATION_CITY:location.setCity(srcLocation.getCity());
		case CAU.GEOLOCATION_STATE:location.setState(srcLocation.getState());
		case CAU.GEOLOCATION_COUNTRY:location.setCountry(srcLocation.getCountry());
		}
		location.setGooglePlaceId(srcLocation.getGooglePlaceId());
		return location;
	}

  

	@Override
	public void addMetaLocations(GeoLocation geoLocation,String taggedBeatId) {
		if(geoLocation!=null){
			List<GeoLocation> toSave = new ArrayList<>();
			if(geoLocation.getMetaLocation()!=null && geoLocation.getMetaLocation().keySet()!=null){

				for(String level : geoLocation.getMetaLocation().keySet()){
					GeoLocation metaLocation = geoLocationRepo.findOne(geoLocation.getMetaLocation().get(level));
					 
					toSave.add(metaLocation);
				}
		 
				toSave.add(geoLocation);
				geoLocationRepo.save(toSave);
 
			}
		}
	}

	@Override
	public void removeBeatMetaLocations(GeoLocation geoLocation,String taggedBeatId) {
		List<GeoLocation> toSave = new ArrayList<>();
		for(String level : geoLocation.getMetaLocation().keySet()){
			GeoLocation metaLocation = geoLocationRepo.findOne(geoLocation.getMetaLocation().get(level));
			//metaLocation.removeTaggedBeatId(taggedBeatId);
			toSave.add(metaLocation);
		}
		//geoLocation.removeTaggedBeatId(taggedBeatId);
		toSave.add(geoLocation);
		geoLocationRepo.save(toSave);
 
	}
 
 
}
