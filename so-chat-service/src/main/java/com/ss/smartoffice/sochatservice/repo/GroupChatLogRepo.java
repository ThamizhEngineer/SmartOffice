package com.ss.smartoffice.sochatservice.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.sochatservice.model.GroupChatLog;

public interface GroupChatLogRepo extends CrudRepository<GroupChatLog, Integer> {
	
	List<GroupChatLog> findByGroupChatId(Integer groupChatId);
	

}
