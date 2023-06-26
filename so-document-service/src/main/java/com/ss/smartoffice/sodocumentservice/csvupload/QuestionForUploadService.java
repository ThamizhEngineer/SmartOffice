package com.ss.smartoffice.sodocumentservice.csvupload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.dm.DocInfoRepository;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.Question;
import com.ss.smartoffice.shared.model.QuestionForUpload;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@RestController
@RequestMapping("csv/question-bank")
public class QuestionForUploadService {
	@Value("${test-category.url}")
	private String testCaregoryUrl;
	@Value("${question.url}")
	private String questionUrl;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	DocumentManagementHelper documentManagementHelper;
	@Autowired
	DocInfoRepository docInfoRepo;
	
	static List<String> knownDifficulty = new ArrayList<String>(Arrays.asList("EASY","MEDIUM","TOUGH"));
	static List<String> knownOptions = new ArrayList<String>(Arrays.asList("1","2","3","4"));
	static List<String> KnownKeyWords = new ArrayList<String>(Arrays.asList("OPTION1","OPTION2","OPTION3","OPTION4","CORRECT_OPTION","DIFFICULTY_CODE","CATEGORY_CODE"));

	
//	----------------------------------------
	

	@PostMapping("/upload/{docTypeCode}")
	public List<DocInfo> triggerQuestionUpload(@PathVariable(value = "docTypeCode") String docTypeCode, @RequestParam("uploadingFiles") MultipartFile[] filesToUpload)
			throws SmartOfficeException {
		List<DocInfo> docInfo =  documentManagementHelper.uploadDocsAsBinary(docTypeCode, filesToUpload);
    	List<Question> qs = new ArrayList<Question>();
		for (DocInfo d : docInfo) {
			System.out.println(d.getDocId());
			if (isNullOrEmpty(d.getDocId())==false) {
				try {
					String path = d.getDocLocation()+"/"+d.getDocName();
					qs = trigger(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
		return docInfo;
	}
	
//	----------------------------------------

	    @GetMapping
	    public  List<Question> trigger(String path) throws IOException {
	        File input = new File(path);
	        List<QuestionForUpload> data = readObjectsFromCsv(input);
	        List<Question> qs = applyRequiredLogic(data);
	        return qs;
	    }
	    
	    public List<QuestionForUpload> readObjectsFromCsv(File file) throws IOException {
	        CsvMapper csvMapper = new CsvMapper();
	        CsvSchema bootstrap = csvMapper.schemaFor(QuestionForUpload.class).withHeader();
	        MappingIterator<QuestionForUpload> mappingIterator = csvMapper.readerFor(QuestionForUpload.class).with(bootstrap).readValues(file);
	        return mappingIterator.readAll();
	    }
	    
	    public List<Question> applyRequiredLogic(List<QuestionForUpload> data) {

	    	List<Question> qs = new ArrayList<Question>();
	    	List<Question> unCleanqs = new ArrayList<Question>();

			List<String> ids= getCategoryIds();

			for (QuestionForUpload questionForUpload : data) {
				try {
					questionForUpload = clearRecords(questionForUpload,ids);
					Question q  = processRecords(questionForUpload);
					if (q.getIsClean().equals("Y")) {
						qs.add(q);
					}
					else if (q.getIsClean().equals("N")) {
						unCleanqs.add(q);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			postCleanQuestions(qs);
	    	return unCleanqs;
	    }
	    
	    public Question processRecords(QuestionForUpload questionForUpload){
	    	Question q= new Question();
	    	q.setTestCategoryId(questionForUpload.getCategoryCode());
	    	q.setDifficultyLevel(questionForUpload.getDifficultyLevel());
	    	q.setIsCorrect(correctOption(questionForUpload.getCorrectOption()));
	    	q.setQuestion(questionForUpload.getQuestion());
	    	q.setOption1(questionForUpload.getOption1());
	    	q.setOption2(questionForUpload.getOption2());
	    	q.setOption3(questionForUpload.getOption3());
	    	q.setOption4(questionForUpload.getOption4());
	    	q.setIsClean(questionForUpload.getIsClean());
	    	q.setRemarks("Clean: "+questionForUpload.getIsClean()+",Message: "+questionForUpload.getProcessMessage());
	    	return q;
	    }
	    
//	    Validations
	    
	    public QuestionForUpload clearRecords(QuestionForUpload questionForUpload,List<String> ids) {

	    	if (isNullOrEmpty(questionForUpload.getCategoryCode())==true || isNullOrEmpty(questionForUpload.getQuestion())==true || 
	    			isNullOrEmpty(questionForUpload.getOption1())==true || isNullOrEmpty(questionForUpload.getOption2())==true || 
	    			isNullOrEmpty(questionForUpload.getOption3())==true || isNullOrEmpty(questionForUpload.getOption4())==true || 
	    			isNullOrEmpty(questionForUpload.getDifficultyLevel())==true || isNullOrEmpty(questionForUpload.getCorrectOption())==true) {
	    		questionForUpload.setIsClean("N");
	    		questionForUpload.setProcessMessage("All fields are mandatory");
			}else {
				
				if (checkKeyWords(questionForUpload.getCategoryCode())==true || checkKeyWords(questionForUpload.getQuestion())==true || 
	    			checkKeyWords(questionForUpload.getOption1())==true || checkKeyWords(questionForUpload.getOption2())==true || 
	    			checkKeyWords(questionForUpload.getOption3())==true || checkKeyWords(questionForUpload.getOption4())==true || 
	    			checkKeyWords(questionForUpload.getDifficultyLevel())==true || checkKeyWords(questionForUpload.getCorrectOption())==true) {
					questionForUpload.setIsClean("N");
		    		questionForUpload.setProcessMessage("Known KeyWords should not be used");
				}else {
					if (checkCategory(ids,questionForUpload.getCategoryCode())) {

			    		if (checkDifficulty(questionForUpload.getDifficultyLevel())==false) {
			    			questionForUpload.setIsClean("N");
				    		questionForUpload.setProcessMessage("Expected Values can be EASY, MEDIUM or TOUGH only");
						}
			    		else if(checkOptions(questionForUpload.getCorrectOption())==false) {
			    			questionForUpload.setIsClean("N");
				    		questionForUpload.setProcessMessage("Expected Values can be 1, 2, 3 or 4 only");
			    		}
			    		else {
				    		questionForUpload.setIsClean("Y");
				    		questionForUpload.setProcessMessage("Clean record");
			    		}

					}else {
			    		questionForUpload.setIsClean("N");
			    		questionForUpload.setProcessMessage("Not Known Category,  Please check the screen 'Question Category' for known list");
					}	
				}
			}
	    	return questionForUpload;
	    }
	    
//	    Check
	    
	    private String correctOption(String inValue) {
	    	String outValue = "";
	    	if (isNullOrEmpty(inValue)==false) {
	    		switch (inValue) {
				case "1":
					outValue="option1";
					break;
				case "2":
					outValue="option2";
					break;
				case "3":
					outValue="option3";
					break;
				case "4":
					outValue="option4";
					break;

				default:
					break;
				}
			}
	    	return outValue;
	    }
	    
	    private static boolean checkCategory(List<String> ids, String inputId) {
	        for (String id : ids) {
	            if (id.equals(inputId)) { 
	                return true;
	            }
	        }
	        return false;
	    }
	    private static boolean checkDifficulty(String inputDiff) {
	        for (String diff : knownDifficulty) {
	            if (diff.equals(inputDiff)) { 
	                return true;
	            }
	        }
	        return false;
	    }
	    private static boolean checkOptions(String inputOps) {
	        for (String ops : knownOptions) {
	            if (ops.equals(inputOps)) { 
	                return true;
	            }
	        }
	        return false;
	    }
	    private static boolean checkKeyWords(String inputOps) {
	        for (String ops : KnownKeyWords) {
	            if (ops.equals(inputOps)) { 
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    public static boolean isNullOrEmpty(String str) {
	        if(str != null && !str.isEmpty())
	            return false;
	        return true;
	    }
	    
//	    Rest Template
	    
		private List<String> getCategoryIds()throws SmartOfficeException{
			String tc = new String();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization",commonUtils.getLoggedinAppToken() ); 
			HttpEntity<String>request=new HttpEntity<String>(tc,headers);
			ResponseEntity<String[]> ids = commonUtils.getRestTemplate().exchange(testCaregoryUrl,
					HttpMethod.GET, request,String[].class);			
			return Arrays.asList(ids.getBody());
		}
		
		private void postCleanQuestions(List<Question> questions)throws SmartOfficeException{ 
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization",commonUtils.getLoggedinAppToken() ); 
			HttpEntity<List<Question>>request=new HttpEntity<List<Question>>(questions,headers);
			ResponseEntity<Question[]> qsFromDB = commonUtils.getRestTemplate().exchange(questionUrl,
					HttpMethod.POST, request,Question[].class);
			//commonUtils.getRestTemplate().postForObject(questionUrl,questions,Question[].class);
		}
	    

}
