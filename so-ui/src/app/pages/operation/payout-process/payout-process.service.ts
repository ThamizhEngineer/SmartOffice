import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response, URLSearchParams } from "@angular/http";
import "rxjs/add/operator/map";
import { environment } from './../../../../environments/environment';
import { PayoutProcess } from './../../vo/payout-process';
import { CommonService } from '../../../shared/common/common.service';

@Injectable()
export class PayoutProcessService {

   

    constructor(private http: Http,private commonService: CommonService) {
    }


   
    getPayoutProcessById(_id: string) {
        return this.http.get(environment.serviceApiUrl+'transaction/process-payouts/' + _id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getPayoutProcess() {
        return this.http.get(environment.serviceApiUrl+'transaction/process-payouts',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    
    processsalary(payoutProcess:PayoutProcess) {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    let body = JSON.stringify(payoutProcess);
    let url = environment.serviceApiUrl+'transaction/process-payouts/create';
    return this.http.patch(url, body,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }

    processSalaries(payoutProcess:PayoutProcess) {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(payoutProcess);
        let url = environment.serviceApiUrl+'transaction/process-payouts/process-salaries';
        return this.http.patch(url, body,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
        }
}