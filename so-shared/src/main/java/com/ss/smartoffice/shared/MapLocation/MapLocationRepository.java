package com.ss.smartoffice.shared.MapLocation;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MapLocationRepository extends CrudRepository<MapLocation, Integer>{
	@Query("select new com.ss.smartoffice.shared.MapLocation.MapLocation(ml.id,ml.locName,ml.address,ml.lats,ml.longs,ml.locType,ml.country)"
			+ " from MapLocation ml where ifnull(LOWER(ml.locName),'') LIKE LOWER(CONCAT('%',ifnull(:locName,''),'%')) "
			+ "AND ifnull(LOWER(ml.address),'') LIKE LOWER(CONCAT('%',ifnull(:address,''), '%')) "
			+"AND ifnull(LOWER(ml.locType),'') LIKE LOWER(CONCAT('%',ifnull(:locType,''), '%')) "
			+"AND ifnull(LOWER(ml.country),'') LIKE LOWER(CONCAT('%',ifnull(:country,''), '%')) ")
	List<MapLocation> findByMapLocation(@Param("locName")String locName,@Param("address")String address,@Param("locType")String locType,@Param("country")String country);
	
	@Query("select mapLoc from  com.ss.smartoffice.shared.MapLocation.MapLocation mapLoc where "
			+ "ifnull(LOWER(mapLoc.lats),'') LIKE LOWER(CONCAT('%',ifnull(:lats,''), '%')) "
			+"AND ifnull(LOWER(mapLoc.longs),'') LIKE LOWER(CONCAT('%',ifnull(:longs,''), '%')) "
			+ "OR LOWER(mapLoc.locName)=:locName And LOWER(mapLoc.address)=:address")
	List<MapLocation> duplicateCheck(@Param("lats")float lats,@Param("longs")float longs,@Param("locName")String locName,@Param("address")String address);
}
