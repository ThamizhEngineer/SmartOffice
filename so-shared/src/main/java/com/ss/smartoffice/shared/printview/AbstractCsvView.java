package com.ss.smartoffice.shared.printview;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public abstract class AbstractCsvView extends AbstractView {
	  private static final String CONTENT_TYPE = "text/csv";

	    public AbstractCsvView() {
	        setContentType(CONTENT_TYPE);
	    }

	    @Override
	    protected boolean generatesDownloadContent() {
	        return true;
	    }

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
	     response.setContentType(getContentType());
	     printCsvDocument(model, request, response);
	}
	 protected abstract void printCsvDocument(
	            Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
	            throws Exception;


	

}
