export class EmployeePayout{
     employeeId ?:string;
     employeeName ?:string;
     employeeCode ?:string;
	 isActive ?:string;
	 compPayCycleId ?:string;
	 compPayCycleType?:string;
	 totalAllowanceAmt ?:string;
	 totalDeducationAmt ?:string;
	 netPayAmt ?:string;
	 remarks ?:string;
	 isEnabled ?:string;
	 createdBy ?:string;
	 modifiedBy ?:string;
	 fromDt?:Date;
	 toDt?:Date;
	 payDt?:Date;
	 employeePayoutLines:Array<EmployeePayoutLine>;
}

export class EmployeePayoutLine {
	  employeePayoutId  ?:string;
	 lineOrder  ?:number;
	  isAllowance  ?:string;
	  payOutType  ?:string;
	  ytdAmt  ?:string;
	  lineAmt  ?:string;
	  payTypeName  ?:string;
	  isEnabled  ?:string;
	  createdBy  ?:string;
	  modifiedBy  ?:string;
}