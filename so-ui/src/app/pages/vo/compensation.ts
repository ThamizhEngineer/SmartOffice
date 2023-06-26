export class Compensation {
    id?: string;
    employeeId?: string;
	validFromDt?: string;
	validToDt?: string;	
	head1Name?: string = "A1. Fixed Benefits";
	head2Name?: string = "A2. Additional Fixed Benefits";
	head3Name?: string = "B. Variable Benefits";
	head4Name?: string = "C. Annual Benefits";
	head5Name?: string = "D. Retirement Benefits";
	head6Name?: string;
	head7Name?: string;
	head8Name?: string;
	head9Name?: string;
	head10Name?: string;
	head1Total?: string;
	head2Total?: string;
	head3Total?: string;
	head4Total?: string;
	head5Total?: string;
	head6Total?: string;
	head7Total?: string;
	head8Total?: string;
	head9Total?: string;
	head10Total?: string;	
	
	fixedBenefits?: string;
	employeeCtcLines?: Array<CTCLine>;
	
	isActive?: string = "Y";
	isEnabled?: string = "Y";
	createdBy?: string;
	modifiedBy?: string;
	createdDt?: string;
	modifiedDt?: string;
}

export class CTCLine {
    id?: string;
    employeeCtcId?: string;
	processOrder?: number;
	sPayoutTypeId?: number;	
	head?: string;
	payCycleId?: string;
	isLumpSum?: string = "N";
	ruleType?:string;
	cusType?:string;
	formula?: string;
	maxLimit?: string;
	lineDesc?: string;
	calculatedAmt?: string;
	hiddenToEmployee?: string;
	isAllowance?:string;
	isEnabled?: string = "Y";
	createdBy?: string;
	modifiedBy?: string;
	createdDt?: string;
	modifiedDt?: string;
}