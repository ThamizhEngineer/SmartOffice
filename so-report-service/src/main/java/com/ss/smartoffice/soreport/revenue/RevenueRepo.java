package com.ss.smartoffice.soreport.revenue;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RevenueRepo extends CrudRepository<com.ss.smartoffice.shared.model.Revenue, Integer> {

	@Query("select x from com.ss.smartoffice.shared.model.Revenue x "
			+ "where ifnull(LOWER(x.soCode),'') LIKE LOWER(CONCAT('%',ifnull(:soCode,''),'%')) AND  "
			+ "ifnull(LOWER(x.invoiceCode),'') LIKE LOWER(CONCAT('%',ifnull(:invoiceCode,''),'%')) AND  "
			+ "ifnull(LOWER(x.monthNo),'') LIKE LOWER(CONCAT('%',ifnull(:monthNo,''),'%')) AND  "
			+ "ifnull(LOWER(x.year),'') LIKE LOWER(CONCAT('%',ifnull(:year,''),'%')) AND  "
			+ "ifnull(LOWER(x.quarterName),'') LIKE LOWER(CONCAT('%',ifnull(:quarterName,''),'%')) AND  "
			+ "ifnull(LOWER(x.buName),'') LIKE LOWER(CONCAT('%',ifnull(:buName,''),'%')) AND  "
			+ "ifnull(LOWER(x.divisionName),'') LIKE LOWER(CONCAT('%',ifnull(:divisionName,''),'%')) AND  "
			+ "ifnull(LOWER(x.functionName),'') LIKE LOWER(CONCAT('%',ifnull(:functionName,''),'%')) AND  "
			+ "ifnull(LOWER(x.clientCode),'') LIKE LOWER(CONCAT('%',ifnull(:clientCode,''),'%')) AND  "
			+ "ifnull(LOWER(x.clientName),'') LIKE LOWER(CONCAT('%',ifnull(:clientName,''),'%')) AND  "
			+ "ifnull(LOWER(x.countryName),'') LIKE LOWER(CONCAT('%',ifnull(:countryName,''),'%')) AND  "
			+ "ifnull(LOWER(x.jobCodes),'') LIKE LOWER(CONCAT('%',ifnull(:jobCodes,''),'%'))")
	List<com.ss.smartoffice.shared.model.Revenue> summary(String soCode, String invoiceCode, String monthNo, String year,String quarterName,
			String buName, String divisionName, String functionName, String clientCode, String clientName,
			String countryName, String jobCodes);

}
