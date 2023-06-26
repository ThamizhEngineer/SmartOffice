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
	
	getLeaveTypesById(id:any){		
        return this.http.get(this.baseUrl + 'seed/leave-types/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
	
	getEmployeeById(id:any){		
        return this.http.get(this.baseUrl + 'master/emp-profiles/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
	getLeaveHistories(){		
        return this.http.get(this.baseUrl + 'transaction/leave-applications/my-leave-applns', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	getLeaveHistoriesById(id:any){		
        return this.http.get(this.baseUrl + 'transaction/leave-applications/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
	
	getMultipleData(){
		let batches = [];
		batches.push(this.http.get(this.baseUrl + 'seed/leave-types', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()));
		batches.push(this.http.get(this.baseUrl + 'transaction/leave-applications/my-leave-applns' ,this.commonService.jwt()).map((response: Response) => response).map(res => res.json()));
		// batches.push(this.http.get(this.baseUrl + 'transaction/leave-applications', this.jwt() ).map((res: Response) => res.json()));
		console.log(batches);
        return Observable.forkJoin(batches);
	}
	
	getRequestMultipleData(id){
		let batches = [];
		batches.push(this.http.get(this.baseUrl + 'seed/leave-types', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()));
		batches.push(this.http.get(this.baseUrl + 'transaction/leave-applications', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()));
		batches.push(this.http.get(this.baseUrl + 'master/employees', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()));

		return Observable.forkJoin(batches);
		
	}
	
	// applyLeave(la: LeaveApplication){		
    //     return this.http.post(this.baseUrl + 'transaction/leave-applications', la, this.jwt()).map((response: Response) => response.json());
    // }
	
	// updateLeave(id, las){
	// 	return this.http.patch(this.baseUrl + 'transaction/leave-applications/'+id, las, this.jwt()).map((response: Response) => response.json());
	// }

	// approveLeave(id, las){
	// 	return this.http.patch(this.baseUrl + 'transaction/leave-applications/'+id+'/approval', las, this.jwt()).map((response: Response) => response.json());
	// }
	createLeaveApplication(){
	return this.http.post(this.baseUrl + 'transaction/leave-applications/create',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	updateLeaveApplication(la:LeaveApplication){
		return this.http.patch(this.baseUrl+'transaction/leave-applications/'+la.id,'/update',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	approveLeave(la:any){
		return this.http.patch(this.baseUrl+'transaction/leave-applications/'+la+'/approval',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	// applyLeave(la:LeaveApplication){
	// 	return this.http.patch(this.baseUrl+'transaction/leave-applications/apply',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	// }


	getLeaveBalance(id:any){		
        return this.http.get(this.baseUrl + 'master/leave-balances?employeeId='+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
	

	createApplyLeave(type:any,la:LeaveApplication){
		console.log(la.reason);		
		return this.http.post(this.baseUrl+'transaction/leave-applications/'+type,la,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	LeaveUpdate(type:any,la:LeaveApplication){
	return this.http.patch(this.baseUrl+'transaction/leave-applications/'+type,la,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	rejectLeave(la:LeaveApplication){
		return this.http.patch(this.baseUrl+'transaction/leave-applications/'+la.id,'/reject',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}

	jobLeave(id,fromDt,toDt){
        return this.http.get(this.baseUrl + 'transaction/leave-applications/jobs-in-leave?employeeId='+id+'&&fromDt='+fromDt+'&&toDt='+toDt, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
}