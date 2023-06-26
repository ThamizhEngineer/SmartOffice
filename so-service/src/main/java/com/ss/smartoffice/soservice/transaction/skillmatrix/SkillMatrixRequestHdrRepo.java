package com.ss.smartoffice.soservice.transaction.skillmatrix;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillMatrixRequestHdrRepo extends CrudRepository<SkillMatrixRequestHdr, Integer> {
	
	List<SkillMatrixRequestHdr> findByKey(String key);

}
