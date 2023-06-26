package com.ss.smartoffice.soservice.transaction.appraisalservice;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
@Component
@Data
public class ResultAnalysis {
private String id;
private String fyId;
private String fyName;
private String metricId;
private String metricName;
private String operatorName;
private String scoreCode;
private String strategy;
private String isTechnical;
private String target;
private String threshold;
private String teamTarget;
private String empTarget;
private String isAcheived;
private String resltsAcheivedByTeam;
private String acheivVerdictName;
private String goalDesc;
private String statusCheck;
private String httpResponseStatus;
private String isEnabled;
private String createdBy;
private String createdDt;

private LocalDateTime triggerDt;

private LocalDateTime lastCompDt;
private List<ResultAnalysisByEmp>resultAnalysisByEmps;

}
