export class Leave{
    id: number;
	employeeId: string;
	leaveTypeId: string;
	leavesType: string;
	leaveApplnDt: string = new Date().toJSON();
	startDt:string = new Date().toJSON();
	endSession: string = "AN";
	endDt:string= new Date().toJSON();
	startSession: string = "FN";
	reason: string;
	leaveStatus: string;
	managerEmpId: string;
	managerRemarks: string;
manager2EmpId: string;
manager2Remarks:string;
	leaveAppDt: string = new Date().toJSON();
	isEnabled: string;
	createdBy: string;
	modifiedBy: string;
	createdDt: string;
	modifiedDt:  string;
}
