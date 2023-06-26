import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response, URLSearchParams } from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { environment } from './../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
import { LeaveApplication } from './../../vo/leave-application';

@Injectable()
export class LeaveApplicationService {

    baseUrl: string = environment.serviceApiUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;

    constructor(private http: Http,private commonService: CommonService) {
    }

    private jwt(paramArr?: any) {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json' });
		return new RequestOptions({ headers: headers });
    }
	
	getUser(){
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		if (currentUser) return currentUser;
	}
	
	getLeaveTypes(){		
        return this.http.get(this.baseUrl + 'seed/leave-types', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	getLeaveBalance(id:any){		
        return this.http.get(this.baseUrl + 'master/leave-balances?employeeId='+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
	
	getLeaveTypesById(id:any){		
        return this.http.get(this.baseUrl + 'seed/leave-types/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	
	getLeaveHistoriesById(id:any){		
        return this.http.get(this.baseUrl + 'transaction/leave-applications/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	
	getAllLeaveHistoryN1(){
		return this.http.get(this.baseUrl + 'transaction/leave-applications/leave-applns-for-my-approval', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	LeaveUpdate(type:any,la:LeaveApplication){
		console.log(type);
		console.log(la);
		return this.http.patch(this.baseUrl+'transaction/leave-applications/'+type,la,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	
}