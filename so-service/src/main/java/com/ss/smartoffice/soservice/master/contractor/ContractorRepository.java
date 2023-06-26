package com.ss.smartoffice.soservice.master.contractor;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface ContractorRepository extends CrudRepository<Contractor, Integer>{

	 @Query("SELECT new com.ss.smartoffice.soservice.master.contractor.Contractor(e.id, e.internalId,e.contractorCode,e.empTypeCode,e.firstName,e.lastName,e.emailId,\n" + 
	    		"	e.contactMobileNo,e.n1EmpId,e.managerName		\n" + 

	    		"			) FROM Contractor e where e.empTypeCode ='CONTRACTOR'") 
	    List<Contractor> findContractorSummaries();

}
