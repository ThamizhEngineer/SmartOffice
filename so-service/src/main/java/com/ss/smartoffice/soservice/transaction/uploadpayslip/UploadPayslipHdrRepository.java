package com.ss.smartoffice.soservice.transaction.uploadpayslip;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.UploadPayslipHdr;

@Scope("prototype")
public interface UploadPayslipHdrRepository extends CrudRepository<UploadPayslipHdr,Integer>{
	List<UploadPayslipHdr>findByPayMonthAndPayYearAndIsOverwriteFlag(Integer payMonth,String payYear,String isOverWriteFlag);
}
