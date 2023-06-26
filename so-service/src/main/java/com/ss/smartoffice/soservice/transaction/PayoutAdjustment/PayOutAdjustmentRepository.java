package com.ss.smartoffice.soservice.transaction.PayoutAdjustment;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.OfficeCalender;

@Scope("prototype")
public interface PayOutAdjustmentRepository extends CrudRepository<PayOutAdjustment, Integer>{

	
	List<PayOutAdjustment> findByPayMonthAndPayYear(String payMonth, String payYear);
	
//	List<OfficeCalender> findByYearAndMonthAndOfficeId(String year,String month,String officeId);

}
