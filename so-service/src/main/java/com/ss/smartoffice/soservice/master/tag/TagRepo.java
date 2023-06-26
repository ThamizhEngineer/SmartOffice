package com.ss.smartoffice.soservice.master.tag;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TagRepo extends CrudRepository<MTag, Integer> {

	@Query("select mt from com.ss.smartoffice.soservice.master.tag.MTag mt where LOWER(mt.name) =LOWER(:name)")
	MTag findByName(String name);
	
}
