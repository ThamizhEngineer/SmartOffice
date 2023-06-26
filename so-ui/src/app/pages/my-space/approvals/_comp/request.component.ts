import { Component,OnInit,ViewEncapsulation } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import {Observable} from 'rxjs/Observable';

import { LeaveApplicationService } from '../../my-leave/leave-application.service';
import { LeaveApplication } from './../../../vo/leave-application';

const names = ['Emp A - Jay Paul', 'Emp B - Surendra', 'Emp C - Ramnath', 'Emp D - Somnath', 'Emp E - Suresh'];

@Component({
    selector: 'leave-request',
    templateUrl: './request.component.html',
})
export class LeaveApprovalRequestComponent implements OnInit {
	
	searchEmp = (text$: Observable<string>) =>text$.debounceTime(200).map(term => term === '' ? []
        : this.allEmployees.filter(v => v.empName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10));
	formatter = (x: {empName: string}) => x.empName;
		
	las: any;
	allEmployees: any;
	leaveTypes: any;
	currentUser: any;

    constructor(private router:Router, private route: ActivatedRoute, private _service: LeaveApplicationService){

    }

    ngOnInit() {
		let user = this.currentUser = this._service.getUser();
		
		this._service.getRequestMultipleData(user.id).subscribe(x=>{
			this.leaveTypes = x[0]; 
			let las = x[1];
			this.allEmployees = x[2]; 
			
			this.objModify(las);
		});
    }
	
	objModify(las: any){
		las = las.filter(x=>(!x.leaveStatus || x.leaveStatus == 'PENDING'));
		las.forEach(x =>{
			let l = this.leaveTypes.filter(y=>(x.leaveTypeId == y.id));
			x.leaveType = l.length>0? l.pop(): {};
			let e = this.allEmployees.filter(y=>(x.employeeId == y.id));
			x.emp = e.length>0? e.pop(): {};
			x.appEmployeeId = this.currentUser.employeeId;
		});
		this.las = las;
	}
	
	selEmp(){
		
	}
	
	onCheck(data){ data.checked = !data.checked; }
	
	selectedRows: any;
	approveLeave(){
		let las = this.las.filter(x=>x.checked == true);
		las.forEach(x=>{
			x.leaveStatus = 'APPROVED';
			x.checked = false;
			this._service.approveLeave(x.id).subscribe(y=>{
				let la = this.las.filter(l=>l.id==y.id).pop();
				la.leaveStatus = 'APPROVED';
			});
		});
		
	}
	
	rejectLeave(){
		let las = this.las.filter(x=>x.checked == true);
		las.forEach(x=>{
			x.leaveStatus = 'REJECTED';
			x.checked = false;
			this._service.rejectLeave(x.id).subscribe(y=>{
				let la = this.las.filter(l=>l.id==y.id).pop();
				la.leaveStatus = 'REJECTED';
			});
		});
	}
}


