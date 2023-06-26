import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response, URLSearchParams } from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { environment } from './../../../environments/environment';
import { CommonService } from '../../shared/common/common.service';


@Injectable()
export class CompensationService {

    baseUrl: string = environment.serviceApiUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;

    constructor(private http: Http, private commonService: CommonService) {
    }

    
	getUser(){
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		if (currentUser) return currentUser;
	}
	
	getEmployees(){		
        return this.http.get(this.baseUrl + 'master/employees', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
	
	getEmployeeById(id){		
        return this.http.get(this.baseUrl + 'master/employees/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
	
	getCTCsById(id){		
        return this.http.get(this.baseUrl + 'master/pay/memployee-ctcs/',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
	
	getPayoutTypes(){		
        return this.http.get(this.baseUrl + 'seed/payout-types', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
	
	getMultipleData(id: number) {
        let batches = [];
		batches.push(this.http.get(this.baseUrl + 'master/employees', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()))
		batches.push(this.http.get(this.baseUrl + 'master/employees/'+id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json()))
		batches.push(this.http.get(this.baseUrl + 'seed/payout-types',this.commonService.jwt()).map((response: Response) => response).map(res => res.json()))
		batches.push(this.http.get(this.baseUrl + 'master/pay/memployee-ctcs/?employeeId=' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()))

        return Observable.forkJoin(batches);
    }
	
	updateCTCs(ctcs: any){		
        return this.http.post(this.baseUrl + 'master/pay/memployee-ctcs/', ctcs, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
	
	getMultpleDefinitions(){		
        let batches = [];
		batches.push(this.http.get(this.baseUrl + 'seed/it-configs', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()));
		batches.push(this.http.get(this.baseUrl + 'seed/ctc-configs', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()));
		batches.push(this.http.get(this.baseUrl + 'seed/fiscal-years', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()));
		
		return Observable.forkJoin(batches);
    }
	
	getFiscalYears(){		
        return this.http.get(this.baseUrl + 'seed/fiscal-years',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
	
	updateITslab(slabs){	
		return this.http.patch(this.baseUrl + 'seed/it-configs/bulk-update', slabs, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
	
	updateDeduction(configs){
		return this.http.patch(this.baseUrl + 'seed/ctc-configs/bulk-update', configs, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	
	getGrades(){		
        return this.http.get(this.baseUrl + 'seed/grades',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	
	getDetailFromConfig(type){
		return this.http.get(environment.serviceApiUrl+'seed/configs?configDtlCode='+type, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	
	getMultpleGradeComp(){		
	    let batches = [];
		batches.push(this.http.get(this.baseUrl + 'seed/grades',this.commonService.jwt()).map((response: Response) => response).map(res => res.json()));
		batches.push(this.http.get(this.baseUrl + 'seed/payout-types',this.commonService.jwt()).map((response: Response) => response).map(res => res.json()));
		
		return Observable.forkJoin(batches);
    }
}