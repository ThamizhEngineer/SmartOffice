package com.ss.smartoffice.soservice.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.ss.smartoffice.soservice.transaction.model.UploadPayslipLine;
@Component

public class Processor implements ItemProcessor<UploadPayslipLine, UploadPayslipLine>{


	@Override
	public UploadPayslipLine process(UploadPayslipLine uploadPayslipLine) throws Exception {
		
//		System.out.println("uploadHeaderId"+uploadHeaderId);
		uploadPayslipLine.setUploadPayslipHdrId(0);
		uploadPayslipLine.setIsClean("Y");
		uploadPayslipLine.setRemarks("Uploading");
		return uploadPayslipLine;
	}

}
