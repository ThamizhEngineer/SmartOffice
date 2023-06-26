export class JobType {
    id?:string;
    jobTypeCode?:string;
    jobTypeName?:string;
    jobDesc?:string;
	jobTypeProfile: Array<JobTypeProfile> = [];
	jobTypeMaterials: Array<JobTypeMaterials> = [];
	jobTypeTaskTypes: Array<JobTypeTaskType> = [];
	jobTypeStageCount?:string;
	jobTypeMaterialCount?:string;
	jobTypeProfileCount?:string;
	isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}

export class JobTypeProfile{
    id?:string;
    jobtypeId?:string;
    stageOrder?:string;
    profile?: any;
    mProfileId?:string;
    profileName?:string="";
    mProfileCode?:string;
    skillId?:string;
    skillLevelCode?:string;
    profilesInvolved?:string;
    profileDescription?:string;
    headCount?:string;
    notes?:string;
    isRemote?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}

export class JobTypeMaterials{
    id?:string;
    jobtypeId?:string;
    materialTypeOrder?:string;
    materialQuantity?:string;
    materialId?:string;
    materialName?:string="";
    materialCode?:string="";
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}

export class JobTypeTaskType{
    id?:string;
    jobtypeId?:string;
    tjobPlanId?:string;
    taskTypeOrder?:string;
    stageId?:string;
    taskTypeId?:string;
    taskTypeName?:string="";
    weightage?:string;
    isEnabled?:string;
    createdBy?:string;
    modifiedBy?:string;
    createdDt?:Date;
    modifiedDt?:Date;
}