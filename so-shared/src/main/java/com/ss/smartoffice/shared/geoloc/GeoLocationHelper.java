package com.ss.smartoffice.shared.geoloc;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mapbox.api.geocoding.v5.MapboxGeocoding;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.api.geocoding.v5.models.GeocodingResponse;
import com.mapbox.geojson.Point;
import com.ss.smartoffice.shared.MapLocation.MapLocation;
import com.ss.smartoffice.shared.MapLocation.MapLocationParameter;
import com.ss.smartoffice.shared.MapLocation.MapLocationParameterRepository;
import com.ss.smartoffice.shared.common.CommonUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Component
public class GeoLocationHelper {
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	MapLocationParameterRepository  mapLocationParameterRepository;
	
	@Value("${map-box-api-key}")
	String mapBoxApiKey ;
	
	public List<MapLocation> forwardGeoPostioning( String locName, String address, String countryCode,  int limit){
		List<MapLocation> mapLocations = new ArrayList<MapLocation>();
			limit=1;
			MapLocationParameter mapLocationParameterSearch =mapLocationParameterRepository.findByAddressAndCountryCodeAndStatusAndLocName(address, countryCode,"COMPLETED",locName);
			
			if(mapLocationParameterSearch==null) {
				MapLocationParameter  mapLocationParameter= new MapLocationParameter();
				mapLocationParameter.setAddress(address);
				mapLocationParameter.setCountryCode(countryCode);
				mapLocationParameter.setLimitParameter(limit);
				mapLocationParameter.setStatus("PROCESSING");
				mapLocationParameter.setLocName(locName);
				mapLocationParameterRepository.save(mapLocationParameter);
				
		MapboxGeocoding mapboxGeocoding = MapboxGeocoding.builder()
				.accessToken(mapBoxApiKey)
				.query(address)
				.limit(limit)
				.country(countryCode)
				.build();

		mapboxGeocoding.enqueueCall(new Callback<GeocodingResponse>() {
			@Override
			public void onResponse(Call<GeocodingResponse> call, Response<GeocodingResponse> response) {
		 
		
				List<CarmenFeature> results = response.body().features();
				MapLocationParameter mapLocationParameter =mapLocationParameterRepository.findByAddressAndCountryCodeAndStatusAndLocName(address, countryCode,"PROCESSING",locName);

					Point firstResultPoint = results.get(0).center();
		
					if(mapLocationParameter!=null) {
						mapLocationParameter.setLats((float)firstResultPoint.latitude());
						mapLocationParameter.setLongs((float)firstResultPoint.longitude());
						mapLocationParameter.setStatus("COMPLETED");
						mapLocationParameter.setIsCompleted("Y");
						mapLocationParameter.setIsError("N");
						mapLocationParameterRepository.save(mapLocationParameter);
	
					}
		
			}
			
			@Override
			public void onFailure(Call<GeocodingResponse> call, Throwable throwable) {
				MapLocationParameter mapLocationParameter =mapLocationParameterRepository.findByAddressAndCountryCodeAndStatusAndLocName(address, countryCode,"PROCESSING",locName);

			
	
				if(mapLocationParameter!=null) {
				
					mapLocationParameter.setStatus("ERROR");
					mapLocationParameter.setIsCompleted("N");
					mapLocationParameter.setIsError("Y");
					mapLocationParameterRepository.save(mapLocationParameter);

				}
	
				throwable.printStackTrace();
			}
		});

			}
		return mapLocations;
	}
		
	
	

}
