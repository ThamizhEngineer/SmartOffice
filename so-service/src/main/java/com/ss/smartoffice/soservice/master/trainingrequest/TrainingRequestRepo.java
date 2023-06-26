package com.ss.smartoffice.soservice.master.trainingrequest;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.smartoffice.soservice.transaction.incident.Incident;

@Repository
@Scope("prototype")
public interface TrainingRequestRepo extends CrudRepository<Incident, Integer>{

}




