import { Component,OnInit,ViewEncapsulation } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { LeaveApplicationService } from '../leave-application.service';
import { status_css } from '../../../vo/status'
import { User } from '../../../../auth/_models';

@Component({
    selector: 'leave-show',
    templateUrl: './show.component.html',
})
export class LeaveApplicationShowComponent implements OnInit {

    constructor(private router:Router, private route: ActivatedRoute, private _service: LeaveApplicationService){

    }

	active:any=[];
	inactive:any=[];
	list:any=[];
	collectionSize:any;
	user:User;
	page :number = 1;
    pageSize :number = 10;

	binding:string;
	isShiftId:Boolean=false;
	statuses: any = status_css;
	
    ngOnInit() {
		this.binding='true';
		this.user = this._service.getUser();

		this._service.getEmployeeById(this.user.employeeId).subscribe(x=>{
			if(x.optional.shiftId!=null){
				this.isShiftId=true;
			}
		})
		
		this._service.getLeaveHistories().subscribe(x=>{
			this.list=x;
			 for(let list of this.list){
				if(list.leaveStatus=='CREATED'||list.leaveStatus=='N1-APPROVAL-PENDING'||list.leaveStatus=='N2-APPROVAL-PENDING'){
					this.active.push(list);
				}else{
					this.inactive.push(list);
				}
			 }				
			 this.collectionSize=this.list.length;
		});
	}
	
	
	changeView(id:any){		
        this.binding=id;
    }

	navigateToDetailPage(id:number){		
		this.router.navigate(['/my-space/my-leave/detail/'] ,{queryParams:{id:id}});
   }
}


