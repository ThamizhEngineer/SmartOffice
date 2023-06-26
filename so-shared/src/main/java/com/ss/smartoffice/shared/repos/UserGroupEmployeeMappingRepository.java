package com.ss.smartoffice.shared.repos;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import com.ss.smartoffice.shared.model.UserGroupEmployeeMapping;

@Scope("prototype")
public interface UserGroupEmployeeMappingRepository extends CrudRepository<UserGroupEmployeeMapping,Integer> {
	List<UserGroupEmployeeMapping> findByEmployeeId(String employeeId);
	List<UserGroupEmployeeMapping> findByUserGroupId(String userGroupId);
}
