import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response, URLSearchParams } from "@angular/http";
import "rxjs/add/operator/map";
import { environment } from './../../../../environments/environment';
import { PayoutProcess } from './../../vo/payout-process';

@Injectable()
export class ProcessSalaryService {

   

    constructor(private http: Http) {
    }


   
    getPayoutProcessById(_id: string) {
        return this.http.get(environment.serviceApiUrl+'transaction/process-payouts/' + _id).map(res => res.json());
    }

    getPayoutProcess() {
        return this.http.get(environment.serviceApiUrl+'transaction/process-payouts').map(res => res.json());
    }

    searchPayoutProcess(employeeId:string,payDt:Date){

        var url = environment.serviceApiUrl+'transaction/pay/memployee-payouts';

        if( employeeId != "" || payDt !=undefined ) {
            url = url + "?dummy=1";  
            
			if(employeeId != "" && employeeId != undefined){
                url = url + "&employeeId="+employeeId ;
            }
			if( payDt != undefined){
                url = url + "&payDt="+payDt ;
            }
        }
      
        return this.http.get(url).map((res: Response) => res.json());
    }

   

}