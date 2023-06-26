package com.ss.smartoffice.soservice.seed.userGroups;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.shared.model.UserGroup;
@Scope("prototype")
public interface UserGroupRepo extends CrudRepository<UserGroup, Integer>{

	@Query("select new com.ss.smartoffice.shared.model.UserGroup(u.id,u.isHrL1,u.isHrL2,u.isAcctL1,u.isAcctL2,u.isDir,u.isAdmin,u.userGroupCode,u.userGroupName,u.description,u.officeId,u.officeName,u.isEnabled,u.createdBy,u.modifiedBy,u.createdDt,u.modifiedDt,u.isMgnt)"
			+ "from UserGroup u where ifnull(LOWER(u.isHrL1),'') LIKE LOWER(CONCAT('%',ifnull(:isHrL1,''),'%'))"
			+ "AND ifnull(LOWER(u.isHrL2),'') LIKE LOWER(CONCAT('%',ifnull(:isHrL2,''), '%'))"
			+ "AND ifnull(LOWER(u.isAcctL1),'') LIKE LOWER(CONCAT('%',ifnull(:isAcctL1,''), '%'))"
			+ "AND ifnull(LOWER(u.isAcctL2),'') LIKE LOWER(CONCAT('%',ifnull(:isAcctL2,''), '%'))"
			+ "AND ifnull(LOWER(u.userGroupName),'') LIKE LOWER(CONCAT('%',ifnull(:userGroupName,''), '%'))")
	List<UserGroup>findBySummaries(@Param("isHrL1")String isHrL1,@Param("isHrL2")String isHrL2,@Param("isAcctL1")String isAcctL1,@Param("isAcctL2")String isAcctL2,@Param("userGroupName")String userGroupName);
	
	@Query("select ug from com.ss.smartoffice.shared.model.UserGroup ug where ug.isAdmin='Y'")
	List<UserGroup>findByIsAdmin();
	
	@Query("select ug from com.ss.smartoffice.shared.model.UserGroup ug where ug.isHrL1='Y'")
	List<UserGroup>findByIsHrL1();
	
	UserGroup findByUserGroupCodeAndIsHrL1(String groupCode,String isHr1);
	UserGroup findByUserGroupCodeAndIsHrL2(String groupCode,String isHr2);
	UserGroup findByUserGroupCodeAndIsAcctL1(String groupCode,String isAcc1);
	UserGroup findByUserGroupCodeAndIsAcctL2(String groupCode,String isAcc2);
	UserGroup findByUserGroupCodeAndIsDir(String groupCode,String isHr1);
	
}
