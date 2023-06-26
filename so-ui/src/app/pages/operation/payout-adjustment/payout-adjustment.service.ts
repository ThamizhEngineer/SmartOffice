import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response, URLSearchParams } from "@angular/http";
import "rxjs/add/operator/map";
import { environment } from './../../../../environments/environment';
import { PayoutProcess } from './../../vo/payout-process';
import { CommonService } from '../../../shared/common/common.service';

@Injectable()
export class payoutAdjustmentService {

    constructor(private http:Http,private commonService:CommonService) { }

    getPayOutAdj(){
        return this.http.get(environment.serviceApiUrl+'transaction/payOutAdj',this.commonService.jwt()).map((response:Response)=>response.json());
    }
    getPayOutAdjById(id:string){
        return this.http.get(environment.serviceApiUrl+'transaction/payOutAdj/'+id,this.commonService.jwt()).map((response:Response)=>response.json());
    }
    addPayOutAdj(payOutAdj){
        return this.http.post(environment.serviceApiUrl+'transaction/payOutAdj',payOutAdj,this.commonService.jwt()).map((response:Response)=>response.json());
    }
    updatePayOutAdjById(payOutAdj,id:string){
        return this.http.patch(environment.serviceApiUrl+'transaction/payOutAdj/'+id,payOutAdj,this.commonService.jwt()).map((response:Response)=>response.json());
    }
    deletePayOutAdjById(id:string){
        return this.http.delete(environment.serviceApiUrl+'transaction/payOutAdj/'+id,this.commonService.jwt()).map((response:Response)=>response);
    }
    getPayOutByMonthYear(month,year){
        return this.http.get(environment.serviceApiUrl + 'transaction/payOutAdj/serach?payMonth='+month+'&payYear='+year, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }
    getPayoutTypes(){		
        return this.http.get(environment.serviceApiUrl+'seed/payout-types',this.commonService.jwt()).map((response:Response)=>response.json());

        // return this.http.get(environment.serviceApiUrl + 'seed/payout-types', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
}