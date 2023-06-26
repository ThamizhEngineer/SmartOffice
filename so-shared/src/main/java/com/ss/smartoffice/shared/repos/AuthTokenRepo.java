package com.ss.smartoffice.shared.repos;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.shared.model.AuthToken;
@Transactional
@Scope("prototype")
public interface AuthTokenRepo extends CrudRepository<AuthToken, Integer>{
	@Query( "select a from com.ss.smartoffice.shared.model.AuthToken a where a.appToken = :appToken" )
	AuthToken fetchByAuthToken(String appToken);
	
	@Query( "select a from com.ss.smartoffice.shared.model.AuthToken a where a.authUserId = :authUserId AND a.applicationCode =:applicationCode order by a.id DESC" )
	List<AuthToken> findByAuthUserIdAndApplicationCode(Integer authUserId,String applicationCode);
	
	@Transactional
	@Modifying
	@Query("DELETE From com.ss.smartoffice.shared.model.AuthToken auth where auth.authUserId=:authId AND auth.applicationCode<>'async'")
	void deleteTokes(Integer authId);
	
	@Transactional
	@Modifying
	@Query("DELETE From com.ss.smartoffice.shared.model.AuthToken auth where auth.authUserId=:authId ")
	void deleteByAuthId(Integer authId);
}
