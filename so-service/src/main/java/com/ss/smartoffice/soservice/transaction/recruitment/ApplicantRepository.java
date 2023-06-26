package com.ss.smartoffice.soservice.transaction.recruitment;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.applicant.Applicant;
@Scope("prototype")
public interface ApplicantRepository extends CrudRepository<Applicant, Integer> {
	 @Query("SELECT new com.ss.smartoffice.shared.model.applicant.Applicant(a.id,a.applicantName,a.applicantCode,a.firstName,a.lastName,a.contactNo,a.startDt,a.endDt,a.contactMobileNo,a.contactEmailId,a.isEnabled,a.loginUserId) FROM Applicant a") 
	    List<Applicant> findSummaries();
	 
	 List<Applicant> findBycollegeNameAndDegreeNameAndCourseNameAndPassedOut(String collegeName,String degreeName,String courseName,String passedOut);
Applicant findByContactEmailId(String contactEmailId);
Applicant findByEmailId(String emailId);



@Query("select ia from com.ss.smartoffice.shared.model.applicant.Applicant ia WHERE ia.contactMobileNo=:contactMobileNo OR ia.contactEmailId=:contactEmailId") 
//		+ "LOWER(ia.contactMobileNo) LIKE LOWER(CONCAT('%',:contactMobileNo,'%')) AND "
//		+ "LOWER(ia.contactEmailId) LIKE LOWER(CONCAT('%',:contactEmailId,'%'))")
Iterable<Applicant> fliter(String contactMobileNo, String contactEmailId);



//
//@Query("select ia from com.ss.smartoffice.shared.model.Applicant ia where ia.ApplicantId=:id AND "
//		+ "ifnull(LOWER(ia.emailId),'') LIKE LOWER(CONCAT('%',ifnull(:emailId,''),'%')) AND  "
//		+ "ifnull(LOWER(ia.contactMobileNo),'') LIKE LOWER(CONCAT('%',ifnull(:contactMobileNo,''),'%'))")
//List<Applicant> findByAdvanceSearch(String id, String emailId, String contactMobileNo);
}

