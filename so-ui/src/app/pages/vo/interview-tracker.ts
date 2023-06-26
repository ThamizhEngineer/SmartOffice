export class InterviewTracker {
    id:                      number;
    incidentId:              string;
    applicantId:             string;
    jrId:                    string;
    jrCode:                  string;
    jrSummary:               string;
    applicantName:           string;
    interviewDate:           string;
    interviewDetail:         InterviewDetail[];
    resumeDocId:             string;
    coverLetter:             string;
    finalDecision:           string;
    finalDecisionRemarks:    string;
    finalDecisionEmpId:      string;
    firstInterviewerId:      string;
    firstInterviewerStatus:  string;
    secondInterviewerId:     string;
    secondInterviewerStatus: string;
    thirdInterviewerId:      string;
    thirdInterviewerStatus:  string;
    isEnabled:               string;
    createdBy:               string;
    modifiedBy:              string;
    createdDt:               string;
    modifiedDt:              string;
}

export class InterviewDetail {
    id:                 number;
    interviewerId:      string;
    interviewerName:    string;
    roundName:          string;
    interviewTime:      string;
    decision:           string;
    interviewTrackerId: string;
    rating:             string;
    isEnabled:          string;
    createdBy:          string;
    modifiedBy:         string;
    createdDt:          string;
    modifiedDt:         string;
    remarks:            string;
    dummyinterviewTime: string;
}
