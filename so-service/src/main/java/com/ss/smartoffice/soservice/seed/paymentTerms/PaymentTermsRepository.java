package com.ss.smartoffice.soservice.seed.paymentTerms;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

@Scope("prototype")
public interface PaymentTermsRepository extends CrudRepository<PaymentTerms, Integer>{

}
