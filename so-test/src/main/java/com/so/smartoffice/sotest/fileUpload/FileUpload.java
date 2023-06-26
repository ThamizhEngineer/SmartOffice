package com.so.smartoffice.sotest.fileUpload;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/file-upload")
@Service
public class FileUpload {
	
	@Value("${doc.url}")
	private String docUrl;
	
	@Value("${upload-file.loc}")
	private String uploadFileLoc;
	
	@PostMapping
	public String fileUploadn(@RequestHeader(value = "Authorization",required = true)String authorization) throws IOException {
		 LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
	        FileSystemResource value = new FileSystemResource(new File(uploadFileLoc));
	        map.add("uploadingFiles", value);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	        headers.set("Authorization", authorization);
	        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.exchange(docUrl+"so-document-service/dm/upload/CLIENT-BILLING", HttpMethod.POST, requestEntity, String.class);	        
		return "success";
	}

	
}
