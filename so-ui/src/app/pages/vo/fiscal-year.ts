export class FiscalYear {
    id?: string;
    fiscalCode?: string;
	fromDt?: string;
	vfromDt?: any;
	toDt?: string;
	vtoDt?: any;
	fyPrefix?: string;
	isEnabled?: string;
	createdBy?: string;
	modifiedBy?: string;
	createdDt?: string;
	modifiedDt?: string;
}

export class PayCycle {
    id?: string;
    fiscalYearId?: string;
	payCycleCode?: string;
	payCycleType?: string;
	isActive?: string = "Y";
	startDt?: string;
	isEnabled?: string = "Y";
	createdBy?: string;
	modifiedBy?: string;
	createdDt?: string;
	modifiedDt?: string;
	companyPayCycleLines?: Array<PayCycleLine>;
}

export class PayCycleLine {
    id?: string;
    compPayCycleId: string;
	compPayCycleLineCode: string;
	fromDt?: string;
	vfromDt?: any;
	toDt?: string;
	vtoDt?: any;
	payDt?: string;
	vpayDt?: any;
	processedDate?: string;
	vprocessedDate?: any;
	isEnabled?: string = "Y";
	createdBy?: string;
	modifiedBy?: string;
	createdDt?: string;
	modifiedDt?: string;
}