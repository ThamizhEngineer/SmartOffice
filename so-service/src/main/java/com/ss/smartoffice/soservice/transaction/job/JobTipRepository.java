package com.ss.smartoffice.soservice.transaction.job;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.JobTip;
@Scope("prototype")
public interface JobTipRepository extends CrudRepository<JobTip,Integer> {

}
