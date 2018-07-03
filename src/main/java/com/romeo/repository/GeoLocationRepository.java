package com.romeo.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.romeo.domain.GeoLocation;

 
//@RepositoryRestResource
public interface GeoLocationRepository extends MongoRepository<GeoLocation, String>{

 
	GeoLocation findByCity(@Param("city") String city);
 
	GeoLocation findByCoordinates(double [] coordinates);
 

	GeoLocation findById(String id);

	GeoLocation findByGooglePlaceId(@Param("googlePlaceId") String id);
	
	
	
}
