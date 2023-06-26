import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response, URLSearchParams } from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { environment } from './../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
@Injectable()
export class FiscalYearService {

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
		return this.http.get(this.baseUrl + 'seed/fiscal-years', this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	getFiscalYear(id){
		return this.http.get(this.baseUrl + 'seed/fiscal-years/'+id,this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	deleteFiscalYear(id: string){
		return this.http.delete(this.baseUrl + 'seed/fiscal-years/'+ id,this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	updateFiscalYear(id: string, data: any){
		return this.http.patch(this.baseUrl + 'seed/fiscal-years/'+id, data, this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	createFiscalYear(data: any){
		return this.http.post(this.baseUrl + 'seed/fiscal-years', data, this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	getMultipleData(id) {
        let batches = [];
		batches.push(this.http.get(this.baseUrl + 'seed/fiscal-years/'+id, this.commonService.jwt()).map((response: Response) => response.json()));
		batches.push(this.http.get(this.baseUrl + 'master/pay/comp-pay-cycles',this.commonService.jwt() ).map((res: Response) => res.json()));

        return Observable.forkJoin(batches);
    }
	
	updatePayCycles(data){
		return this.http.patch(this.baseUrl + 'master/pay/comp-pay-cycles/multi-update', data, this.commonService.jwt()).map((response: Response) => response.json());
	}
}