export class TrainingPreparation {
	id?: number;
	location?: string;
	locLat?: string;
	locLong?: string;
	startDate?: string;
	endDate?: string;
	duration?: string;
	trainingStatus?: string;
	trainingTypeId?: TrainingTypeId;
	trainerName?: string;
	trainerContDetails?: string;
	internalTrainer?: string;
	trainingSkill?: Array<TrainingSkill>;
	trainingAttendee?: Array<TrainingAttendee>;
	trainingFinalResult?: any;
	isEnabled?: string;
	createdBy?: string;
	modifiedBy?: string;
	createdDt?: string;
	modifiedDt?: string;
}
export class TrainingTypeId{
	createdBy?: string;
	createdDt?: string;
	endDt?: string;
	id?: number;
	isEnabled?: string;
	modifiedBy?: string;
	modifiedDt?: string;
	startDt?: string;
	trainingTypeName?: string;
}

export class TrainingSkill{
	createdBy?: string;
	createdDt?: string;
	endDt?: string;
	id?: number;
	isEnabled?: string;
	modifiedBy?: string;
	modifiedDt?: string;
	startDt?: string;
	skillLevel?: string;
	remarks?: string;
	skillTypeId?: string;
}


export class TrainingAttendee{
	createdBy?: string;
	createdDt?: string;
	endDt?: string;
	id?: number;
	isEnabled?: string;
	modifiedBy?: string;
	modifiedDt?: string;
	startDt?: string;
	attendeeStatus?: string;
	employeeId?: any;
}