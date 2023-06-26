package com.ss.smartoffice.soauthservice.auth.menu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soauthservice.model.auth.AuthMenu;

public interface AuthMenuFeatureRepository extends CrudRepository<AuthMenu, String> {
List<AuthMenu> findByUserId(String userId);
List<AuthMenu> findByUserIdAndFeatureId(String userId,String featureId);


}
