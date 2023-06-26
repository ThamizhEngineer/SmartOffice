import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";
import "rxjs/add/operator/map";

import { environment } from './../environments/environment';
import { CommonService } from '../providers/common.service';

@Injectable()
export class MySpaceService {

    constructor(private http: Http, 
                private commonService: CommonService,
                ) { }

    getCurrentUser() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser && currentUser.appToken) return currentUser;
        return null;
    }

    getMyTasks(){ 
        return this.http.get(environment.serviceApiUrl + 'my-space/my-tasks', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }

    getMyLeaves(){
        return this.http.get(environment.serviceApiUrl + 'my-space/my-leaves', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }

    getMyLeaveApproval(){
        return this.http.get(environment.serviceApiUrl + 'my-space/my-leave-approvals', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }

    getMyAttendance(){
        return this.http.get(environment.serviceApiUrl + 'my-space/my-attendances', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }

    getMyTeamAttendance(date){
        return this.http.get(environment.serviceApiUrl + 'my-space/my-team-attendances?attendanceDt='+date , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }

    getMyHolidays(year){
        return this.http.get(environment.serviceApiUrl + 'my-space/my-holidays?holidayYear='+year, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }

    getMyExactPayslip(month,year){
        return this.http.get(environment.serviceApiUrl + 'my-space/my-payouts?dSalaryForMonth='+month+'&dSalaryForYear='+year, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }

    getAllMyPayslips(){
        return this.http.get(environment.serviceApiUrl + 'my-space/my-payouts', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    } 

    getAllLeaveTypes(){
        return this.http.get(environment.serviceApiUrl + 'master/leave-balances', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());     
    }
}