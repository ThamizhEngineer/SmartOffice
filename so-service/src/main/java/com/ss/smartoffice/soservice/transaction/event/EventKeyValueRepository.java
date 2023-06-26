package com.ss.smartoffice.soservice.transaction.event;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface EventKeyValueRepository extends CrudRepository<EventKeyValue, Integer> {

}
