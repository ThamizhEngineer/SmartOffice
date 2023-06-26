import { Component, OnInit } from '@angular/core';
import { NG_VALIDATORS, Validator, Validators, AbstractControl, ValidatorFn } from '@angular/forms';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { EmployeePayout ,EmployeePayoutLine} from './../../../vo/employee-payout';
import { EmployeePayoutService} from '../employee-payout.service';
@Component({
    selector: 'employee-payout-detail',
    templateUrl: './employee-payout-detail.component.html'
})
export class EmployeePayoutDetailComponent implements OnInit {
    addScreen: boolean = true;
    employeePayout:EmployeePayout;

    earningEmpPayoutLine:EmployeePayoutLine;
    deductionEmpPayoutLine:EmployeePayoutLine;
    earningEmpPayoutLines:Array<EmployeePayoutLine>;
    deductionEmpPayoutLines:Array<EmployeePayoutLine>;

    constructor(private router:Router,private route: ActivatedRoute,private service: EmployeePayoutService){

    }

    ngOnInit() {
        this.employeePayout = new EmployeePayout();
        this.earningEmpPayoutLines = new Array<EmployeePayoutLine>();
        this.deductionEmpPayoutLines = new Array<EmployeePayoutLine>();
     

    	if (this.route.params['_value']['_id']) {
			this.addScreen = false;
			this.route.params
				.switchMap((params: Params) => this.service.getEmployeePayoutById(params['_id']))
				.subscribe((x: EmployeePayout) => {
                    this.employeePayout = x

                    this.employeePayout.employeePayoutLines.forEach(line=>{
                        if(line.isAllowance=='Y'){

                            this.earningEmpPayoutLine = new EmployeePayoutLine();
                            this.earningEmpPayoutLine.employeePayoutId   = line.employeePayoutId;
                            this.earningEmpPayoutLine.lineOrder   = line.lineOrder;
                            this.earningEmpPayoutLine.isAllowance    = line.isAllowance;
                            this.earningEmpPayoutLine.payOutType    = line.payOutType;
                            this.earningEmpPayoutLine.ytdAmt    = line.ytdAmt;
                            this.earningEmpPayoutLine.lineAmt    = line.lineAmt;
                            this.earningEmpPayoutLine.payTypeName    = line.payTypeName;
                            this.earningEmpPayoutLine.isEnabled    = line.isEnabled;
                            this.earningEmpPayoutLine.dPayTypeName =line.dPayTypeName;
                            this.earningEmpPayoutLines.push(this.earningEmpPayoutLine);
                            
                        }else if(line.isAllowance=='N'){
                            this.deductionEmpPayoutLine = new EmployeePayoutLine();
                            this.deductionEmpPayoutLine.employeePayoutId   = line.employeePayoutId;
                            this.deductionEmpPayoutLine.lineOrder   = line.lineOrder;
                            this.deductionEmpPayoutLine.isAllowance    = line.isAllowance;
                            this.deductionEmpPayoutLine.payOutType    = line.payOutType;
                            this.deductionEmpPayoutLine.ytdAmt    = line.ytdAmt;
                            this.deductionEmpPayoutLine.lineAmt    = line.lineAmt;
                            this.deductionEmpPayoutLine.payTypeName    = line.payTypeName;
                            this.deductionEmpPayoutLine.isEnabled    = line.isEnabled;
                            this.deductionEmpPayoutLine.dPayTypeName =line.dPayTypeName;
                            this.deductionEmpPayoutLines.push(this.deductionEmpPayoutLine);
                        }
                    })
                  
				
				});
		}
    }
    
   
}