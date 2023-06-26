package com.ss.smartoffice.soservice.transaction.vacancyrequest;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface VacancyRequestRepo extends CrudRepository<VacancyRequest, Integer>{
@Query("select job from com.ss.smartoffice.soservice.transaction.vacancyrequest.VacancyRequest job where job.hr1UsrGrpId =:hr1UserGroupId or job.hr2UsrGrpId= :hr2UserGroupId")
List<VacancyRequest> findByHr1UsrGrpIdOrHr2UsrGrpId(String hr1UserGroupId,String hr2UserGroupId);

@Query("select job from com.ss.smartoffice.soservice.transaction.vacancyrequest.VacancyRequest job where job.status='PENDING-APPROVAL' ")
List<VacancyRequest> findByPendingStatus();

@Query("select job from com.ss.smartoffice.soservice.transaction.vacancyrequest.VacancyRequest job where job.status='OPEN' ")
List<VacancyRequest> findByOpenStatus();
}
