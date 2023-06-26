import { Component, OnInit ,ViewChild, TemplateRef  } from '@angular/core';
import { Router, ActivatedRoute, Route,Params } from '@angular/router';
import { EmployeePayout } from './../../../vo/employee-payout';
import { EmployeePayoutService } from '../employee-payout.service';

import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';

@Component({
    selector: 'employee-payout-list',
    templateUrl: './employee-payout-list.component.html'
})
export class EmployeePayoutListComponent implements OnInit {
    searchEmployeeId: any="";
    
    rows: Array<EmployeePayout>;
    page :number = 1;
    pageSize :number = 10;
	employees: any = [];
	empDetail: any;
    saveMsg: any;
    mpFromConfig = {'placeHolder': 'Salary Month', 'readonly':false};
    mpSalModel: any;

    constructor(private router: Router,private route: ActivatedRoute, private service: EmployeePayoutService) {
        let d = new Date();
        this.mpSalModel = {'month': ("0" + (d.getMonth())).slice(-2), 'year': d.getFullYear().toString()};
    }

	empAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.filterEmps(term) ) );
	formatter = (x: {empName: string}) => x.empName;
	
	filterEmps(term){
		term = term.toString();
		let e = this.employees.filter(v => v.empName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10);
		if(e.length == 0){
			e = this.employees.filter(v => v.id && v.id.toString().indexOf(term.toLowerCase()) > -1).slice(0, 10);
		}
        if(e.length == 0){
            e = this.employees.filter(v => v.empCode && v.empCode.toString().indexOf(term.toLowerCase()) > -1).slice(0, 10);
        }
		return e;
	}
	
    ngOnInit() {
		this.service.getEmployees().subscribe(_emps => {
            this.employees = _emps;
        })
        let salMon = new Date().getMonth().toString();let salYear = new Date().getFullYear().toString();
        this.service.getEmployeePayouts(null, salMon, salYear).subscribe(_emps => {
            this.rows = _emps;
        })
    }

    navigateToDetailPage(id: number) {
        this.router.navigateByUrl("/operation/employee-payout/detail/" + id);
    }

    search() {
        let empId;let salMon;let salYear;
        if(this.empDetail) empId = this.empDetail.id;
        if(this.mpSalModel){
            salMon = this.mpSalModel.month;
            salYear = this.mpSalModel.year;
        } 
        
        this.service.getEmployeePayouts(empId, salMon, salYear).subscribe(_emps => {
            this.rows = _emps;
        })
    }
	
	selEmp($event){
       if (typeof $event === "string") return this.empDetail = [];
       console.log($event)
       this.empDetail = $event;
    }

    sendEmail(employeePayout: EmployeePayout){
        this.service.sendEmail(employeePayout).subscribe(_emps => {          
            this.saveMsg = {'type': 'success', 'text': "Email Sent Successfully"}    
        })
    }

    mpOnModelChange($event){
        this.mpSalModel = $event;
    }
    
}
