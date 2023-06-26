package com.ss.smartoffice.sodocumentservice.documentmanager;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

@Service
public class FileOperationHelper {
	
//File operations
	
	// copy files from (input)source to (input)destination
	public void copyFile(String source, String destination) {
		Path sourcePath      = Paths.get(source);
		Path destinationPath = Paths.get(destination);
		try {
		    Files.copy(sourcePath, destinationPath);
		} catch(FileAlreadyExistsException e) {
		    //destination file already exists
		} catch (IOException e) {
		    //something else went wrong
		    e.printStackTrace();
		}
	}

	
	public void createDirectories(String path) throws IOException { // For given (input)path this function checks and creates directories
		boolean isFileExists = false;
		Path validPath = Paths.get(path);
		isFileExists = Files.exists(validPath);
		if (isFileExists) {
			System.out.println("directory exists");
		}else {
			Files.createDirectories(validPath);
		}
	}
	
	

}
