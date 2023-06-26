package com.ss.smartoffice.soservice.transaction.websiteapplicant;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WebSiteApplicantRepository extends CrudRepository<WebSiteApplicant, Integer> {
	
	@Query("select ws from com.ss.smartoffice.soservice.transaction.websiteapplicant.WebSiteApplicant ws where ifnull(LOWER(ws.email),'') LIKE LOWER(CONCAT('%',ifnull(:email,''), '%')) AND ifnull(LOWER(ws.mobNum),'') LIKE LOWER(CONCAT('%',ifnull(:phNo,''), '%')) AND ifnull(LOWER(ws.vcId),'') LIKE LOWER(CONCAT('%',ifnull(:vcId,''), '%'))")
	List<WebSiteApplicant> findByemailAndVcIdAndMobNum(String email,String vcId,String phNo );
		
	@Query("select ws from com.ss.smartoffice.soservice.transaction.websiteapplicant.WebSiteApplicant ws where ifnull(LOWER(ws.email),'') LIKE LOWER(CONCAT('%',ifnull(:email,''), '%'))")
	List<WebSiteApplicant> findByemail(String email);
}
