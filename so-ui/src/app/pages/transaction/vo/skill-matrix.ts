export class SkillMatrixRequestHdr{
    id :number
    status:string
    key:string
    trigrredBy:string
    trigrredDt:string
    processType:string
    bkProcessId:string
    isError:string
    errorMessage  :string  
    deptId:string
    officeId:string
    isSpecificSkillList:string
    productId:string
    productFamilyId:string
    manufactureId:string
    isEnabled:string
    createdBy:string
    createdDt:Date
    modifiedBy:string
    modifiedDt:Date
}

export class SkillMatrixResultQueryObject{
        id : number
		mEmployeeId : number
		mProductId : number
		mProfileId : number
		skillLevelCode : string
		SkillMatrixHdrKey : string
}

export class SkillMatrixRequestSkills{
    id : number
	SkillMatrixHdrKey : string
	productId : string
	productName:string='';
	serviceId : string
	serviceName:string='';
	expectedCount:number;
	isEnabled : string
	createdDt : Date
	modifiedBy : string
	modifiedDt : Date  
	horizontal : [SkillMatrixResult]
}

export class SkillMatrixRequestEmp{
    id : number
	SkillMatrixHdrKey : string
	employeeId : string
	employeeName:string='';
	departmentId : string
	deptName : string
	isEnabled : string
	createdBy : string
	createdDt : Date
	modifiedBy : string
	modifiedDt : Date
}

export class SkillMatrixResult {
	id:                number;
	skillMatrixHdrKey: string;
	employeeId:        string;
	employeeCode:      string;
	employeeName:      string;
	deptId:            string;
	deptName:          string;
	productId:         string;
	productName:       string;
	serviceId:         string;
	serviceName:       string;
	skillLevelCode:    number;
	type:              string;
	isTraining:        string;
	isError:           string;
	errorMessage:      string;
	bkProcessId:       string;
	isEnabled:         string;
	createdDt:         string;
	modifiedBy:        string;
	modifiedDt:        string;
	deptCode:          string;
	n1EmpId:           string;
	n2EmpId:           string;
	designationId:     string;
	designationName:   string;
	isSpanNeed:		   string;
	horizontal:       [SkillMatrixResult];
    vertical:         [SkillMatrixResult];
}
