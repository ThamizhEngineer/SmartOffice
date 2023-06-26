package com.ss.smartoffice.soservice.master.costService;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

@Scope("prototype")
public interface CostCenterRepository extends CrudRepository<CostCenter, Integer> {

	Page<CostCenter> findAll(Pageable pageable);
	
	Page<CostCenter> findByDeptId(Pageable pageable,Integer deptId);
}
