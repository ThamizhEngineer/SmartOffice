export class Holiday {
    id?: string;
    holidayName: string;
	holidayDt: string;
	firstSession: string = "Y";
	secondSession?: string = "Y";
	restrictedHoliday?: string = "N";
	locationId?: string  = "1";
	isAdhoc?: string  = "Y";
	isEnabled?: string = "Y";
	createdBy?: string;
	modifiedBy?: string;
	createdDt?: string;
	modifiedDt?: string;
}