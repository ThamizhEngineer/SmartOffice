package com.ss.smartoffice.soservice.transaction.ProfileFinderService;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface ProfileFinderJobRepository extends CrudRepository<ProfileFinderJob, Integer>{

}
