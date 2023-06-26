import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { ActivatedRoute,Params,Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { CommonService } from '../../../../shared/common/common.service';
import { ShiftChangeRequest } from '../../vo/shift-change';
import { User } from '../../../../auth/_models';
import { UserService } from '../../../../auth/_services';
import { ShiftChangeService } from '../shift-change.service';
import { status_css } from '../../../vo/status'
import { Employee } from '../../../vo/employee';


@Component({
    selector: '',
    templateUrl: 'shift-change-list.component.html'
})

export class ShiftChangeListComponent implements OnInit {
   
    @ViewChild('createShift') createShift: TemplateRef<any>;
    @ViewChild('viewShift') viewShift: TemplateRef<any>;
    @ViewChild('warning') warning: TemplateRef<any>;

    statuses:any=status_css;

    shiftChangeList:any=[];
    shiftRequest:ShiftChangeRequest;
    view:string;
    modalReference:any = null;
    user:User;
    employee:Employee;
    shifts:any=[];
    page :number = 1;
    pageSize :number = 10

    constructor(
        private activedRouter:ActivatedRoute,
        private _userService:UserService,
        private modalService: NgbModal,
        private service:ShiftChangeService
    ) { }

    ngOnInit() {

        this.user = this._userService.getCurrentUser();         
        if(this.activedRouter.params['_value']['view']){   
            this.activedRouter.params.subscribe(x=>{   
            this.view=x.view;
            this.ShiftView(x.view);
            });        
        }
        this.service.getShift().subscribe(x=>{
            this.shifts=x;
        })
        
     }

ShiftView(view){
    switch (view){
        case 'emp':
            this.employee = new Employee();
            this.service.getByEmployeeId(this.user.employeeId).subscribe(x=>{
                this.employee=x;
                console.log(this.employee);
            })
            this.service.getShiftChangeByEmployee().subscribe(x=>{
                this.shiftChangeList=x;
            })
        break;
        case 'manager':
            this.service.getShiftChangeByManager().subscribe(x=>{
                this.shiftChangeList=x;
            })
        break;
        case 'hr':
            this.service.getShiftChangeByHrManager().subscribe(x=>{
                this.shiftChangeList=x;
            })
        break;
    }
    
}

projectSelected($event){

    this.shiftRequest.currShiftName=null;
    this.shiftRequest.currShiftId=null;

    this.shiftRequest.empId=$event.id;
    this.shiftRequest.empName=$event.empName;   
    this.service.getByEmployeeId($event.id).subscribe(x=>{
        this.shiftRequest.currShiftName=x.shiftName;
        this.shiftRequest.currShiftId=x.shiftId;
    })
}

create(){
    this.shiftRequest = new ShiftChangeRequest();
    this.shiftRequest.empName='';
    if(this.view=='emp'){
        this.shiftRequest.empName=this.employee.empName;
        this.shiftRequest.currShiftName=this.employee.shiftName;
        this.shiftRequest.currShiftId=this.employee.shiftId;
    }
    console.log(this.shiftRequest);
    this.modalReference = this.modalService.open(this.createShift, { size: 'lg' });
}

viewDetail(id){
    this.shiftRequest = new ShiftChangeRequest();
    this.service.getShiftChangeById(id).subscribe(x=>{
        this.shiftRequest=x;
        console.log(this.shiftRequest);
    })
    this.modalReference = this.modalService.open(this.viewShift, { size: 'lg' });
}

alert(){
    this.modalReference.close();
    this.modalReference = this.modalService.open(this.warning, { size: 'lg' });
}

shiftCreate(){
    if(this.view=='emp'){
        this.shiftRequest.empId=this.user.employeeId;
        this.service.createShiftChange('request',this.shiftRequest).subscribe(x=>{
            this.modalReference.close();
            this.ngOnInit();
        })
    }else if(this.view!='emp'){
        this.service.createShiftChange('proxy-request',this.shiftRequest).subscribe(x=>{
            this.modalReference.close();
            this.ngOnInit();
        })
    }
}

save(action){
    this.service.createShiftChange(action,this.shiftRequest).subscribe(x=>{
        this.modalReference.close();
        this.ngOnInit();
    })
}

}