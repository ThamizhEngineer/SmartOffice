package com.ss.smartoffice.soservice.master.Questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ss.smartoffice.shared.model.Question;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("master/questions")
@Scope("prototype")
public class QuestionService {

	
	@Autowired
	QuestionRepo questionRepo;
	
	@Autowired
	CommonUtils commonutils;
	
	@GetMapping
	public Iterable<Question> getAllQuestions() throws SmartOfficeException{
		return questionRepo.findAll();
	}
	
	@GetMapping("/test/{id}")
	public Iterable<Question> getAllQuestionsByCatogeryId(@PathVariable(value="id")String id) throws SmartOfficeException{
		return questionRepo.findByTestCategoryId(id);
	}
	
	@GetMapping("/{id}")
	public Optional<Question> getQuestionById(@PathVariable(value="id")Integer id) throws SmartOfficeException{
		return questionRepo.findById(id);
	}
	
	@GetMapping("/{id}/{levelName}")
	public List<Question> getQuestionById(@PathVariable(value="id")Integer id,@PathVariable(value="levelName")String levelName) throws SmartOfficeException{
		return questionRepo.findByTestCategoryIdAndLevel(levelName, id.toString());
	}
	
	@PostMapping
	public Question createQuestion(@RequestBody Question question) throws SmartOfficeException{
		question.setCreatedBy(commonutils.getLoggedinEmployeeId());
		return questionRepo.save(question);
	}
	@PatchMapping("/{id}")
	public Question updateQuestion(@PathVariable(value="id")Integer id,@RequestBody Question question) throws SmartOfficeException{
		question.setModifiedBy(commonutils.getLoggedinEmployeeId());
		return questionRepo.save(question);
	}
	
	@GetMapping("/get-all")
	public Iterable<Question> getBycategoryNameAndLeve(@RequestParam(value = "categoryId",required = true) String categoryId,@RequestParam(value = "level",required = true) String level ){
		return questionRepo.findByTestCategoryIdAndLevel(level,categoryId);
	}
	
	@PostMapping("/bulk-update")
	public Iterable<Question> bulkaddAndUpdate(@RequestBody List<Question> questions) throws SmartOfficeException{
		List<Question> questionList=new ArrayList<Question>();
		
		try {
			for(Question que:questions) {
				try {
					if(que.getId()==null || que.getId()<=0) {
						que.setCreatedBy(commonutils.getLoggedinEmployeeId());				
						que = addNewRecord(que);
					}else {
						que.setModifiedBy(commonutils.getLoggedinEmployeeId());
						Question queFromDB =questionRepo.findById(que.getId()).orElse(new Question());
						que=this.matchAndUpdateFields(queFromDB,que);
					}
					questionList.add(questionRepo.save(que));
				} catch (Exception e) { 
					e.printStackTrace();
					System.out.println("Couldnt save this Question -->"+que);
					System.out.println("Moving to next question");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}
		
		return (Iterable<Question>)questionList;
		// return questionRepo.saveAll(questions);
	}
	
	private Question addNewRecord(Question fromReq) {
		Question q = new Question();
		q.setTestCategoryId(fromReq.getTestCategoryId());
		q.setQuestionCode(fromReq.getQuestionCode());
		q.setDifficultyLevel(fromReq.getDifficultyLevel());
		q.setQuestion(fromReq.getQuestion());
		q.setIsCorrect(fromReq.getIsCorrect());
		q.setOption1(fromReq.getOption1());
		q.setOption2(fromReq.getOption2());
		q.setOption3(fromReq.getOption3());
		q.setOption4(fromReq.getOption4());
		q.setQuestionCode(fromReq.getQuestionCode());
		q.setIsEnabled("Y");
		q.setCreatedBy(fromReq.getCreatedBy());	
		
		return q; 
	}
	
	private Question matchAndUpdateFields(Question fromDB, Question fromReq) {

		fromDB.setTestCategoryId(fromReq.getTestCategoryId());
		fromDB.setQuestionCode(fromReq.getQuestionCode());
		fromDB.setDifficultyLevel(fromReq.getDifficultyLevel());
		fromDB.setQuestion(fromReq.getQuestion());
		fromDB.setIsCorrect(fromReq.getIsCorrect());
		fromDB.setOption1(fromReq.getOption1());
		fromDB.setOption2(fromReq.getOption2());
		fromDB.setOption3(fromReq.getOption3());
		fromDB.setOption4(fromReq.getOption4());
		fromDB.setQuestionCode(fromReq.getQuestionCode());
		fromDB.setIsEnabled("Y");
		fromDB.setModifiedBy(fromReq.getModifiedBy());	
		return fromDB;
	}
	
	@DeleteMapping("/bulk-delete/{testCategoryId}")
	public void deleteAllQuestionByTestCategoryId(@PathVariable(value = "testCategoryId") String testCategoryId) throws SmartOfficeException{
		try {
			questionRepo.deleteAllQueByTestCategoryId(testCategoryId);
		} catch (Exception e) { 
			e.printStackTrace();
			System.out.println("Couldnt delete questions in testCategoryId -->"+testCategoryId);
			throw new SmartOfficeException(e.getMessage()); 
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteQuestion(@PathVariable(value="id") Integer id) throws SmartOfficeException{
		try {
			questionRepo.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Couldnt delete question with id -->"+id);
			throw new SmartOfficeException(e.getMessage()); 
		}
	}
	
}
