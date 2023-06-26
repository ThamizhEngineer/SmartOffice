package com.ss.smartoffice.shared.printviewresolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.ss.smartoffice.shared.printview.PdfView;



public class PdfViewResolver implements ViewResolver{

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		 return new PdfView();
	}

}
