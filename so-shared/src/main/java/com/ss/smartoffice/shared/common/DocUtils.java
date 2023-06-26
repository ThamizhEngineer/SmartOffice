package com.ss.smartoffice.shared.common;

import java.io.File;
import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ss.smartoffice.shared.model.SmartOfficeException;


@Component
public class DocUtils {

	public File getOrCreateFolder(String folderPath) {
		try {
			File folder = new File(folderPath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			return folder;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}
	}
	
	public String getFileExtension(MultipartFile file) {
		try {
			String fileExtension = "";
			// Get file Name 
			String fileName = file.getOriginalFilename();

			// If fileName do not contain "." or starts with "." then it is not a valid file
			if (fileName.contains(".") && fileName.lastIndexOf(".") != 0) {
				fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
			}

			return fileExtension;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}
	}
	
	public String getCurrentMonthYear() {
		int month = LocalDate.now().getMonthValue();
		String monthStr=(month>9?month+"":"0"+month);
		return monthStr+"-"+LocalDate.now().getYear();
	}
	

	
}
