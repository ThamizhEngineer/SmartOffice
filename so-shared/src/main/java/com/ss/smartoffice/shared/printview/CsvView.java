package com.ss.smartoffice.shared.printview;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ss.smartoffice.shared.print.PrintBusHelper;


public class CsvView extends AbstractCsvView{

	@Override
	protected void printCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintBusHelper.printCsvDocument(model, request, response);
	
		
	}

}
