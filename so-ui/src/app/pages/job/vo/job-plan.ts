export class JobPlan{
	appSystemId?: string;
	appUserId?: string;
	clientConfStatus?: string;
	clientEmailId?: string;
	clientMobNo?: string;
	endClientName?:string;
	clientName?: string;
	clientRemarks?: string;
	confirmationContent?: string;
	contactEmail?: string;
	contactName?: string;
	contactPhone?: any;
	createdBy?: string;
	createdDt?: string;
	endDt?:  string = new Date().toJSON();
	expEndDt?: string;
	expStartDt?: string;
	id?: any;
	isEnabled?: string;
	jobAccomodations?: string;
	jobBays?: string;
	jobChatHistories?: string;
	jobCode?: string;
	jobCompleteDt?: string;
	jobDoc?: string;
	jobLats?: string;
	mapLocationName?:string;
	jobLocation?: string;
	jobLongs?: string;
	jobMaterials?: string;
	jobPlanProfiles?: Array<JobPlanProfile> = [];
	jobPlanEmps?: Array<JobPlanEmp> = [];
	jobPlanMaterials?: Array<JobPlanMaterial> = [];
	JobPlanTaskTypes?: Array<JobPlanTaskType> = [];
	jobPlanStatus?: string;
	jobPlanTeams?: string;
	jobPlanTestKits?: string;
	jobStages?: string;
	jobTeam?: string;
	jobTeamStructure?: string;
	jobTestKits?: string;
	jobTrackBays?: string;
	jobTravels?: string;
	jobTypeId?: string;
	jobTypeName?: string;
	listAuthFeatures?: string;
	modifiedBy?: string;
	modifiedDt?: string;
	momDraftApprovalDt?: string;
	noOfBays?: string;
	partnerId?: string;
	projId?: string;
	projName?: string;
	siteLocationId?:string;
	siteLocationName?:string;
	ptwDt?: string;
	saleOrder?: string;
	saleOrderId?: string;
	startDt?: string = new Date().toJSON();
	trackProgressDt?: string;
	jobName?: string;
	mJobTypeId?: string;
	mapLocationId?: string;
	shiftId?:string;
}

export class JobPlanProfile{
	id?: string;
	profile?: any;
	tJobId?: string;
	mJobTypeId?: string;
	mJobTypeName?: string;
	profileId?: string;
	profileName?:string="";
	mProfileCode?: string;
	headCount?: string;
	notes?: string;
	employeeProfile?: string;
	isEnabled?: string;
	createdBy?: string;
	modifiedBy?: string;
	createdDt?: string;
	modifiedDt?: string;
}

export class JobPlanEmp{
	id?: string;
	profileName?: string;
	comptabilityScore?: string;
	distanceToLocation?: string;
	profileId?: string;
	jobId?: string;
	employeeId?: string;
	employeeName?: string;
	startDt?:  string = new Date().toJSON();
	endDt?:  string = new Date().toJSON();
	isEnabled?: string;
	createdBy?: string;
	modifiedBy?: string;
	createdDt?: string;
	modifiedDt?: string;

	jobPlanEmpSkills?:Array<JobPlanEmpSkill>

	jobPlanEmpCommitments?:Array<JobPlanEmpCommitment>
}

export class JobPlanMaterial{
	id?: string;
	jobId?: string;
	materialId?: string;
	materialCode?: string;
	materialName?: string;
	taskTypeOrder?: string;
	materialQuantity?: string;
	materialTypeOrder?: string;
	stageOrder?: string;
	isEnabled?: string;
	createdBy?: string;
	modifiedBy?: string;
	createdDt?: string;
	modifiedDt?: string;
}

export class JobPlanTaskType{
	id?: string;
	jobId?: string;
	taskTypeId?: string;
	taskTypeName?: string;
	taskTypeOrder?: string;
	weightage?: number;
	isEnabled?: string;
	createdBy?: string;
	modifiedBy?: string;
	createdDt?: string;
	modifiedDt?: string;
}

export class JobPlanEmpSkill{
	id?: string;
	tJobPlanEmpId?: string;
	isMatched?: string;
	mProfileLineId?: string;
	serviceId?: string;
	serviceName?: string;
	productId?: string;
	productName?: string;
	skillLevel?: string;

	isEnabled?: string;
	createdBy?: string;
	modifiedBy?: string;
	createdDt?: string;
	modifiedDt?: string;
}

export class JobPlanEmpCommitment{
	id?: string;
	jobPlanEmpId?: string;
	commitmentType?: string;
	fromDt?: Date;
	toDt?: Date;
	leaveName?: string;
	holidayName?: string;
	lats?: string;
	longs?: string;
	distanceToLocation?: string;

	isEnabled?: string;
	createdBy?: string;
	modifiedBy?: string;
	createdDt?: string;
	modifiedDt?: string;
}