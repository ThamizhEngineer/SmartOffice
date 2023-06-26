package com.ss.smartoffice.shared.interceptor;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.shared.model.AuthApplicant;
import com.ss.smartoffice.shared.model.AuthClient;
import com.ss.smartoffice.shared.model.AuthEmployee;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.AuthVendor;


@Transactional
@Scope("prototype")
public interface AuthUserRepository extends CrudRepository<AuthUser, Integer> {
	List<AuthUser> findByUserName(@RequestParam("userName") String userName);
	List<AuthUser> findByUserNameAndAppClientId(@RequestParam("userName") String userName,@RequestParam("appClientId") String appClientId);
   List<AuthUser> findByUserNameAndPassword(String userName,String password);
   List<AuthUser> findByEmployeeId(String employeeId);
   List<AuthUser> findByPartnerId(String partnerId);
   AuthUser findByApplicantId(String applicantId);
List<AuthUser> save(String user2);

@Query( "select auth from com.ss.smartoffice.shared.model.AuthUser auth where auth.empCode IS NOT NULL" )
List<AuthUser> findBySummary();

@Query("select auth from com.ss.smartoffice.shared.model.AuthUser auth where applicantId IS NOT NULL And auth.emailId =:emailId")
Optional<AuthUser> findByEmailId(String emailId);

@Query("select new com.ss.smartoffice.shared.model.AuthEmployee(au.id as authId,me.id as employeeId,CONCAT(me.empCode,'-',me.firstName,' ',me.lastName) as employee,au.emailId as emailId,"
		+ "me.empTypeCode as empType,me.empStatus as empStatus,au.userName as userName,au.userStatus as userStatus,au.userAccessDt as loginAccess) "
		+ "from com.ss.smartoffice.shared.model.employee.memployee me left join com.ss.smartoffice.shared.model.AuthUser au on me.id = au.employeeId")
List<AuthEmployee> fetchAllEmployeeUsers();

@Query("select new com.ss.smartoffice.shared.model.AuthApplicant(au.id as authId,app.id as applicantId,CONCAT(app.applicantCode,'-',app.firstName,' ',app.lastName) as employee,au.emailId as emailId,"
		+ "au.userName as userName,au.userStatus as userStatus ,au.userAccessDt as loginAccess) "
		+ "from com.ss.smartoffice.shared.model.applicant.Applicant app left join com.ss.smartoffice.shared.model.AuthUser au on app.id = au.applicantId")
List<AuthApplicant> fetchAllApplicantUsers();

@Query("select new com.ss.smartoffice.shared.model.AuthClient(au.id as authId,cus.id as partnerId,cusEmp.id as partnerEmpId,CONCAT(cusEmp.firstName,' ',cusEmp.lastName) as partnerEmpName,"
		+ "CONCAT(cus.clientCode,'-',cus.clientName) as client,au.emailId as emailId,au.userName as userName,au.userStatus as userStatus ,au.userAccessDt as loginAccess) "
		+ "from com.ss.smartoffice.shared.model.partner.PartnerEmployee cusEmp left join com.ss.smartoffice.shared.model.AuthUser au on cusEmp.id = au.partnerId left join "
		+ "com.ss.smartoffice.shared.model.partner.Partner cus on cusEmp.partnerId=cus.id where "
		+ "cus.isClient='Y'")
List<AuthClient> fetchAllClientUsers();

@Query("select new com.ss.smartoffice.shared.model.AuthVendor(au.id as authId,ven.id as partnerId,venEmp.id as partnerEmpId,CONCAT(venEmp.firstName,' ',venEmp.lastName) as partnerEmpName,"
		+ "CONCAT(ven.vendorCode,'-',ven.vendorName) as vendor,au.emailId as emailId,au.userName as userName,au.userStatus as userStatus ,au.userAccessDt as loginAccess) "
		+ "from com.ss.smartoffice.shared.model.partner.PartnerEmployee venEmp  left join com.ss.smartoffice.shared.model.AuthUser au on venEmp.id = au.partnerId "
		+ "left join com.ss.smartoffice.shared.model.partner.Partner ven on venEmp.partnerId=ven.id where "
		+ "ven.isVendor='Y'")
List<AuthVendor> fetchAllVendorUsers();

@Query("select new com.ss.smartoffice.shared.model.AuthClient(au.id as authId,cus.id as partnerId,cusEmp.id as partnerEmpId,CONCAT(cusEmp.firstName,' ',cusEmp.lastName) as partnerEmpName,"
		+ "CONCAT(cus.clientCode,'-',cus.clientName) as client,au.emailId as emailId,au.userName as userName,au.userStatus as userStatus ,au.userAccessDt as loginAccess) "
		+ "from com.ss.smartoffice.shared.model.partner.PartnerEmployee cusEmp left join com.ss.smartoffice.shared.model.AuthUser au on cusEmp.id = au.partnerId left join "
		+ "com.ss.smartoffice.shared.model.partner.Partner cus on cusEmp.partnerId=cus.id where "
		+ "au.partnerId=:partnerID")
List<AuthClient> authClientUserByPartnerID(@Param("partnerID")String partnerID);

@Query("select new com.ss.smartoffice.shared.model.AuthVendor(au.id as authId,ven.id as partnerId,venEmp.id as partnerEmpId,CONCAT(venEmp.firstName,' ',venEmp.lastName) as partnerEmpName,"
		+ "CONCAT(ven.vendorCode,'-',ven.vendorName) as vendor,au.emailId as emailId,au.userName as userName,au.userStatus as userStatus ,au.userAccessDt as loginAccess) "
		+ "from com.ss.smartoffice.shared.model.partner.PartnerEmployee venEmp left join com.ss.smartoffice.shared.model.AuthUser au on venEmp.id = au.partnerId "
		+ "left join com.ss.smartoffice.shared.model.partner.Partner ven on venEmp.partnerId=ven.id where "
		+ "au.partnerId=:partnerID")
List<AuthVendor> authVendorUserByPartnerID(@Param("partnerID")String partnerID);


@Transactional
@Modifying
@Query("delete from com.ss.smartoffice.shared.model.AuthUser auth where auth.userType='APPLICANT' AND auth.applicantId=:applicantId")
void deleteApplicant(@Param("applicantId") String applicantId); 

}
