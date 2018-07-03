package com.romeo.service.impl;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romeo.domain.GeoLocation;
import com.romeo.service.GoogleMapService;
import com.romeo.util.CAU;
 
@Service(value = "googleMapService")
public class GoogleMapServiceImpl implements GoogleMapService {
	private final static Logger log = LoggerFactory.getLogger(GoogleMapServiceImpl.class);

	@Value("${gcmAppkey}")
	private String gcmAppkey;
	 
	@Override
	public Map getGoogleLocations(String query){
		try{

			HttpURLConnection request = (HttpURLConnection) (
					new URL("https://maps.googleapis.com/maps/api/place/autocomplete/json?key="+gcmAppkey+"&input="+
							CAU.encodeURIComponent(query))).openConnection();
			request.connect();
			LinkedHashMap gApiResponse = new ObjectMapper().readValue((InputStream)request.getContent(), LinkedHashMap.class);
			return gApiResponse;
		}
		catch(Exception e){
			log.error("Error while retrieving location from google", e);
		}
		return null;
	}
 
	@Override
	public Map resolveGooglePlaceById(String id){
		try{
			HttpURLConnection request = (HttpURLConnection) (
					new URL("https://maps.googleapis.com/maps/api/place/details/json?key="+gcmAppkey+"&placeid="+id)).openConnection();
			request.connect();
			Map gApiResponse = new ObjectMapper().readValue((InputStream)request.getContent(), Map.class);
			return gApiResponse;
		}
		catch(Exception e){
			log.error("Error while retrieving location from google", e);
		}
		return null;
	}
	
 
 

	@Override
	public GeoLocation convertFirstToGeolocation(Map map){
		if(map.size()<1)return null;
		try{
			ObjectMapper mapper = new ObjectMapper();
			 JSONObject jsonObj1 = new JSONObject(mapper.writeValueAsString(map)).getJSONArray("predictions").getJSONObject(0);
			  
			JSONObject jsonObj = new JSONObject(mapper.writeValueAsString(this.resolveGooglePlaceById(jsonObj1.getString("place_id")))).getJSONObject("result");
			GeoLocation location = new GeoLocation();
			location.setCoordinates(new double[]{jsonObj.getJSONObject("geometry").getJSONObject("location").getDouble("lng"),jsonObj.getJSONObject("geometry").getJSONObject("location").getDouble("lat")});
			JSONArray array =jsonObj.getJSONArray("address_components");
			for (int i = 0, size = array.length(); i < size; i++){
				String type = array.getJSONObject(i).getJSONArray("types").getString(0);
				JSONObject component = array.getJSONObject(i);
				if (type.equals("country")) {
					location.setCountry(component.getString("long_name"));
					//CAU.updateContinent(location);
				}
				if (type.equals("locality")) {
					location.setCity(component.getString("long_name"));
				}
				if (type.equals("administrative_area_level_1")) {
					location.setState(component.getString("long_name"));
				}
				if (type.equals("route")) {
					location.setStreet(component.getString("long_name"));
				}

				if (type.equals("street_number")) {
					location.setStreetNumber(component.getString("long_name"));
				}

				if (type.equals("sublocality_level_1")) {
					location.setSubLocality1(component.getString("long_name"));
				}

				if (type.equals("sublocality_level_2")) {
					location.setSubLocality2(component.getString("long_name"));
				}

				if (type.equals("sublocality_level_3")) {
					location.setSubLocality3(component.getString("long_name"));
				}
				if (type.equals("postal_code")) {
					location.setZipcode(component.getString("long_name"));
				}

			}

			location.setFormattedAddress(jsonObj.getString("formatted_address"));
			location.setFormattedPhoneNumber(jsonObj.isNull("formatted_phone_number")?null:jsonObj.getString("formatted_phone_number"));
			location.setIcon(jsonObj.isNull("icon")?null:jsonObj.getString("icon"));
			location.setGoogleId(jsonObj.isNull("id")?null:jsonObj.getString("id"));
			location.setGooglePlaceId(jsonObj.isNull("place_id")?null:jsonObj.getString("place_id"));
			location.setInternationalPhoneNumber(jsonObj.isNull("international_phone_number")?null:jsonObj.getString("international_phone_number"));
			location.setGoogleRating(jsonObj.isNull("rating")?null:jsonObj.getString("rating"));
			location.setName(jsonObj.isNull("name")?null:jsonObj.getString("name"));
			location.setVicinity(jsonObj.isNull("vicinity")?null:jsonObj.getString("vicinity"));
			location.setUrl(jsonObj.isNull("url")?null:jsonObj.getString("url"));
			location.setWebsite(jsonObj.isNull("website")?null:jsonObj.getString("website"));
			return location;
		}
		catch(Exception e){
			//log.error("Error while retrieving location from google", e);
		}
		return null;
	}
 

}
