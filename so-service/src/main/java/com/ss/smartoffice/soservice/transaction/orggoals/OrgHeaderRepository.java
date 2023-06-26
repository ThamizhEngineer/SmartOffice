package com.ss.smartoffice.soservice.transaction.orggoals;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.ExportOrg;
import com.ss.smartoffice.shared.model.OrgHeader;

public interface OrgHeaderRepository extends CrudRepository<OrgHeader, Integer>{
OrgHeader findByFyYearId(String fyYearId);


@Query("SELECT new com.ss.smartoffice.shared.model.ExportOrg(header.fyYearCode,header.empName,line.metricName,line.operator,line.metricValue,line.metricCategoryName,line.metricLevelCode,line.descp,line.metricSummary) from com.ss.smartoffice.shared.model.OrgHeader header left join com.ss.smartoffice.shared.model.OrgLine line on header.id=line.orgObjHeaderId where header.fyYearId=:fyYearId")
//List<com.ss.smartoffice.shared.model.ExportTestResultView> findByincidentsId(Integer incidentId);
List<ExportOrg> findByFyYearIdList(String fyYearId);
}
