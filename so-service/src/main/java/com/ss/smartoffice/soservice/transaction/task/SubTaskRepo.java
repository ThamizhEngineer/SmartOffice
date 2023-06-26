package com.ss.smartoffice.soservice.transaction.task;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface SubTaskRepo extends CrudRepository<SubTask, Integer> {
	@Transactional
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.task.SubTask subTask where subTask.id= ?1")
	void delete(int id);
}
