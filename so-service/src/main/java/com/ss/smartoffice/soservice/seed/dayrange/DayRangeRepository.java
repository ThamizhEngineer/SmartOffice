package com.ss.smartoffice.soservice.seed.dayrange;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public interface DayRangeRepository extends CrudRepository<DayRange,Integer> {

}
