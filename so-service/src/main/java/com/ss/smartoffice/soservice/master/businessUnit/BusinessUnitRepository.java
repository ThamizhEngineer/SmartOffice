package com.ss.smartoffice.soservice.master.businessUnit;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface BusinessUnitRepository extends CrudRepository<BusinessUnit, Integer>{


}
