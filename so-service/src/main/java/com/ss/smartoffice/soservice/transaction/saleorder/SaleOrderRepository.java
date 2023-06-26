package com.ss.smartoffice.soservice.transaction.saleorder;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.soservice.transaction.model.SaleOrder;
@Scope("prototype")
public interface SaleOrderRepository extends CrudRepository<SaleOrder, Integer>{


	 @Query("SELECT new com.ss.smartoffice.soservice.transaction.model.SaleOrder (so.id, so.saleOrderCode,"
	 		+ "so.projectName,so.status,so.partnerName,so.purchaseOrderId,so.divisionId,so.partnerId) FROM SaleOrder so where ifnull(LOWER(so.partnerId),'') LIKE LOWER(CONCAT('%',ifnull(:partnerId,''),'%')) And ifnull(LOWER(so.projectId),'') LIKE LOWER(CONCAT('%',ifnull(:projectId,''),'%')) ")
	 List<SaleOrder>findBySummaries(@Param("partnerId")String partnerId,@Param("projectId")String projectId);
	 
	 @Query("SELECT new com.ss.smartoffice.soservice.transaction.model.SaleOrder(so.id, so.saleOrderCode,so.projectName, so.status,\n" + 
	    		"		so.partnerName) FROM SaleOrder so where so.id= ?1 ")
	 void findByIds(); 
	 
	 SaleOrder findBySaleOrderCode(String saleOrderCode);
}
