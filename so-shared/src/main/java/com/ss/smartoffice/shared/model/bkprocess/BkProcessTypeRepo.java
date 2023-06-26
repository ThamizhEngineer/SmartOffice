package com.ss.smartoffice.shared.model.bkprocess;

import org.springframework.data.repository.CrudRepository;

public interface BkProcessTypeRepo extends CrudRepository<BkProcessType, Integer>{

	BkProcessType findByBkProcessName(String processName);
	
}
