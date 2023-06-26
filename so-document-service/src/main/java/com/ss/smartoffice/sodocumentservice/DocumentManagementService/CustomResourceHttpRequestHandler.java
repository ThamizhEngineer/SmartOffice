package com.ss.smartoffice.sodocumentservice.DocumentManagementService;

import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class CustomResourceHttpRequestHandler extends ResourceHttpRequestHandler{
	
	final static String ATTR_FILE = CustomResourceHttpRequestHandler.class.getName() + ".file";

	@Override
	protected Resource getResource(HttpServletRequest request) throws IOException {

		final File file = (File) request.getAttribute(ATTR_FILE);
		return new FileSystemResource(file);
	}

}
