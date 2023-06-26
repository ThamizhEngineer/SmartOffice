package com.ss.smartoffice.soservice.transaction.job;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.JobMaterial;
@Scope("prototype")
public interface JobMaterialRepository extends CrudRepository<JobMaterial, Integer> {

}
