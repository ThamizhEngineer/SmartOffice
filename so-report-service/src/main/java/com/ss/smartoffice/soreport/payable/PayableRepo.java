package com.ss.smartoffice.soreport.payable;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.Payable;

public interface PayableRepo extends CrudRepository<Payable, Integer> {

	
	@Query("select x from com.ss.smartoffice.shared.model.Payable x where "
			+ "ifnull(LOWER(x.poCode),'') LIKE LOWER(CONCAT('%',ifnull(:poCode,''),'%')) AND "
			+ "ifnull(LOWER(x.vendorId),'') LIKE LOWER(CONCAT('%',ifnull(:vendorId,''),'%')) AND "
			+ "ifnull(LOWER(x.vendorCode),'') LIKE LOWER(CONCAT('%',ifnull(:vendorCode,''),'%')) AND "
			+ "ifnull(LOWER(x.vendorName),'') LIKE LOWER(CONCAT('%',ifnull(:vendorName,''),'%')) AND "
			+ "ifnull(LOWER(x.monthNo),'') LIKE LOWER(CONCAT('%',ifnull(:monthNo,''),'%')) AND  "
			+ "ifnull(LOWER(x.year),'') LIKE LOWER(CONCAT('%',ifnull(:year,''),'%')) AND  "
			+ "ifnull(LOWER(x.quarterName),'') LIKE LOWER(CONCAT('%',ifnull(:quarterName,''),'%')) AND  "
			+ "ifnull(LOWER(x.countryName),'') LIKE LOWER(CONCAT('%',ifnull(:countryName,''),'%'))")
	List<Payable> summary(String poCode,String vendorId,String vendorCode,String vendorName,
			String monthNo, String year,String quarterName,String countryName);
}
