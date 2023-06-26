package com.ss.smartoffice.soservice.master.partnerservice;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.partner.PartnerDocument;
@Scope("prototype")
public interface PartnerDocumentRepository extends CrudRepository<PartnerDocument, Integer>{
	@Modifying
	@Query("DELETE from com.ss.smartoffice.shared.model.partner.PartnerDocument partnerDocument where partnerDocument.id= ?1")
	void delete(int id);
}
