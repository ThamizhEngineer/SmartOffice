package com.ss.smartoffice.shared.seed.querysetup;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuerySetupRepository extends CrudRepository<QuerySetup, Integer> {

	@Query("select x from com.ss.smartoffice.shared.seed.querysetup.QuerySetup x where x.purpose=:purpose and x.entity=:entity")
	List<QuerySetup> fetchByPurposeAndEntity(@Param("purpose") String purpose, @Param("entity") String entity);
	
	@Query("select x from com.ss.smartoffice.shared.seed.querysetup.QuerySetup x")
	List<QuerySetup> fetchAll();
	
}
