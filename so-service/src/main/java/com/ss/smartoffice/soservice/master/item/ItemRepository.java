package com.ss.smartoffice.soservice.master.item;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Scope("prototype")
public interface ItemRepository extends CrudRepository<Item, Integer> {
	
	@Query("select x from com.ss.smartoffice.soservice.master.item.Item x where x.hsnSacCode=:hsnSacCode")
	List<Item> findByHsnSacCode(@Param("hsnSacCode")String hsnSacCode);


@Query("select i from com.ss.smartoffice.soservice.master.item.Item i "
			+ "where ifnull(LOWER(i.itemCode),'') LIKE LOWER(CONCAT('%',ifnull(:itemCode,''), '%'))  AND ifnull(LOWER(i.referenceNumber),'') LIKE LOWER(CONCAT('%',ifnull(:referenceNumber,''), '%')) AND ifnull(LOWER(i.forPurchase),'') LIKE LOWER(CONCAT('%',ifnull(:forPurchase,''), '%'))  "
			+ "AND ifnull(LOWER(i.hsnSacCode),'') LIKE LOWER(CONCAT('%',ifnull(:hsnSacCode,''), '%')) AND ifnull(LOWER(i.itemName),'') LIKE LOWER(CONCAT('%',ifnull(:itemName,''), '%'))")
List<Item> fetchByAdvanceFilter(
			@Param("itemCode")String itemCode,@Param("referenceNumber")String referenceNumber,
			@Param("forPurchase")String forPurchase,@Param("hsnSacCode")String hsnSacCode,@Param("itemName")String itemName);
	
	
}
