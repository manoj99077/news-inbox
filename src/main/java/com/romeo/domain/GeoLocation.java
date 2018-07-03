package com.romeo.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;

 
 
@CompoundIndexes({
    @CompoundIndex(name = "geolocation_createorid", def = "{ 'creator': 1 }")
})
public class GeoLocation extends Entity implements Serializable {
 
	private static final long serialVersionUID = 8938888897293855766L;

	private String name;
	
	private String city;

	private String state;

	private String country;

	private String zipcode;
	
	private String street;
	
	private String streetNumber;
	
	private String subLocality3;
	
	private String subLocality1;
	
	private String subLocality2;
	//google specific
	private String formattedAddress;
	private String formattedPhoneNumber;
	private String icon;
	private String googleId;
	@Indexed
	private String googlePlaceId;
	private String internationalPhoneNumber;
	private String googleRating;
	private String url;
	private String website;
	private String vicinity;
	private String continent;
	
	private Map<String,String> metaLocation ;
 
    
    //@DBRef
    //@JsonBackReference
	//private User creator;
    private String creatorId;
	
	 

	/**
	 * coordinates -longitude = coordinates[0] & latitude = coordinates[1]
	 */
	private double[] coordinates;

 
	
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the coordinates
	 */
	public double[] getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates
	 *            the coordinates to set
	 */
	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * 
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode
	 *            the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 
	
	

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber() {
		return streetNumber;
	}

	/**
	 * @param streetNumber the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	 
	 
	public String getCreatorId() {
		return creatorId;
	}
	
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * @return the subLocality3
	 */
	public String getSubLocality3() {
		return subLocality3;
	}

	/**
	 * @param subLocality3 the subLocality3 to set
	 */
	public void setSubLocality3(String subLocality3) {
		this.subLocality3 = subLocality3;
	}

	/**
	 * @return the subLocality1
	 */
	public String getSubLocality1() {
		return subLocality1;
	}

	/**
	 * @param subLocality1 the subLocality1 to set
	 */
	public void setSubLocality1(String subLocality1) {
		this.subLocality1 = subLocality1;
	}

	/**
	 * @return the subLocality2
	 */
	public String getSubLocality2() {
		return subLocality2;
	}

	/**
	 * @param subLocality2 the subLocality2 to set
	 */
	public void setSubLocality2(String subLocality2) {
		this.subLocality2 = subLocality2;
	}

	 
	
	public void getFullAddress(){
		StringBuffer fullAddress = new StringBuffer("");
		if(!StringUtils.isEmpty(name)){
			fullAddress.append(name).append(" ");
		}
		if(!StringUtils.isEmpty(street)){
			fullAddress.append(street).append(" ");
		}
		if(!StringUtils.isEmpty(streetNumber)){
			fullAddress.append(streetNumber).append(" ");
		}
		if(!StringUtils.isEmpty(subLocality3)){
			fullAddress.append(subLocality3).append(" ");
		}
		if(!StringUtils.isEmpty(subLocality2)){
			fullAddress.append(subLocality2).append(" ");
		}
		if(!StringUtils.isEmpty(subLocality1)){
			fullAddress.append(subLocality1).append(" ");
		}
		if(!StringUtils.isEmpty(city)){
			fullAddress.append(city).append(" ");
		}
		if(!StringUtils.isEmpty(state)){
			fullAddress.append(state).append(" ");
		}
		if(!StringUtils.isEmpty(country)){
			fullAddress.append(country).append(" ");
		}
		if(!StringUtils.isEmpty(zipcode)){
			fullAddress.append(zipcode).append(" ");
		}
		
		this.name=fullAddress.toString();
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public String getFormattedPhoneNumber() {
		return formattedPhoneNumber;
	}

	public void setFormattedPhoneNumber(String formattedPhoneNumber) {
		this.formattedPhoneNumber = formattedPhoneNumber;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public String getGooglePlaceId() {
		return googlePlaceId;
	}

	public void setGooglePlaceId(String googlePlaceId) {
		this.googlePlaceId = googlePlaceId;
	}

	public String getInternationalPhoneNumber() {
		return internationalPhoneNumber;
	}

	public void setInternationalPhoneNumber(String internationalPhoneNumber) {
		this.internationalPhoneNumber = internationalPhoneNumber;
	}

	public String getGoogleRating() {
		return googleRating;
	}

	public void setGoogleRating(String googleRating) {
		this.googleRating = googleRating;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getVicinity() {
		return vicinity;
	}

	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}

	public Map<String, String> getMetaLocation() {
		if(metaLocation==null) metaLocation = new HashMap<>();
		return metaLocation;
	}

	public void setMetaLocation(Map<String, String> metaLocation) {
		if(metaLocation==null) metaLocation = new HashMap<>();
		this.metaLocation = metaLocation;
	}
	
	
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String constructedAddress(){
		StringBuilder sb = new StringBuilder("");
		if(!StringUtils.isEmpty(this.getStreet())){
			sb.append(this.getStreet()).append(" ");
		}
		if(!StringUtils.isEmpty(this.getStreetNumber())){
			sb.append(this.getStreetNumber()).append(" ");
		}
		if(!StringUtils.isEmpty(this.getSubLocality1())){
			sb.append(this.getSubLocality1()).append(" ");
		}
		if(!StringUtils.isEmpty(this.getSubLocality2())){
			sb.append(this.getSubLocality2()).append(" ");
		}
		if(!StringUtils.isEmpty(this.getSubLocality3())){
			sb.append(this.getSubLocality3()).append(" ");
		}
		if(!StringUtils.isEmpty(this.getCity())){
			sb.append(this.getCity()).append(" ");
		}
		if(!StringUtils.isEmpty(this.getState())){
			sb.append(this.getState()).append(" ");
		}
		if(!StringUtils.isEmpty(this.getCountry())){
			sb.append(this.getCountry()).append(" ");
		}
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof GeoLocation ){
			GeoLocation location = (GeoLocation)obj;
			return location.getId().equals(this.getId());
		}
		return false;
	}
	@Override
	public String toString() {
		return "GeoLocation [name=" + name + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", zipcode=" + zipcode + ", street=" + street + ", streetNumber=" + streetNumber + ", subLocality3="
				+ subLocality3 + ", subLocality1=" + subLocality1 + ", subLocality2=" + subLocality2
				+ ", formattedAddress=" + formattedAddress + ", formattedPhoneNumber=" + formattedPhoneNumber
				+ ", icon=" + icon + ", googleId=" + googleId + ", googlePlaceId=" + googlePlaceId
				+ ", internationalPhoneNumber=" + internationalPhoneNumber + ", googleRating=" + googleRating + ", url="
				+ url + ", website=" + website + ", vicinity=" + vicinity + ", continent=" + continent
				+ ", metaLocation=" + metaLocation 
				+ ", creatorId=" + creatorId + ", coordinates="
				+ Arrays.toString(coordinates)+"]";
	}
	
}
