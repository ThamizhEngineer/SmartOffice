package com.ss.smartoffice.soauthservice.auth.role;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soauthservice.model.auth.AuthRoleFeature;

public interface AuthRoleFeatureRepository extends CrudRepository<AuthRoleFeature, Integer> {

	List<AuthRoleFeature> findByAuthRoleId(Integer authRoleId);
	List<AuthRoleFeature> findByAuthFeatureId(String authFeatureId);
	List<AuthRoleFeature> findByAuthRoleIdAndAuthFeatureId(Integer authRoleId,String authFeatureId);
}
