package com.romeo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romeo.domain.GeoLocation;
import com.romeo.repository.GeoLocationRepository;
import com.romeo.service.GeoLocationService;
 
@RestController
@RequestMapping(value="/locations")
public class LocationController {

	private final Logger log = LoggerFactory.getLogger(LocationController.class);


	@Autowired
	ObjectMapper objectMapper;
	 
	@Autowired
	GeoLocationService geoLocationService;

	@Autowired
	GeoLocationRepository geoLocationRepo;
  

	@RequestMapping
	public String getHomePage() {
		return "beatsByLocation";
	}

	/**
	 * Return the GeoLocation Bean by Taking the City Name
	 * @param city: String
	 * @return GeoLocation Bean
	 */

	@RequestMapping(value = "/id", method = RequestMethod.POST)
	public @ResponseBody GeoLocation getLocationWithId(@RequestParam("city") String city) {
		try {
			GeoLocation geoLocation = geoLocationService.findGeoLocationById(city);
			return geoLocation;
		} catch (Exception e) {
			 
		}
		return null;
	}
     
    
	@RequestMapping(value = "/getTotalLocationCount", method = RequestMethod.GET)
	public @ResponseBody
	long getTotalLocationCount()  {
		try {
			Long count = geoLocationRepo.count();
			return count;
		} catch (Exception ex) {
			log.error("Error while retrieving blogs", ex);
		}
		return 0;
	}  
      
	@RequestMapping(value = "/getLocationById", method = RequestMethod.GET)
	public @ResponseBody
	GeoLocation getLocationByCountry(@RequestParam("id") String id)  {
		if(id==null) return null;
		GeoLocation location = geoLocationRepo.findById(id);
		if(location==null)return null;
		location.getFullAddress();
		return location;
	}
      
	@RequestMapping(value = "/createMetaLocations", method = RequestMethod.POST)
	GeoLocation createMetaLocations(@RequestBody GeoLocation geoLocationDB){
		return geoLocationService.createMetaLocations(geoLocationDB);
	}  

}


