export class OrgHeader {
    id:         number;
    fyYearId:   string;
    empId:      string;
    empName:    string;
    status:     string;
    isEnabled:  string;
    createdBy:  string;
    modifiedBy: string;
    createdDt:  string;
    modifiedDt: string;
    orgLines:   OrgLine[];
}

export class OrgLine {
    id:             number;
    orgObjHeaderId: string;
    metricId:       string;
    metricName:     string;
    metricSummary:  string;
    operator:       string;
    metricValue:    string;
    metricCategoryId:string;
    metricCategoryName:string;
    metricLevelCode:string;
    unitTypeSymbol: string;
    descp:    string;

    isEnabled:      string;
    createdBy:      string;
    modifiedBy:     string;
    createdDt:      string;
    modifiedDt:     string;

    dummySymbol:   string;
    threshold: string;
}
