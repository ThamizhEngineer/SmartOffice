package com.ss.smartoffice.soservice.transaction.job;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.JobTaskType;
@Scope("prototype")
public interface JobTaskTypeRepository extends CrudRepository<JobTaskType, Integer>{
List<JobTaskType>findByJobId(String jobId);
}
