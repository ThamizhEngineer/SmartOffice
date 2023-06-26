package com.ss.smartoffice.soservice.transaction.incident;

public interface OverallIncidentResultView {
String getIncidentId();
String getFirstName();
String getLastName();
String getEmail();
String getMobileNumber();
String getExpType();
String getIsInterviewEligible();
String getIsEligibleForTest();
String getFinalTestStatus();
String getInterviewScheduledStatus();
String getFinalDecision();
String getFinalDecisionDt();
}
