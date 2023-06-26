export class AppraisalReview {
    id:                number;
    appraisalGoalId:   string;
    reviewDt:          string;
    reviewTypeCode:    string;
    isOverDue:         string;
    reviewSubDt:       string;
    comments:          string;
    empComments:       string;
    empAcheivedTarget: string;
    empRemarks:        string;
    reviewScoreCode:   number;
    reviewScoreName:   string;
    reviewStatusCode:  string;
    reviewCompStatus:  string;
    metricId:          string;
    metricName:        string;
    n1Operator:        string;
    n1Target:          string;
    unitDispSymbol:    string;
    appraisalHdrId:    string;
    fyCode:            string;
    empId:             string;
    empName:           string;
    isEnabled:         string;
    createdBy:         string;
    modifiedBy:        string;
    createdDt:         string;
    modifiedDt:        string;

    select:            boolean;
}
