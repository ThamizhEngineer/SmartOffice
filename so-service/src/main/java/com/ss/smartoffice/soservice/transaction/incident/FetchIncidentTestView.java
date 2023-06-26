package com.ss.smartoffice.soservice.transaction.incident;

import java.time.LocalDateTime;

public interface FetchIncidentTestView {

	Integer getId();
	Integer getParticipantId();
	String getStartTime();
	String getApplicantName();
	String getDuration();
	LocalDateTime getTestDate();
	String getTestStatus();
	String getNoOfQuestions();
	String getTotalUnAttended();
	String getTestTemplateName();
}
