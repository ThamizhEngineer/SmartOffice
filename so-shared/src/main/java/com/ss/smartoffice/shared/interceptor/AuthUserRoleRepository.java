package com.ss.smartoffice.shared.interceptor;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.shared.model.AuthUserRole;



public interface AuthUserRoleRepository extends CrudRepository<AuthUserRole, Integer> {
	List<AuthUserRole> findByAuthUserId(String authUserId);
	
	@Transactional
	@Modifying
	@Query("delete from com.ss.smartoffice.shared.model.AuthUserRole role where role.authUserId=:authUserId")
	void deleteByAuthId(@Param("authUserId") String authUserId);
	
	@Query("select DISTINCT role.authRoleId from com.ss.smartoffice.shared.model.AuthUserRole role where role.authUserId=:authUserId")
	List<Integer> authUserRole(String authUserId);
	
}
