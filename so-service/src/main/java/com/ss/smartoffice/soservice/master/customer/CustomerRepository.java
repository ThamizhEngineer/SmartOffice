package com.ss.smartoffice.soservice.master.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.master.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{

	Page<Customer> findAll(Pageable pageable);

	Page<Customer> findByCustomerCode(Pageable pageable, String customerCode);

	Page<Customer> findByContactNameAndCustomerCode(Pageable pageable, String customerCode, String contactName);

	Page<Customer> findByContactName(Pageable pageable, String contactName);

}
