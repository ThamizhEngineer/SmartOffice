package com.ss.smartoffice.soservice.seed.bankinginfo;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public interface BankInfoRepo extends CrudRepository<BankInfo,Integer> {

}
