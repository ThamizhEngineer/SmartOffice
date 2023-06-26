package com.ss.smartoffice.soservice.transaction.appraisalservice;

import javax.persistence.Column;

import org.hibernate.annotations.Formula;

public class GoalAnalysis {

private String appraisalHdrId;
@Column(name="m_metric_id")
private String metricId;
@Formula("(select line.operator from t_org_obj_line line where line.metric_id=m_metric_id)")
private String operator;
@Formula("(select metric.metric_name from m_metric metric where metric.id=m_metric_id)")
private String metricName;
private String finalTarget;
private String goalDesc;
public GoalAnalysis() {
	super();
	// TODO Auto-generated constructor stub
}



public GoalAnalysis(String appraisalHdrId, String metricId, String operator,String metricName, String finalTarget, String goalDesc) {
	super();
	this.appraisalHdrId = appraisalHdrId;
	this.metricId = metricId;
	this.operator=operator;
	this.metricName = metricName;
	this.finalTarget = finalTarget;
	this.goalDesc = goalDesc;
}



public String getAppraisalHdrId() {
	return appraisalHdrId;
}



public void setAppraisalHdrId(String appraisalHdrId) {
	this.appraisalHdrId = appraisalHdrId;
}



public String getMetricId() {
	return metricId;
}



public void setMetricId(String metricId) {
	this.metricId = metricId;
}



public String getOperator() {
	return operator;
}



public void setOperator(String operator) {
	this.operator = operator;
}



public String getMetricName() {
	return metricName;
}



public void setMetricName(String metricName) {
	this.metricName = metricName;
}



public String getFinalTarget() {
	return finalTarget;
}



public void setFinalTarget(String finalTarget) {
	this.finalTarget = finalTarget;
}



public String getGoalDesc() {
	return goalDesc;
}



public void setGoalDesc(String goalDesc) {
	this.goalDesc = goalDesc;
}



@Override
public String toString() {
	return "GoalAnalysis [appraisalHdrId=" + appraisalHdrId + ", metricId=" + metricId + ", operator=" + operator
			+ ", metricName=" + metricName + ", finalTarget=" + finalTarget + ", goalDesc=" + goalDesc + "]";
}






}
