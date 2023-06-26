package com.ss.smartoffice.soauthservice.model.auth;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.menu;

public interface AuthMenuRepo extends CrudRepository<AuthMenu, String> {

	@Query("SELECT new com.ss.smartoffice.shared.model.menu(authMenu.id,authMenu.name,authMenu.state,authMenu.type,authMenu.icon,authMenu.userId,authMenu.featureId,authMenu.menuOrder) "
			+ "from com.ss.smartoffice.soauthservice.model.auth.AuthMenu authMenu where authMenu.state=:stateId")
	List<menu> findByState(String stateId);
}
