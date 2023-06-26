package com.ss.smartoffice.soservice.master.partnerservice;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.partner.PartnerContact;
@Scope("prototype")
public interface PartnerContactRepository extends CrudRepository<PartnerContact, Integer>{
	@Modifying
	@Query("DELETE from com.ss.smartoffice.shared.model.partner.PartnerContact partnerContact where partnerContact.id= ?1")
	void delete(int id);
}
