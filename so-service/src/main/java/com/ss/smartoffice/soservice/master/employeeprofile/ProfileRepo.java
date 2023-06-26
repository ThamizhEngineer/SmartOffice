package com.ss.smartoffice.soservice.master.employeeprofile;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
@Scope("prototype")

public interface ProfileRepo extends PagingAndSortingRepository<Profile, Integer> {

	Page<Profile> findByProfileCode(Pageable pageable, String profileCode);

	Page<Profile> findByIdAndProfileCode(Pageable pageable, String profileCode, String id);

	Page<Profile> findById(Pageable pageable, String id);

}
