package com.ss.smartoffice.soservice.master.partnerservice;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.partner.PartnerGst;
@Scope("prototype")
public interface PartnerGstRepository extends CrudRepository<PartnerGst, Integer>{
	@Modifying
	@Query("DELETE from com.ss.smartoffice.shared.model.partner.PartnerGst partnerGst where partnerGst.id= ?1")
	void delete(int id);
	List<PartnerGst> findByPartnerId(String partnerId);
}
