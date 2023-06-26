package com.ss.smartoffice.sochatservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.sochatservice.model.IndividualChatLog;

public interface IndividualChatLogRepo extends CrudRepository<IndividualChatLog, Integer> {
	
	List<IndividualChatLog> findByToAuthUserId(Integer toAuthUserId);
	
	List<IndividualChatLog> findByFromAuthUserIdAndToAuthUserId(Integer fromAuthUserId,Integer toAuthUserId);
	
	@Query( "select i from com.ss.smartoffice.sochatservice.model.IndividualChatLog i where (i.fromAuthUserId=:fromAuthUserId and i.toAuthUserId=:toAuthUserId) or (i.fromAuthUserId=:toAuthUserId and i.toAuthUserId=:fromAuthUserId)" )
	List<IndividualChatLog> fetchCurrentChatLog(@Param("fromAuthUserId")Integer fromAuthUserId,@Param("toAuthUserId")Integer toAuthUserId);

}
