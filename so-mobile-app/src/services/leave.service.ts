import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";
import "rxjs/add/operator/map";
import { environment } from './../environments/environment';
import { CommonService } from '../providers/common.service';
import{Leave} from '../pages/leave/vo/leave';

@Injectable()
export class LeaveService {

    constructor(private http: Http, 
                private commonService: CommonService,
                ) { }

    getCurrentUser() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser && currentUser.appToken) return currentUser;
        return null;
    }

    getLeaveById(leaveId:any){
return this.http.get(environment.serviceApiUrl+'transaction/leave-applications/'+leaveId,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }

    addLeave(leave: Leave){
        console.log(leave);

        
        return this.http.post(environment.serviceApiUrl + 'transaction/leave-applications/create-apply',leave, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
      
    }
    approveLeave(leaveId:any,leave:Leave){
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let obj = JSON.stringify({  managerEmployeeId: currentUser.employeeId,managerRemarks : leave.managerRemarks});
        return this.http.patch(environment.serviceApiUrl+'transaction/leave-applications/'+leaveId+'/approve',obj,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }
    rejectLeave(leaveId:any,leave:Leave){
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let obj = JSON.stringify({  managerEmployeeId: currentUser.employeeId,managerRemarks : leave.managerRemarks,leaveStatus:'REJECTED'});
        return this.http.patch(environment.serviceApiUrl+'transaction/leave-applications/'+leaveId+'/approve',obj,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
    }
}