package com.ss.smartoffice.shared.interceptor;

import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.AuthUserType;

public interface AuthUserTypeRepo extends CrudRepository<AuthUserType, Integer> {

}
