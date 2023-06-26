export class LeaveApplication {
	appSystemId:           string;
	appUserId:             string;
	id:                    number;
	leaveCode:             string;
	employeeId:            string;
	empFname:              string;
	employeeCode:          string;
	empLname:              string;
	leaveTypeId:           string;
	leaveTypeName:         string;
	leaveTypeCode:         string;
	startDt:               string;
	startSession:          string='FN';
	leaveAppliedDate:      string;
	endDt:                 string;
	endSession:            string='AN';
	reason:                string;
	shiftId:			   string;
	shiftName:			   string;
	leaveStatus:           string;
	leaveStatusName:       string;
	eligibilityStatusCode: string;
	eligibilityStatusName: string;
	eligibleRemarks:       string;
	n1EmpId:               string;
	n1EmpCode:             string;
	n1EmpFname:            string;
	n1EmpLname:            string;
	n1Decision:            string;
	n1DecisionDt:          string;
	n1Remarks:             string;
	needN2Approval:        string;
	n2EmpId:               string;
	n2EmpCode:             string;
	n2EmpFname:            string;
	n2EmpLname:            string;
	n2Decision:            string;
	n2DecisionDt:          string;
	n2Remarks:             string;
	isSettled:             string;
	hr1SettlementDt:       string;
	hr1UserGroupId:        string;
	hr1UserGroupName:      string;
	hr1EmpId:              string;
	hr1EmpFname:           string;
	hr1EmpLname:           string;
	eligible:              string;
	eligibleDuration:      number;
	jobAckgmt:			   string;
	emgncyAvailability:	   string;
	leaveDenialAckgmt:     string;
	isEnabled:             string;
	createdBy:             string;
	modifiedBy:            string;
	createdDt:             string;
	modifiedDt:            string;
	listAuthFeatures:      string;
}