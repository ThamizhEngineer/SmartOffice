package com.ss.smartoffice.soauthservice.auth.role;

import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soauthservice.model.auth.AuthRole;

public interface AuthRoleRepository extends CrudRepository<AuthRole, String> {
	Iterable<AuthRole> findByRoleCode(String roleCode);

}
