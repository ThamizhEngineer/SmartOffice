export class OrgAnalysis {
    id:                   number;
    fyId:                 string;
    fyName:               string;
    metricId:             string;
    metricName:           string;
    target:               string;
    threshold:            string;
    teamTarget:           string;
    empTarget:            string;
    resltsAcheivedByTeam: string;
    acheivVerdictName:    string;
    goalDesc:             string;
    statusCheck:          string;
    httpResponseStatus:   string;
    isEnabled:            string;
    createdBy:            string;
    createdDt:            string;
    triggerDt:            string;
    lastCompDt:           string;
    resultAnalysisByEmps: { [key: string]: string | string }[];
}

export class resultEmps{
    employeeName:string;
    designationName:string;
    targetEmp:string;
    empAcheiv:string;
    managerScore:string;
    managerRemarks:string;
    isEnabled : string;
    createdBy : string;
    createdDt : string;
    officeName : string;
}