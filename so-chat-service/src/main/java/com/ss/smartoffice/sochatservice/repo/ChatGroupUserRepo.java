package com.ss.smartoffice.sochatservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.sochatservice.model.ChatGroupUser;

public interface ChatGroupUserRepo extends CrudRepository<ChatGroupUser, Integer> {
	
	List<ChatGroupUser> findByAuthUserId(Integer authUserId);
	
	@Query("select count(*) from ChatGroupUser grpUsr where grpUsr.groupId=:groupId and grpUsr.authUserId=:authUserId")
	Long checkChatGroupUsers(String groupId,Integer authUserId);
	
}	
