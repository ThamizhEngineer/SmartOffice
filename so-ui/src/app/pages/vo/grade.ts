export class Grade {
    id?: string;
    gradeCode: string;
	gradeName: string;
	gradeOrder: string;
	locationId?: string  = "1";
	isEnabled?: string = "Y";
	createdBy?: string;
	modifiedBy?: string;
	createdDt?: string;
	modifiedDt?: string;
}