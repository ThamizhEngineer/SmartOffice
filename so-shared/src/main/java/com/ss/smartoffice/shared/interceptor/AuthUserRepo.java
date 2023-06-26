package com.ss.smartoffice.shared.interceptor;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.AuthUserSummary;

public interface AuthUserRepo extends CrudRepository<AuthUserSummary, Integer> {
	List<AuthUserSummary> findByUserName(@RequestParam("userName") String userName);
	List<AuthUserSummary> findByUserNameAndAppClientId(@RequestParam("userName") String userName,@RequestParam("appClientId") String appClientId);
   List<AuthUserSummary> findByUserNameAndPassword(String userName,String password);
   List<AuthUserSummary> findByEmployeeId(String employeeId);
   List<AuthUserSummary> findByApplicantId(String applicantId);
   List<AuthUserSummary> findByPartnerId(String partnerId);
List<AuthUserSummary> save(String user2);

}
