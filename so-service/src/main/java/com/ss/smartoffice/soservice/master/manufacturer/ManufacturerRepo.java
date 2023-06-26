package com.ss.smartoffice.soservice.master.manufacturer;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
@Scope("prototype")
public interface ManufacturerRepo extends PagingAndSortingRepository<Manufacturer, Integer>{

	Page<Manufacturer> findAll(Pageable pageable);

//	static Page<Manufacturer> findByManufacturerCode(Pageable pageable, String manufacturerCode) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	Page<Manufacturer> findByManufacturerCode(Pageable pageable, String manufacturerCode);




}
