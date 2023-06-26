package com.ss.smartoffice.sodocumentservice.ImageService;

import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.ImageService.ImageServiceShared;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.dm.DocInfoRepository;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.employee.memployee;


@RestController
@RequestMapping(path = "/img")
public class ImageServices {
	
	@Autowired
	DocumentManagementHelper documentManagementHelper;
	@Autowired
	ImageServiceShared imageService;
	@Autowired
	DocInfoRepository docInfoRepository;
	@Autowired
	CommonUtils commonUtils;
	@Value("${emp.profile.url}")
	private String empUrl;
	
	
	@GetMapping("/{docId}/test")
	public ResponseEntity<InputStreamResource> getDocByDocIdTest(@PathVariable("docId") String docId,
			@RequestParam("authorization") String token)
			 throws HeadlessException {
						System.out.println("token"+token);	
						try {
							DocInfo docInfo = documentManagementHelper.getDocInfoByDocId(docId).get(0);
							File file = documentManagementHelper.getDocByDocId(docId);

							HttpHeaders respHeaders = new HttpHeaders();
							respHeaders.setContentType(MediaType.parseMediaType("image/jpeg"));
							respHeaders.add("Content-Disposition", String.format("attachment; filename=\"" + docInfo.getDocNameFromUser() +"\""));

							InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
							return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new SmartOfficeException(e.getMessage());
						}

	}
	
	@GetMapping("/{docId}/image")
	public ResponseEntity<InputStreamResource> getResizedImage(@PathVariable("docId") String docId,
			@RequestParam("authorization") String token)
			 throws HeadlessException {
						System.out.println("token"+token);						
						try {
							
							memployee emp = new memployee();
							emp = getEmpByDocId(docId);
							System.out.println("emp-->"+emp);
							
							DocInfo docInfo = documentManagementHelper.getDocInfoByDocId(docId).get(0);
							
							String inputImagePath = docInfo.getDocLocation()+"/"+docInfo.getDocName();
							
							String outputImagePath = docInfo.getDocLocation()+"/"+docInfo.getDocName()+"-Thmyleaf";
														
							String dimension = imageService.findDimensions(inputImagePath);
							int height =  Integer.parseInt(dimension.split(",")[0]);
							int width =   Integer.parseInt(dimension.split(",")[1]);
							
							imageService.resizeImageByPercentage(inputImagePath,outputImagePath,height,width);
							
							File file =new File(outputImagePath);
							
							updateDoc(docInfo.getDocName(), docInfo, file, emp);
																											
							HttpHeaders respHeaders = new HttpHeaders();
							respHeaders.setContentType(MediaType.parseMediaType("image/jpeg"));
							respHeaders.add("Content-Disposition", String.format("attachment; filename=\"" + docInfo.getDocNameFromUser() +"\""));

							InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
							
							return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
						} catch (Exception e) {
							
							e.printStackTrace();
							throw new SmartOfficeException(e.getMessage());
						}

	}
	
	@GetMapping("/_internal/{docId}/image")
	public ResponseEntity<InputStreamResource> getResizedImageInternally(@PathVariable("docId") String docId,
			@RequestParam(value = "authorization",required = false) String token)
			 throws HeadlessException {
						System.out.println("token"+token);						
						try {
							
							memployee emp = new memployee();
							emp = getEmpByDocId(docId);
							System.out.println("emp-->"+emp);
							
							DocInfo docInfo = documentManagementHelper.getDocInfoByDocId(docId).get(0);
							
							String inputImagePath = docInfo.getDocLocation()+"/"+docInfo.getDocName();
							
							String outputImagePath = docInfo.getDocLocation()+"/"+docInfo.getDocName()+"-Thmyleaf";
							
							String dimension = imageService.findDimensions(inputImagePath);
							int height =  Integer.parseInt(dimension.split(",")[0]);
							int width =   Integer.parseInt(dimension.split(",")[1]);
							
							imageService.resizeImageByPercentage(inputImagePath,outputImagePath,height,width);
							
							File file =new File(outputImagePath);
							
							updateDoc(docInfo.getDocName(), docInfo, file, emp);
																											
							HttpHeaders respHeaders = new HttpHeaders();
							respHeaders.setContentType(MediaType.parseMediaType("image/jpeg"));
							respHeaders.add("Content-Disposition", String.format("attachment; filename=\"" + docInfo.getDocNameFromUser() +"\""));

							InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
							
							return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
						} catch (Exception e) {
							
							e.printStackTrace();
							throw new SmartOfficeException(e.getMessage());
						}

	}
	
	public memployee getEmpByDocId(String docId) {
		memployee emp = new memployee();
		String getEmpUrl= empUrl+"/_internal/"+docId+"/docId";
		emp = commonUtils.getRestTemplate().getForObject(getEmpUrl, memployee.class);
		return emp;
	}
	
	public String updateDoc(String docName,DocInfo docInfo,File file,memployee emp) {
		List<DocInfo> docList = documentManagementHelper.getDocInfoByDocName(docName);
		System.out.println("list-->"+docList);
		if(docList.size()>1) {
			for(DocInfo docObj:docList) {
				if(docObj.getDocExtension().equals("Thymleaf")) {
					docInfoRepository.delete(docObj);
					DocInfo doc = new DocInfo();
					doc.setCreatedDt(LocalDateTime.now());
					doc.setDocExtension("Thymleaf");
					doc.setDocId(commonUtils.generateId());
					doc.setDocLocation(docInfo.getDocLocation());
					doc.setDocName(docInfo.getDocName());
					doc.setDocNameFromUser(docInfo.getDocNameFromUser());
					doc.setDocTypeId(docInfo.getDocTypeId());
					doc.setDocSize(file.length()+"");									
					docInfoRepository.save(doc);
					emp.setDocId(doc.getDocId());
					updateDocIdinEmp(emp);
				}
			}
		}else {
			DocInfo doc = new DocInfo();
			doc.setCreatedDt(LocalDateTime.now());
			doc.setDocExtension("Thymleaf");
			doc.setDocId(commonUtils.generateId());
			doc.setDocLocation(docInfo.getDocLocation());
			doc.setDocName(docInfo.getDocName());
			doc.setDocNameFromUser(docInfo.getDocNameFromUser());
			doc.setDocTypeId(docInfo.getDocTypeId());
			doc.setDocSize(file.length()+"");									
			docInfoRepository.save(doc);
			emp.setDocId(doc.getDocId());
			updateDocIdinEmp(emp);
		}
		return docName;
		
	}
	
	public memployee updateDocIdinEmp(memployee emp) {
		String patchEmpUrl = empUrl+"_internal/"+emp.getId()+"/header";
		System.out.println("patchEmpUrl-->"+patchEmpUrl);
		memployee updatedemp = new memployee();
		updatedemp = commonUtils.getRestTemplate().postForObject(patchEmpUrl, emp, memployee.class);
		return updatedemp;
	}
	
	
//	------------------Test purpose
	public static BufferedImage convertToBufferedImage(Image image,String docId)
	{
	    BufferedImage newImage = new BufferedImage(
	        image.getWidth(null), image.getHeight(null),
	        BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = newImage.createGraphics();
	    g.drawImage(image, 50, 50, null);
	    g.dispose();
	    return newImage;
	}

}
