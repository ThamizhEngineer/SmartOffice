package com.ss.smartoffice.soservice.transaction.job;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.JobTaskList;
@Scope("prototype")

public interface JobTaskListRepo  extends CrudRepository<JobTaskList, Integer>{

}
