import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { LeaveApplicationService } from '../leave-approvel.service';
import { DatePipe } from '@angular/common';
import { LeaveApplication } from '../../../vo/leave-application';
import { LeaveType } from '../../../vo/leaveTypes';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'detail',
    templateUrl: 'leave-approvel-detail.component.html'
})

export class LeaveApprovelDetailComponent implements OnInit {
   

    @ViewChild('Approve') Approve: TemplateRef<any>;
    @ViewChild('Reject') Reject: TemplateRef<any>;

	la:LeaveApplication;
	leaveDetails:LeaveType;
    CL:any;
	
    modalReference:any = null;
  

   
    constructor(private router:Router,private route: ActivatedRoute, private modalService: NgbModal,private datePipe: DatePipe, private service: LeaveApplicationService) { }

    ngOnInit() { 

        this.la = new LeaveApplication();
        this.leaveDetails = new LeaveType();

        this.route.params.switchMap((par: Params) => this.service.getLeaveHistoriesById(par['_id'])).subscribe(x=>{
        this.la = x;
        console.log(x);
            this.getLeaveDays(x.employeeId);
        })
    
    }



    getLeaveDays(id:any){
        this.service.getLeaveBalance(id).subscribe(leave=>{
			for(let list of leave){
				if(list.leaveTypeId=='1'){
					this.CL=list.leaveBalance;
				}
            }
            this.leaveType();
		});
	}
    
    leaveType(){
		this.leaveBalance();
		this.service.getLeaveTypesById(this.la.leaveTypeId).subscribe(x=>{
			this.leaveDetails=x;
		});
    }
    
    leaveBalance(){
		this.service.getLeaveBalance(this.la.employeeId).subscribe(leave=>{
			for(let list of leave){
				if(list.leaveTypeId==this.la.leaveTypeId){
					this.CL=list.leaveBalance;
				}
			}
		})
	}

	approveLeave(){
        console.log(this.la);
        if(this.la.leaveStatus=='N1-APPROVAL-PENDING'){
            this.la.needN2Approval=(this.la.needN2Approval?"Y":"N");
            this.service.LeaveUpdate("n1-approve",this.la).subscribe(x=>{
                this.modalReference.close();  
                this.router.navigateByUrl("/my-task/leave-approvel/view");
            });
        }
        if(this.la.leaveStatus=='N2-APPROVAL-PENDING'){
            this.service.LeaveUpdate("n2-approve",this.la).subscribe(x=>{
                this.modalReference.close();  
                this.router.navigateByUrl("/my-task/leave-approvel/view");
            });
        }
       

	}
	rejectLeave(){

        if(this.la.leaveStatus=='N1-APPROVAL-PENDING'){
            this.service.LeaveUpdate("n1-reject",this.la).subscribe(x=>{
                this.modalReference.close();  
                this.router.navigateByUrl("/my-task/leave-approvel/view");
            });
        }
        if(this.la.leaveStatus=='N2-APPROVAL-PENDING'){
            this.service.LeaveUpdate("n2-reject",this.la).subscribe(x=>{
                this.modalReference.close();  
                this.router.navigateByUrl("/my-task/leave-approvel/view");
            });
        }
		
    }
    
   


    back(){
        this.modalReference.close();  
    }
    
          approve(){
            this.modalReference = this.modalService.open(this.Approve, {size: 'lg'});	
          }

          reject(){
            this.modalReference = this.modalService.open(this.Reject, {size: 'lg'});	
          }  


}