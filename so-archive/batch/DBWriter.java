package com.ss.smartoffice.soservice.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.smartoffice.soservice.transaction.model.UploadPayslipLine;
import com.ss.smartoffice.soservice.transaction.uploadpayslip.UploadPayslipLineRepository;
@Component
public class DBWriter implements ItemWriter<UploadPayslipLine> {

    @Autowired
    private UploadPayslipLineRepository uploadPayslipLineRepository;

	@Override
	public void write(List<? extends UploadPayslipLine> uploadPayslipLine) throws Exception {
		// TODO Auto-generated method stub
		 uploadPayslipLineRepository.saveAll(uploadPayslipLine);
	}

  
}
