package com.ss.smartoffice.soservice.master.manufacturer;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface ProductFamilyRepo extends CrudRepository<ProductFamily, Integer> {

}
