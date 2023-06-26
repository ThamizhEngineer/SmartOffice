import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response, URLSearchParams } from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { environment } from './../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
@Injectable()
export class LeaveBalanceService {

    baseUrl: string = environment.serviceApiUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;

    constructor(private http: Http,private commonService:CommonService) {
    }

    private jwt() {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json' });
		return new RequestOptions({ headers: headers });
    }
	
	getFiscalYears(){		
        return this.http.get(this.baseUrl + 'seed/fiscal-years', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
	
	getMultpleDefinitions(id){		
        let batches = [];
		batches.push(this.http.get(this.baseUrl + 'master/leave-balances?fiscalYearId='+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()));
		batches.push(this.http.get(this.baseUrl + 'seed/leave-types', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()));
		batches.push(this.http.get(this.baseUrl + 'seed/fiscal-years', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()));
		batches.push(this.http.get(this.baseUrl + 'master/employees', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()));
		
		return Observable.forkJoin(batches);
    }
	
	updateLeaveBalance(lbs){
		return this.http.patch(this.baseUrl + 'master/leave-balances/bulk-update', lbs, this.commonService.jwt()).map((response: Response) => response).map(res => res.json())
	}
	
	deleteLeaveBalance(id: string){
		return this.http.delete(this.baseUrl + 'master/leave-balances/'+ id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
}