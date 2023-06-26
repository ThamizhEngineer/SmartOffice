import {Task} from '../vo/task';
import { JobPlanEmp,JobPlanMaterial,JobPlanProfile,JobPlanTaskType} from '../vo/job-plan'
export class Job{
    id?:string;
    jobCode?:string;
    jobName?:string;
    contactName?:string;
    contactPhone?:string;
    contactEmail?:string;
    mJobTypeId?:string;
    projId?:string;
    projName?:string="";
    jobLats?:string;
    jobLongs?:string;
    jobLocation?:string;
    mapLocationId?:string;
    siteLocationId?:string;
	siteLocationName?:string="";
    expStartDt?:Date;  
    expEndDt?:Date;  
    clientConfStatus?:string;
    partnerId?:string;
    endClientId?:string;
    clientName?:string;
    clientMobNo?:string;
    clientEmailId?:string;
    saleOrder?:string;
    saleOrderId?:string;
    saleOrderCode?:string="";
    startDt?: string = new Date().toJSON();  
    endDt?: string = new Date().toJSON();  
    jobPlanStatus?:string;
    jobTypeName?:string="";
    confirmationContent
    clientRemarks?:string;
    jobTrackStatus?:string;
    ptwDt?:Date;  
    noOfBays?:string;
    trackProgressDt?:Date;  
    momDraftApprovalDt?:Date;  
    jobCompleteDt?:Date;  
    receivedPtwDt?:Date;  
    receivedPtwReviews?:string;
    ptwDocId?:string;
    receivedPtwRemarks?:string;
    siteSurveyRemarks?:string;
    siteSurveyCompDt?:Date;  
    surveyDocId?:string;
    clientFeedbackScore?:string;
    clientFeedbackRemarks?:string;
    momDraftPreparedDt?:Date;  
    momDraftPreparedDocId?:string;
    momDraftPreparedRemarks?:string;
    momDraftApprovedDt?:Date;
    momDraftApprovedDocId?:string;
    momDraftApprovedRemarks?:string;
    mapLocationName?:string;
    endClientName?:string="";
    docAttachedReport1?:string;
    docAttachedReport2?:string;
    docAttachedReport3?:string;
    docAttachedReport4?:string;
    docAttachedReport5?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
    jobEmployees?:Array<JobEmployee>
    jobMaterials?:Array<JobMaterial>
    jobTips?:Array<JobTip>
    jobDocs?:Array<JobDoc>
    jobChatHistories?:Array<JobChatHistory>
    jobTravels?:Array<JobTravel>
    jobProfiles?:Array<JobProfile>
    jobAccomodations?:Array<JobAccomdation>
    jobMilestones?:Array<JobMilestone>
    jobAssets?:Array<JobAsset>
    jobPlanProfiles?: Array<JobPlanProfile> = [];
	jobPlanEmps?: Array<JobPlanEmp> = [];
	jobPlanMaterials?: Array<JobPlanMaterial> = [];
	jobPlanTaskTypes?: Array<JobPlanTaskType> = [];
}
export class JobEmployee{
    id?:string;
    tJobId?:string;
    employeeId?:string;
    expStartDt?:string;  
    expEndDt?:string;  
    tJobProfileId?:string;
    hasReported?:string;
    hasDeparted?:string;
    isJobTravelComplete?:string;
    isJobAccComplete?:string;
    reportedRemarks?:string;
    departedRemarks?:string;
    reportedDt?:Date;  
    departedDt?:Date;  
    clientFeedbackScore?:string;
    clientFeedbackRemarks?:string;
    jobCode?:string;
    mJobTypeId?:string;
    partnerId?:string;
    endClientId?:string;
    clientName?:string;
    jobTypeName?:string;
    mapLocationId?:string;
    mapLocationName?:string;
    jobName?:string;
    endClientName?:string;
    employeeName?:string;
    jobProfileName?:string="";
    showStartDt?:string;
    showEndDt?:string;
}

export class JobMaterial{
    id?:string;
    jobId?:string;
    taskTypeOrder?:string;
    materialTypeOrder?:string;
    materialQuantity?:string;
    materialTypeName?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}

export class JobTip{
    id?:string;
    jobId?:string;  
    tips?:string;  
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}

export class JobDoc{
    id?:string;
    jobId?:string;  
    docId?:string;  
    docName?:string; 
    creauploadDttedDt?:Date;
    uploadUserId?:string;
    docTypeCode?:string;
    isSourceOnsite?:string;
    remarks?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date; 
}

export class JobChatHistory{
    id?:string;
    jobId?:string; 
    senderId?:string; 
    senderName?:string;
    receiverId?:string;
    receiverName?:string; 
    message?:string; 
    messageTime?:Date;
    userType?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}

export class JobTravel{
    id?:string;
    tJobId?:string;
    tJobEmpId?:string;
    travelType?:string;
    travelNumber?:string;
    travelTime?:string;
    pnrNo?:string;
    boardingPoint?:string;
    droppingAt?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}

export class JobProfile{
    id?:string;
    tJobId?:string;
    profileId?:string;
    profileName?:string;
    headCount?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}

export class JobAccomdation{
    id?:string;
    jobId?:string;
    jobEmpId?:string;
    accType?:string;
    accName?:string;
    accDate?:string;
    bookingId?:string;
    pickUp ?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;    
}

export class JobMilestone{
    id?:string;
    jobId?:string;
    jobCode?:string;
    mJobTypeId?:string;
    partnerId?:string;
    endClientId?:string;
    clientName?:string;
    jobTypeName?:string;
    jobLocation?:string;
    jobName?:string;
    endClientName?:string;
    milestoneName?:string;
    milestoneOrder?:string;
    milestoneDesc?:string;
    milestoneStatus?:string;
    progress?:string;
    jobTaskListCount:string;
	openFlag:boolean = false;  
    jobTaskList?:Array<JobTaskList>;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;  
}

export class JobTaskList{
    id?:string;
    milestoneId?:string;
    taskListName?:string;
    taskListDesc?:string;
    status?:string;
    progress?:string;
    taskListOrder?:string;
	openFlag:boolean = false;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date; 
    tasks?:Array<Task>;
}

export class JobAsset{
    id?:string;
    jobId?:string;
    assetId?:string;
    fromDt?:Date;
    toDt?:Date;
    comments?:string;
    mEmployeeId?:string;
    employeeName?:string="";
    jobMasterAssetId?:string;
    assetOwner?:string;
    assetName?:string="";
    jobAssetDocs?:Array<JobAssetDocs>;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}

export class JobAssetDocs{
    id?:string;
    jobId?:string;
    jobAssetsId?:string;
    docId?:string;
    docName?:string;
    uploadDt?:Date;
    remarks?;string;
    uploadUserId?:string;
    docTypeCode?:string;
    isSourceOnsite?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}