package com.ss.smartoffice.sodocumentservice.printer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.dm.DocTypeHelper;
import com.ss.smartoffice.shared.interceptor.AuthUserRepo;
import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.dm.DocumentType;
import com.ss.smartoffice.sodocumentservice.DocumentManagementService.DocMgmtService;
import com.ss.smartoffice.sodocumentservice.model.EngAssessmentPrintObject;
import com.ss.smartoffice.sodocumentservice.model.GapAnalysisPrintObject;
import com.ss.smartoffice.sodocumentservice.model.SkillMatrixRequestSkills;
import com.ss.smartoffice.sodocumentservice.model.SkillMatrixResult;

@Service
public class SkillMatrixDocGenerator {

	private static Logger log = LoggerFactory.getLogger(SkillMatrixDocGenerator.class);

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	@Value("docs.base.location")
	private String baseLocation;

	@Value("${skill-matrix-base.url}")
	private String skillMatrixbaseUrl;

	@Value("${skill-matrix-result.url}")
	private String sillMatrixResultUrl;

	@Autowired
	DocTypeHelper docTypeHelper;
	@Autowired
	DocMgmtService docMgmtService;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	AuthUserRepository authUserRepo;

	String fileLocation = null;
	String fileNameSuffix = null;
	String fileNamePrefix = null;

	public void generateDoc(List<SkillMatrixResult> list, String type)
			throws SmartOfficeException, DocumentException, IOException {
		String key = list.get(0).getSkillMatrixHdrKey();
		log.info("Generating Pdf:" + key);

		if (type.equals("ENG-ASSESSMENT")) {
			List<EngAssessmentPrintObject> engAssesmentList = formEngAssesment(list);
			String htmlContent = prepareContext(null, type, engAssesmentList);
			formMetadta(htmlContent, key, type);
		} else {
			List<GapAnalysisPrintObject> gapAnalysisList = formGapAnalysis(list);
			String htmlContent = prepareContext(gapAnalysisList, type, null);
			formMetadta(htmlContent, key, type);
		}

	}

	private List<GapAnalysisPrintObject> formGapAnalysis(List<SkillMatrixResult> inputList) {
		log.info("Forming gap analysis");
		List<GapAnalysisPrintObject> outputList = new ArrayList<GapAnalysisPrintObject>();
		List<SkillMatrixRequestSkills> req = fetchReqHdrSkill(inputList.get(0).getSkillMatrixHdrKey());
		
		for (SkillMatrixRequestSkills sk : req) {
			GapAnalysisPrintObject ga = new GapAnalysisPrintObject();
			ga.setSkill(sk.getProductName() + "-" + sk.getServiceName());
			ga.setMarketNeed(sk.getExpectedCount().toString());
			
			List<SkillMatrixResult> x = inputList.stream().filter(i -> i.getProductId().equals(sk.getProductId()) && 
					i.getServiceId().equals(sk.getServiceId()))
					.collect(Collectors.toList());
			
			int gap = 0;
			gap = sk.getExpectedCount()-x.size();
			
			ga.setInHouseCapacity(String.valueOf(x.size()));
			
			if (gap==0) {
				ga.setGap("none");
			}else if(gap>0) {
				ga.setGap("surplus("+gap+")");
			}else if(gap<0) {
				ga.setGap(String.valueOf(gap));
			}else {
				ga.setGap("NA");
			}
			outputList.add(ga);
		}
		return outputList;
	}

