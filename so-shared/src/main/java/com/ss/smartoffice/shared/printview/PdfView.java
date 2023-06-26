package com.ss.smartoffice.shared.printview;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.ss.smartoffice.shared.print.PrintBusHelper;


public class PdfView extends AbstractPdfView{

	@Override
	protected void printPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
			PrintBusHelper.printPdfDocument(model, document, writer, request, response);

	}

}
