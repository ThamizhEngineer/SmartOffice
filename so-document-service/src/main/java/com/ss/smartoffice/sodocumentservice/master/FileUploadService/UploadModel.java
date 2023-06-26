package com.ss.smartoffice.sodocumentservice.master.FileUploadService;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class UploadModel {
	private String extraField;

    private MultipartFile[] files;

	public UploadModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UploadModel(String extraField, MultipartFile[] files) {
		super();
		this.extraField = extraField;
		this.files = files;
	}

	public String getExtraField() {
		return extraField;
	}

	public void setExtraField(String extraField) {
		this.extraField = extraField;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "UploadModel [extraField=" + extraField + ", files=" + Arrays.toString(files) + "]";
	}
    
    
}