	public List<SkillMatrixRequestSkills> fetchReqHdrSkill(String key) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", commonUtils.getLoggedinAppToken());
		HttpEntity<String> request = new HttpEntity<String>(key, headers);
		String url = skillMatrixbaseUrl + "result-hdr";
		log.info("Url: " + url);
		ResponseEntity<List<SkillMatrixRequestSkills>> responseObj = commonUtils.getRestTemplate().exchange(url,
				HttpMethod.POST, request, new ParameterizedTypeReference<List<SkillMatrixRequestSkills>>() {
				});
		return responseObj.getBody();
	}

	private List<EngAssessmentPrintObject> formEngAssesment(List<SkillMatrixResult> inputList) {
		List<EngAssessmentPrintObject> outputList = new ArrayList<EngAssessmentPrintObject>();
		List<String> empIds = inputList.stream().map(SkillMatrixResult::getEmployeeId).collect(Collectors.toList());
		empIds = empIds.stream().distinct().collect(Collectors.toList());
		for (String emp : empIds) {
			List<SkillMatrixResult> x = inputList.stream().filter(i -> i.getEmployeeId().equals(emp))
					.collect(Collectors.toList());
			
			EngAssessmentPrintObject ea = new EngAssessmentPrintObject();
			ea.setEngineer(x.get(0).getEmployeeName());
			List<SkillMatrixResult> hasList = new ArrayList<SkillMatrixResult>();
			List<SkillMatrixResult> lacksList = new ArrayList<SkillMatrixResult>();
			List<SkillMatrixResult> trainingList = new ArrayList<SkillMatrixResult>();

			for (SkillMatrixResult res : x) {
				if (Integer.valueOf(res.getSkillLevelCode()) > 1) {
					hasList.add(res);
				} else if (Integer.valueOf(res.getSkillLevelCode()) <= 1) {
					lacksList.add(res);
					trainingList.add(res);
				}
			}
			ea.setHas(hasList);
			ea.setLacks(lacksList);
			ea.setTraining(trainingList);
			outputList.add(ea);
		}
		return outputList;
	}

	private List<DocInfo> formMetadta(String htmlContent, String key, String type)
			throws DocumentException, IOException {
		ITextRenderer renderer = new ITextRenderer();
		List<DocInfo> docInfos = new ArrayList<DocInfo>();
		try {
			log.info("Forming metaData");
			System.out.println(fileLocation);
			renderer.setDocumentFromString(htmlContent);
			renderer.layout();
			renderer.createPDF(new FileOutputStream(fileLocation));
			renderer.finishPDF();
			MultipartFile multipartFile = new MockMultipartFile(fileNamePrefix + fileNameSuffix,
					fileNamePrefix + fileNameSuffix, "", new FileInputStream(new File(fileLocation)));
			MultipartFile[] multipartFileArray = new MultipartFile[1];
			multipartFileArray[0] = multipartFile;
			docInfos = docMgmtService.uploadDocAsBinary(type, multipartFileArray);
			System.out.println(docInfos);
			docInfos.get(0).setDocExtension("pdf");
			docInfos = docMgmtService.checkInDocs(docInfos);
			log.info("Temp DocId: " + docInfos.get(0).getDocId());
			afterDocCheckIn(docInfos.get(0).getDocId(), key);
		} catch (IOException e) {
			log.error("Error: Generating pdf: " + key);
			e.printStackTrace();
		}
		return docInfos;
	}

	private void afterDocCheckIn(String docId, String key) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", commonUtils.getLoggedinAppToken());
			System.out.println(headers);
			Map<String, String> x = new HashMap<String, String>();
			x.put("docId", docId);
			x.put("key", key);
			System.out.println(x);
			HttpEntity<Map<String, String>> request = new HttpEntity<Map<String, String>>(x, headers);
			String url = sillMatrixResultUrl + "/update";
			System.out.println(url);
			commonUtils.getRestTemplate().exchange(url, HttpMethod.POST, request, Boolean.class);
		} catch (RestClientException e) {
			log.error("Error: After Doc check");
			e.printStackTrace();
		}
	}

	private String prepareContext(List<GapAnalysisPrintObject> gapObj, String type, List<EngAssessmentPrintObject> engObj) {
		final Context ctx = new Context(Locale.ENGLISH);
		
		String updatedBy = "";
		Optional<AuthUser> authUser = authUserRepo.findById(Integer.parseInt(commonUtils.getLoggedinUserId()));
		if (authUser.isPresent()) {
			updatedBy = authUser.get().getFirstName();
		}
		
		Date now = new Date();
		String currentdate = now.toString();
		ctx.setVariable("curentdate", currentdate);
		ctx.setVariable("updatedBy", updatedBy);
		
		if (type.equals("ENG-ASSESSMENT")) {
			ctx.setVariable("res", engObj);
		} else {
			ctx.setVariable("res", gapObj);
		}
		List<DocumentType> docTypes = docTypeHelper.findByDocTypeCode(type);
		fileNameSuffix = ".pdf";
		fileNamePrefix = type + LocalDateTime.now();
		System.out.println("docTypes: " + docTypes);
		fileLocation = "/opt/so/data/docs/" + docTypes.get(0).getFirstFolderName() + fileNameSuffix;
		final String htmlContent = this.htmlTemplateEngine.process(docTypes.get(0).getTemplateName(), ctx);
		return htmlContent;
	}
}
