package com.romeo.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.romeo.service.GoogleMapService;
 
@RestController
@RequestMapping(value = "/gmap")
public class GoogleMapController {
	
	@Autowired GoogleMapService googleMapService;
	/**
	 * Returns A Map Bean by taking String Query as parameter
	 * @param query
	 * @return Map Bean
	 * @throws IOException
	 * first step 
	 */
	@RequestMapping(value = "/getGoogleLocations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map getGoogleLocations(@RequestParam("query") String query)
			throws IOException {
		return googleMapService.getGoogleLocations(query);
	}
	
 
	/**
	 * 
	 * @param id
	 * @return Map
	 * @throws IOException
	 */
	@RequestMapping(value = "/resolveGooglePlaceById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map resolveGooglePlaceById(@RequestParam("id") String id)
			throws IOException {
		return googleMapService.resolveGooglePlaceById(id);
	}


}
