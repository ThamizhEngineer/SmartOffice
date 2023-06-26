package com.ss.smartoffice.soservice.transaction.uploadpayslip;


import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ss.smartoffice.soservice.transaction.model.UploadPayslipLine;
@Scope("prototype")
public interface UploadPayslipLineRepository  extends JpaRepository<UploadPayslipLine,Integer>{
	List<UploadPayslipLine>findByUploadPayslipHdrId(Integer uploadPayslipHdrId);
	
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.model.UploadPayslipLine uploadPayslipLine where uploadPayslipLine.id= ?1")
	void delete(int id);
}

