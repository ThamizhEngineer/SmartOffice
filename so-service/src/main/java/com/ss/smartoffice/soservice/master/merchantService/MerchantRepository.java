package com.ss.smartoffice.soservice.master.merchantService;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface MerchantRepository extends CrudRepository<Merchant, Integer> {

}
