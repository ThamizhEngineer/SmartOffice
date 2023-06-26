package com.ss.smartoffice.soservice.master.Questions;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.ss.smartoffice.shared.model.Question;

public interface QuestionRepo extends CrudRepository<Question,Integer> {
	
	@Query("select question from com.ss.smartoffice.shared.model.Question question where question.testCategoryId=:id")
	List<Question> findByTestCategoryId(String id);

	@Query("select question from com.ss.smartoffice.shared.model.Question question where question.difficultyLevel=:levelName And  question.testCategoryId=:id")
	List<Question> findByTestCategoryIdAndLevel(String levelName,String id);
	
	@Transactional
	@Modifying
	@Query("delete from com.ss.smartoffice.shared.model.Question question where question.testCategoryId=:testCategoryId")
	void deleteAllQueByTestCategoryId(@Param("testCategoryId") String testCategoryId);
}
