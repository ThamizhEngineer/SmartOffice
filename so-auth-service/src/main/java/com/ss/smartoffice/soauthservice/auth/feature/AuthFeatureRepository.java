package com.ss.smartoffice.soauthservice.auth.feature;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.soauthservice.model.auth.AuthFeature;

public interface AuthFeatureRepository extends CrudRepository<AuthFeature,Integer > {
	
	@Query("select new com.ss.smartoffice.soauthservice.model.auth.AuthFeature(af.id,af.functionalityCode,af.featureCode,af.featureName,af.description,"
			+ "af.isEnabled,af.createdBy,af.modifiedBy,af.createdDt,af.modifiedDt)"
			+ "from AuthFeature  af where af.id=:id and af.functionalityCode='DASHBOARD'")
	List<AuthFeature> getById(@Param("id") Integer id);
	}
