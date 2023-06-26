import { Component,OnInit,ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { FormGroup, FormBuilder, Validators,FormControl } from '@angular/forms';

import { LeaveApplicationService } from '../leave-application.service';
import { LeaveApplication } from './../../../vo/leave-application';
import { DatePipe } from '@angular/common';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LeaveType } from '../../../vo/leaveTypes';

@Component({
    selector: 'leave-create',
    templateUrl: './create.component.html',
})
export class LeaveApplicationCreateComponent implements OnInit {

	@ViewChild('Ack') Ack: TemplateRef<any>;
	@ViewChild('Job') Job: TemplateRef<any>;
	@ViewChild('Deny') Deny: TemplateRef<any>;




	constructor(private router:Router,
		private modalService: NgbModal,		
		private route: ActivatedRoute,
		private datePipe: DatePipe,
		private _service: LeaveApplicationService){

    }

	leaveTypes: any;
	modalReference:any;
	la: LeaveApplication;
	las:any=[];
	currentUser: any;
	update:boolean;
	leaveDetails:LeaveType;
	id:any;
	CL:any;
	checkJob:boolean=true;

	form: FormGroup;

	type:FormControl;
	fDt:FormControl;
	tDt:FormControl;
	remarks:FormControl;
	showJobs:any=[];

	shiftName:string;
	
	
    ngOnInit() {
		let user = this.currentUser = this._service.getUser();
		this.la = new LeaveApplication();
		this.leaveDetails = new LeaveType();
		this.la.employeeId = user.employeeId;		

		this._service.getEmployeeById(user.employeeId).subscribe(x=>{
			this.shiftName=x.optional.shiftName;
		})

		this._service.getLeaveTypes().subscribe(x=>this.leaveTypes = x);
		this.route.queryParams.subscribe(params=>{
			this.id=params.id;
		})
		
		this.getLeaveById();
		this.validate();
		this.createForm();
	
    }

	saveMsg: any;

	getLeaveById(){
		
			if(this.id!=null){
				this._service.getLeaveHistoriesById(this.id).subscribe(x=>{
				this.la=x;
				this.leaveType();
				this.jobLeave();
				this.objModify(this.la);
			});
		}
		
		
	}

	createApplyLeave(){
		console.log(this.la);
		this._service.createApplyLeave('create',this.la).subscribe(x=>{
			this.id=x.id;
			this.router.navigate(['/my-space/my-leave/detail/'] ,{queryParams:{id:x.id}});
			this.saveMsg = {'type': 'success', 'text': x.eligibleRemarks}
			this.la = new LeaveApplication();
			this.la.employeeId = this.currentUser.employeeId;
			// console.log(this.la);
		});
		
	}

	leaveType(){
		this.leaveBalance();
		this._service.getLeaveTypesById(this.la.leaveTypeId).subscribe(x=>{
			this.leaveDetails=x;
		});
	}

	jobLeave(){
		console.log(this.la.startDt+'end Date'+this.la.endDt)
		if(this.la.startDt!=null&&this.la.endDt!=null){
			this._service.jobLeave(this.la.employeeId,this.la.startDt,this.la.endDt).subscribe(x=>{
				this.showJobs=x;
				console.log(this.showJobs);
				if(this.showJobs[0]!=null){
					this.checkJob=false;
				}else{
					this.checkJob=true;
				}
			});			
		}

		
	}

	validate(){
		this.type = new FormControl('', [Validators.required]);
		this.fDt = new FormControl('', [Validators.required]);
		this.tDt = new FormControl('', [Validators.required]);
		this.remarks = new FormControl('', [Validators.required]);
	}

	createForm(){
		this.form = new FormGroup({
			type:this.type,
			fDt:this.fDt,
			tDt:this.tDt,
			remarks:this.remarks
		});		
	}

	objModify(la:LeaveApplication){
		this.form.patchValue({
			type: la.leaveTypeId,
			fDt:la.startDt,
			tDt:la.endDt,
			remarks:la.reason
		});
	}

	checkValue(){
		if(this.la.jobAckgmt==null){
			this.la.jobAckgmt="Y";
		}
		if(this.la.emgncyAvailability==null){
			this.la.emgncyAvailability="Y";
		}
		if(this.la.leaveDenialAckgmt==null){
			this.la.leaveDenialAckgmt="Y";
		}
	}

	applyLeave(){
		
		this.checkValue();
		if(this.form.valid){
			console.log(this.la);
		this._service.createApplyLeave('apply',this.la).subscribe(x=>{
			this.id=x.id;
			this.modalReference.close();  
			this.saveMsg = {'type': 'success', 'text': x.eligibleRemarks}
			this.router.navigate(['/my-space/my-leave/detail/'] ,{queryParams:{id:x.id}});
		});
	}
	}
	cancelLeave(){
		this._service.LeaveUpdate('cancel',this.la).subscribe(x=>{
			this.navigateToListPage();
		},error=>{
			this.saveMsg = {'type': 'danger', 'text': 'Leave Application cannot in cancelled at this stage'}
		})

	}

	leaveBalance(){
		this._service.getLeaveBalance(this.currentUser.employeeId).subscribe(leave=>{
			for(let list of leave){
				if(list.leaveTypeId==this.la.leaveTypeId){
					this.CL=list.leaveBalance;
				}
			}
		})
	}

	back(){
        this.modalReference.close();  
    }

	navigateToListPage(){
		this.router.navigateByUrl('/my-space/my-leave/');
	}

	submit(){
		this.jobLeave();
		console.log(this.checkJob);
		if(this.checkJob==true){
			this.infos();	
		}else{
			this.job();
		}
	}


	infos(){ 
		if(this.checkJob==false){
			this.modalReference.close(this.Job); 
			this.la.jobAckgmt="Y";  			
		}    
		this.modalReference = this.modalService.open(this.Ack, {size: 'lg'});
	}
	job(){
		this.modalReference = this.modalService.open(this.Job, {size: 'lg'});
	}
	deny(){
		this.modalReference.close(this.Ack); 
		this.la.emgncyAvailability="N";
		this.la.leaveDenialAckgmt="Y";
		this.modalReference = this.modalService.open(this.Deny, {size: 'lg'});
	}
}


