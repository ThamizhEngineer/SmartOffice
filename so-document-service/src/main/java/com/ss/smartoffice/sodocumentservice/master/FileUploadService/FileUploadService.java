package com.ss.smartoffice.sodocumentservice.master.FileUploadService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;





@RestController
@RequestMapping("master/file")
public class FileUploadService {
	
	 

	 //Save the uploaded file to this folder
	    private static String UPLOADED_FOLDER = "/home/priya/upload/";
	    
	    //  Single file upload
	 
	    @PostMapping("/upload")
	    
	    @ResponseBody
	    
	    public ResponseEntity<?> uploadFile(
	            @RequestParam("file") MultipartFile uploadfile) {

	      

	        if (uploadfile.isEmpty()) {
	            return new ResponseEntity<Object>("please select a file!", HttpStatus.OK);
	        }
	        try {

	            saveUploadedFiles(Arrays.asList(uploadfile));

	        }catch(IOException e) {
	        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<Object>("Successfully uploaded - " +
	                uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
	    }
	    
	//  Multiple file upload
	    @PostMapping("/upload/multi")
	    public ResponseEntity<?> uploadFileMulti(
	            @RequestParam("extraField") String extraField,
	            @RequestParam("files") MultipartFile[] uploadfiles) {


	        
	     // Get file name maximum file size can be 10Mb when exceeds throw error
	        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
	                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

	        if (StringUtils.isEmpty(uploadedFileName)) {
	            return new ResponseEntity<Object>("please select a file!", HttpStatus.OK);
	        }
	        try {

	            saveUploadedFiles(Arrays.asList(uploadfiles));

	        } catch (IOException e) {
	        	  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
	        return new ResponseEntity<Object>("Successfully uploaded - "
	                + uploadedFileName, HttpStatus.OK);

	    }
	 //  maps html form to a Model
	    @PostMapping("/upload/multi/model")
	    
	    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadModel model) {

	     
	        try {

	            saveUploadedFiles(Arrays.asList(model.getFiles()));

	        } catch (IOException e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        return new ResponseEntity<Object>("Successfully uploaded!", HttpStatus.OK);
	    }
	  //save file
        private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

            for (MultipartFile file : files) {

                if (file.isEmpty()) {
                    continue; //next pls
                }

                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);

            }

	

}
}
