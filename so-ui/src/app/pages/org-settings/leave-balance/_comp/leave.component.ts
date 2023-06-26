import { Component, OnInit, ViewChild, TemplateRef, ViewEncapsulation } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { LeaveBalanceService } from '../leave-balance.service';

import { LeaveBalance } from '../../../vo/leave-balance';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'org-leave-balance',
    templateUrl: './leave.component.html',
	styleUrls:[ 'card.css' ],
	encapsulation: ViewEncapsulation.None 
})
export class OrgLeaveBalanceComponent implements OnInit {

	@ViewChild('cdelete') cdelete: TemplateRef<any>;
    constructor(private router:Router, private route: ActivatedRoute, private modalService: NgbModal, private _service:LeaveBalanceService){

    }

	isOpen: boolean = false;
	isEdit: boolean = false;
	isModified: boolean = false;
	editLBs: any = [];
	newLBs: any = [];
	allLBs: any;
	leaveBalances: any;
	leaveTypes: any;
	fiscalYears: any;
	allEmployees: any;
	fyear: string;
	
	newLB: LeaveBalance;
    
	ngOnInit() {
		
		this.newLB = new LeaveBalance();
		
		
		if (this.route.params['_value']['fyear']) {
			this.route.params.subscribe( (params: Params)=>{
				let id = this.fyear = params['fyear'];
				this.newLB.fiscalYearId = id;
				
				this._service.getMultpleDefinitions(id).subscribe(x=>{
					
					this.leaveTypes = x[1];
					this.fiscalYears = x[2];
					this.allEmployees = x[3];
					this.objModify(x[0]);
				});
			});
		}
		else this._service.getFiscalYears().subscribe(x=> this.fiscalYears = x );
		
    }
	
	objModify(lb){
		lb.forEach(x=>{
			x.emp = this.allEmployees.filter(y=>y.id == x.employeeId).pop();
			x.ltype = this.leaveTypes.filter(y=>y.id == x.leaveTypeId).pop();
		});
		this.allLBs = this.leaveBalances = lb;
	}
	
	selectedEmp: any;
	selEmp(emp){
		if(emp.id){
			this.selectedEmp = emp;
			this.leaveBalances = this.allLBs.filter(x=>x.employeeId == emp.id);
		}
		else this.leaveBalances = this.allLBs;
	}
	
	yearChange($e){
		this.router.navigateByUrl("/org-settings/leave-balance/"+$e);
	}
	
	
	saveMsg: any;
	saveLeaveBalances(){
		this.editLBs.forEach(x=>x.edited = false);
		let lbs = [...this.newLBs, ...this.editLBs];
		this._service.updateLeaveBalance(lbs).subscribe(x=>{
			this.saveMsg = {'type': 'success', 'text': "Leave Balance Added Successfully"};
			this.allLBs = [...this.allLBs, ...this.newLBs];
			this.leaveBalances = [...this.leaveBalances, ...this.newLBs];
			this.newLBs = [];
			
		}, e=> {
			this.saveMsg = {'type': 'success', 'text': "Error in Service"};
			
		});
	}
	
	modalReference:any = null;
	modalData: any;
	deleteLB(lb: LeaveBalance){
		this.modalData = lb;
		this.modalReference = this.modalService.open(this.cdelete);
	}
	
	editLB(lb:LeaveBalance){
		this.newLB = lb;
		this.isOpen = true;
		this.isEdit = true;
	}
	
	confirmDelete(data){
		
		this._service.deleteLeaveBalance(data.id).subscribe(x=>{
			this.saveMsg = {'type': 'success', 'text': "Leave Balance Deleted Successfully"};
			this.allLBs = this.leaveBalances = this.allLBs.filter(obj => obj !== data);
			this.modalReference.close();
		}, e=> {
			this.saveMsg = {'type': 'success', 'text': "Leave Balance Deleted Successfully"};
			this.allLBs = this.leaveBalances = this.allLBs.filter(obj => obj !== data);
			this.modalReference.close();
		});
	}
	
	addLB(lb: LeaveBalance){
		lb.emp = this.allEmployees.filter(y=>y.id == lb.employeeId).pop();
		lb.ltype = this.leaveTypes.filter(y=>y.id == lb.leaveTypeId).pop();
		lb.edited = true;
		this.isEdit = false;
		this.isOpen = false;
		
		if(lb.id) this.editLBs.push(lb);
		else this.newLBs.push(lb);
		
		this.newLB = new LeaveBalance();
		
		this.newLB.fiscalYearId = this.fyear;
	}
	
	deleteNewLB(lb: LeaveBalance){
		this.newLBs = this.newLBs.filter(obj => obj !== lb);
	}
}


