package com.ss.smartoffice.soservice.master.partnerservice;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.shared.model.partner.Partner;


@Scope("prototype")
public interface PartnerRepository extends CrudRepository<Partner, Integer>{
	
	@Query("SELECT new com.ss.smartoffice.shared.model.partner.Partner (p.id,p.clientName,p.clientCode,p.isClient) FROM Partner p")
	 List<Partner>findBySummaries();
	@Query("select x from com.ss.smartoffice.shared.model.partner.Partner x where x.isClient=:isClient order by x.id DESC ")
	 List<Partner>findByIsClient(String isClient);
	 List<Partner> findByGstNoAndPriFirstNameAndPriLastName(String gstNo,String priFirstName,String priLastName);
	 
	 @Query("select x from com.ss.smartoffice.shared.model.partner.Partner x where x.referenceNumber=:referenceNumber")
	 List<Partner> fetchByReferenceNumber(@Param("referenceNumber")String referenceNumber);

	 @Query("select i from com.ss.smartoffice.shared.model.partner.Partner i "
				+ "where ifnull(LOWER(i.clientCode),'') LIKE LOWER(CONCAT('%',ifnull(:clientCode,''), '%'))  AND ifnull(LOWER(i.companyName),'') LIKE LOWER(CONCAT('%',ifnull(:companyName,''), '%')) AND ifnull(LOWER(i.countryName),'') LIKE LOWER(CONCAT('%',ifnull(:countryName,''), '%'))")
	List<Partner> fetchByAdvanceFilter(
				@Param("clientCode")String clientCode,@Param("companyName")String companyName,
				@Param("countryName")String countryName);
	 
	 @Query("select i from com.ss.smartoffice.shared.model.partner.Partner i "
				+ "where ifnull(LOWER(i.vendorCode),'') LIKE LOWER(CONCAT('%',ifnull(:vendorCode,''), '%'))  AND ifnull(LOWER(i.vendorName),'') LIKE LOWER(CONCAT('%',ifnull(:vendorName,''), '%')) AND ifnull(LOWER(i.countryName),'') LIKE LOWER(CONCAT('%',ifnull(:countryName,''), '%'))"
				+ "AND i.isClient=:isClient "
				 + "AND i.isVendor=:isVendor")
	List<Partner> fetchByAdvanceSearch(
				@Param("vendorCode")String vendorCode,@Param("vendorName")String vendorName,
				@Param("countryName")String countryName,@Param("isClient")String isClient,@Param("isVendor")String isVendor);
	 
}
