package com.ss.smartoffice.soservice.seed.PayoutType;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface PayoutTypeRepository extends CrudRepository<PayoutType, Integer> {

}
