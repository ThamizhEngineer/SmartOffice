export class AppraisalHdr {
    id:                        number;
    empId:                     string;
    fyId:                      string;
    n1EmpId:                   string;
    n2EmpId:                   string;
    officeId:                  string;
    designationId:             string;
    appraisalTargetStatusCode: string;
    appraisalAchvmtStatusCode: string;
    empCode:                   string;
    empFName:                  string;
    empLName:                  string;
    n1EmpFName:                string;
    n1EmpLName:                string;
    n2EmpFName:                string;
    n2EmpLName:                string;
    officeName:                string;
    fyName:                    string;
    designationName:           string;
    empTargetSubDt:            string;
    n1ReturnComment:           string;
    n1TargetReviewDt:          string;
    n2TargetReviewDt:          string;
    empTargetAcceptDt:         string;
    empTargetRejectDt:         string;
    n1returnComment:           string;
    escalatReason:             string;
    empNxtPos1:                string;
    empNxtPos2:                string;
    empCareerMove1:            string;
    empCareerMove2:            string;
    n1NxtPos1:                 string;
    n1NxtPos2:                 string;
    n1CareerPos1:              string;
    n1CareerPos2:              string;
    empMobility:               string;
    n1Mobility:                string;
    techScoreCode:             string;
    techScoreName:             string;
    behaveScoreCode:           string;
    behaveScoreName:           string;
    overalScoreCode:           string;
    overalScoreName:           string;
    empAreaOfStrength:         string;
    n1AreaOfStrength:          string;
    empAreaOfDev:              string;
    n1AreaOfDev:               string;
    isEnabled:                 string;
    createdBy:                 string;
    modifiedBy:                string;
    createdDt:                 string;
    modifiedDt:                string;

    settleUserGroupId:         string;
    settleUserGroup2Id:        string;
    skillObjectives:           SkillObjectives[];
    goals:                     Goal[];
    devActions:                DevAction[];
}

export class SkillObjectives {
    id:                 number;
    appraisalHdrId:     string;
    productId:          string;
    abilityId:          string;
    productName:        string;
    abilityName:        string;
    isExistingSkill:    string;
    currSkillLevel:     number;
    expectedSkillLevel: number;
    skillLevelFromEmp:  number;
    skillLevelFromN1:   number;
    skillLevelFinal:    number;
    isMandatory:        string;
    empRemarks:         string;
    n1Remarks:          string;
    isEnabled:          string;
    createdBy:          string;
    modifiedBy:         string;
    createdDt:          string;
    modifiedDt:         string;

    // for dummy data used only for UI 

    dummyId:            number;
    textEdit:           boolean;
}


export class DevAction {
    id:             number;
    appraisalHdrId: string;
    devAction:      string;
    targetDate:     string;
    resultsAcheiv:  string;
    isEnabled:      string;
    createdBy:      string;
    modifiedBy:     string;
    createdDt:      string;
    modifiedDt:     string;
}

export class Goal {
    id:                   number;
    dummyid:              number;
    priority:             number;
    dummyPriority:        number;
    appraisalHdrId:       string;
    strategy:             string;
    isTechnical:          string;
    isOrgGoal:            string;
    isOrgGoalDummy:       boolean;
    metricId:             string;
    operator:             string;
    target:               string;
    unitName:             string;
    unitDisp:             string;
    unitDesc:             string;
    metricName:           string;
    goalDesc:             string;
    reviewIntervalType:   string;
    empOperator:          string;
    empOperatorName:      string;
    empTarget:            string;
    n1Operator:           string;
    n1OperatorName:       string;
    n1Target:             string;
    n1TargetRemarks:      string;
    n2Operator:           string;
    n2OperatorName:       string;
    n2Target:             string;
    n2TargetRemarks:      string;
    empAchvmt:            string;
    n1EmpAchvmt:          string;
    n2EmpAchvmt:          string;
    empAchvmtRemarks:     string;
    n1AchvmtRemarks:      string;
    n2AchvmtRemarks:      string;
    empScoreCode:         string;
    empScoreName:         string;
    n1ScoreCode:          string;
    n1ScoreName:          string;
    n2ScoreCode:          string;
    n2ScoreName:          string;
    empResultSubDt:       string;
    n1ResultSubDt:        string;
    n2ResultSubDt:        string;
    finalScoreCode:       string;
    finalScoreName:       string;
    finalOperator:        string;
    finalTarget:          string;
    finalAchvmt:          string;
    goalTargetStatusCode: string;
    goalAchvmtStatusCode: string;
    isEmpAccept:          string;

    currSkillCount:       number;
    expectedSkillCount:   number;
    currSkillScore:       number;
    expectedSkillScore:   number;
    skillScoreFromEmp:    number;
    skillScoreFromN1:     number;
    skillScoreFinal:      number;
    isSkillGoal:          string;

    reviews:              Review[];
    isEnabled:            string;
    createdBy:            string;
    modifiedBy:           string;
    createdDt:            string;
    modifiedDt:           string;
}

export class Review {
    id:               number;
    appraisalGoalId:  string;
    reviewDt:         string;
    reviewTypeCode:   string;
    isOverDue:        string;
    reviewSubDt:      string;
    reviewCompDt:     string;
    reviewAchvmt:     string;
    comments:         string;
    reviewScoreCode:  string;
    reviewScoreName:  string;
    reviewStatusCode: string;
    isEnabled:        string;
    createdBy:        string;
    modifiedBy:       string;
    createdDt:        string;
    modifiedDt:       string;
}
