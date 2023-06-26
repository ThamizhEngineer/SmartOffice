package com.ss.smartoffice.sochatservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.sochatservice.model.ChatUser;

public interface ChatUserRepo extends CrudRepository<ChatUser, Integer> {

	@Query( "select c from com.ss.smartoffice.sochatservice.model.ChatUser c where c.employeeCode IS NOT NULL" )
	List<ChatUser> fetchOnlyEmp();
}
