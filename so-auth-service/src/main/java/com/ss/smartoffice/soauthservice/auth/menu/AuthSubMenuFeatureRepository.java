package com.ss.smartoffice.soauthservice.auth.menu;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.children;
import com.ss.smartoffice.shared.model.menu;
import com.ss.smartoffice.soauthservice.model.auth.AuthSubMenu;

public interface AuthSubMenuFeatureRepository extends CrudRepository<AuthSubMenu, String> {

//	Optional<AuthSubMenu> findById(String id);
	
	AuthSubMenu findByAuthFeatureId(String authFeatureId);
	@Query("SELECT new com.ss.smartoffice.shared.model.children(c.authMenuId,c.name,c.state,c.subState,c.type,c.authFeatureId,c.purpose,c.menuOrder) "
			+ "from com.ss.smartoffice.soauthservice.model.auth.AuthSubMenu c where c.authFeatureId=:authFeatureId AND c.isEnabled='Y'")
	List<children> findByAuthFeatureIde(String authFeatureId);
}
