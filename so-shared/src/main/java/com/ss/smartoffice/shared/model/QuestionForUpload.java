package com.ss.smartoffice.shared.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonPropertyOrder({"CATEGORY_CODE","DIFFICULTY_CODE","QUESTION","OPTION1","OPTION2","OPTION3","OPTION4","CORRECT_OPTION"})	
public class QuestionForUpload {

	@JsonProperty("CATEGORY_CODE")
	public String categoryCode;
	@JsonProperty("DIFFICULTY_CODE")
	public String difficultyLevel;
	@JsonProperty("QUESTION")
	public String question;
	@JsonProperty("OPTION1")
	public String option1;
	@JsonProperty("OPTION2")
	public String option2;
	@JsonProperty("OPTION3")
	public String option3;
	@JsonProperty("OPTION4")
	public String option4;
	@JsonProperty("CORRECT_OPTION")
	public String correctOption;
	public String isClean;
	public String processMessage;

}
