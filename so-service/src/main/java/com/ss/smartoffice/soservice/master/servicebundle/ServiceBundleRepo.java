package com.ss.smartoffice.soservice.master.servicebundle;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.ss.smartoffice.soservice.master.servicebundle.ServiceBundle;
@Scope("prototype")
public interface ServiceBundleRepo extends PagingAndSortingRepository<ServiceBundle, Integer> {

	Page<ServiceBundle> findAll(Pageable pageable);

	Page<ServiceBundle> findBySacCode(Pageable pageable, String sacCode);

	Page<ServiceBundle> findBySbName(Pageable pageable, String sbName);

	Page<ServiceBundle> findBySbNameAndSacCode(Pageable pageable, String sacCode, String sbName);

}
