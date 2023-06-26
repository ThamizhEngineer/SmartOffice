package com.ss.smartoffice.soservice.transaction.task;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface TaskHistoryRepo extends CrudRepository<TaskHistory, Integer> {

}
