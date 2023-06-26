package com.ss.smartoffice.sochatservice.repo;

import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.sochatservice.model.ChatGroup;

public interface ChatGroupRepo extends CrudRepository<ChatGroup, Integer>{

	ChatGroup findByGroupId(Integer groupId);
	
}
