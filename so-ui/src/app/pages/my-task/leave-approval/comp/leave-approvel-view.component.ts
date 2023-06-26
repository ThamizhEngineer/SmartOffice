import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { LeaveApplicationService } from '../leave-approvel.service';
import { status_css } from '../../../vo/status'

@Component({
    selector: 'view',
    templateUrl: 'leave-approvel-view.component.html'
})

export class LeaveApprovalRequestComponent implements OnInit {
	
	searchEmp = (text$: Observable<string>) =>text$.debounceTime(200).map(term => term === '' ? []
        : this.allEmployees.filter(v => v.empName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10));
	formatter = (x: {empName: string}) => x.empName;
		
	las: any;
	active:any=[];
	inactive:any=[];
	allEmployees: any;
	statuses:any=status_css;
	leaveTypes: any;
	currentUser: any;
	binding:any;

    constructor(private router:Router, private route: ActivatedRoute, private _service: LeaveApplicationService){

    }

    ngOnInit() {
		let user = this.currentUser = this._service.getUser();
		
		this.binding='true';
		this._service.getAllLeaveHistoryN1().subscribe(x=>{
			console.log(x);
		for(let list of x){
			if(list.leaveStatus=='N1-APPROVAL-PENDING' || list.leaveStatus=='N2-APPROVAL-PENDING' ){
				this.active.push(list);
			}else{
				this.inactive.push(list);
			}
		}		
		});
    }
	
	
	
	changeManager(value:any){
		this.binding=value;
		
	}
	
	
	navigateToDetailPage(id:number){
		console.log(id)
		this.router.navigateByUrl("/my-task/leave-approvel/detail/"+id);
   }
}
