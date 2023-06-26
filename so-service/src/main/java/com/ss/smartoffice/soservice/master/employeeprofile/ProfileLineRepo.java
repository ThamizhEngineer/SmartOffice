package com.ss.smartoffice.soservice.master.employeeprofile;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.PagingAndSortingRepository;
@Scope("prototype")
public interface ProfileLineRepo extends PagingAndSortingRepository<ProfileLine, Integer>{

}
