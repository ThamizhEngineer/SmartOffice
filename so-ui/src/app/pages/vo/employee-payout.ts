export class EmployeePayout{
	 id?:string;
     employeeId ?:string;
     employeeName ?:string;
     employeeCode ?:string;
	 isActive ?:string;
	 compPayCycleId ?:string;
	 compPayCycleType?:string;
	 totalAllowanceAmt ?:string;
	 totalDeductionAmt ?:string;
	 totalD1value ?:string;
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
	  dPayTypeName ?:string;
	  ytdAmt  ?:string;
	  lineAmt  ?:string;
	  payTypeName  ?:string;
	  isEnabled  ?:string;
	  createdBy  ?:string;
	  modifiedBy  ?:string;
}