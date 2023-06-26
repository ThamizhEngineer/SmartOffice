package com.ss.smartoffice.soreport.orderintake;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderInTakeRepo extends CrudRepository<com.ss.smartoffice.shared.model.OrderInTake, Integer> {

	@Query("select x from com.ss.smartoffice.shared.model.OrderInTake x "
			+ "where ifnull(LOWER(x.soCode),'') LIKE LOWER(CONCAT('%',ifnull(:soCode,''),'%')) AND  "
			+ "ifnull(LOWER(x.soOrderAmount),'') LIKE LOWER(CONCAT('%',ifnull(:soOrderAmount,''),'%')) AND  "
			+ "ifnull(LOWER(x.isVirtualPo),'') LIKE LOWER(CONCAT('%',ifnull(:isVirtualPo,''),'%')) AND  "
			+ "ifnull(LOWER(x.virtualPoNum),'') LIKE LOWER(CONCAT('%',ifnull(:virtualPoNum,''),'%')) AND  "
			+ "ifnull(LOWER(x.hasGoods),'') LIKE LOWER(CONCAT('%',ifnull(:hasGoods,''),'%')) AND  "
			+ "ifnull(LOWER(x.hasServices),'') LIKE LOWER(CONCAT('%',ifnull(:hasServices,''),'%')) AND  "
			+ "ifnull(LOWER(x.buName),'') LIKE LOWER(CONCAT('%',ifnull(:buName,''),'%')) AND  "
			+ "ifnull(LOWER(x.divisionName),'') LIKE LOWER(CONCAT('%',ifnull(:divisionName,''),'%')) AND  "
			+ "ifnull(LOWER(x.functionName),'') LIKE LOWER(CONCAT('%',ifnull(:functionName,''),'%')) AND  "
			+ "ifnull(LOWER(x.clientCode),'') LIKE LOWER(CONCAT('%',ifnull(:clientCode,''),'%')) AND  "
			+ "ifnull(LOWER(x.clientName),'') LIKE LOWER(CONCAT('%',ifnull(:clientName,''),'%')) AND  "
			+ "ifnull(LOWER(x.countryName),'') LIKE LOWER(CONCAT('%',ifnull(:countryName,''),'%')) AND  "
			+ "ifnull(LOWER(x.jobCodes),'') LIKE LOWER(CONCAT('%',ifnull(:jobCodes,''),'%')) AND  "
			+ "ifnull(LOWER(x.monthNo),'') LIKE LOWER(CONCAT('%',ifnull(:monthNo,''),'%')) AND  "
			+ "ifnull(LOWER(x.year),'') LIKE LOWER(CONCAT('%',ifnull(:year,''),'%'))")
	List<com.ss.smartoffice.shared.model.OrderInTake> summary(String soCode, String soOrderAmount, String isVirtualPo,
			String virtualPoNum, String hasGoods, String hasServices, String buName, String divisionName,
			String functionName, String clientCode, String clientName, String countryName, String jobCodes,
			String monthNo, String year);
}
