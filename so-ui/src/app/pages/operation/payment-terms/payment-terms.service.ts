import { Injectable } from '@angular/core';
import { environment } from './../../../../environments/environment';
import { Http, Response } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';
import { PaymentTerms } from './vo/payment-terms';

@Injectable()
export class  PaymentTermsService {
    baseUrl:string=environment.serviceApiUrl;
    xapikey:string=environment.xapikey;
    clientId:string=environment.clientId;

    constructor(private http:Http,private commonService:CommonService) { }


    getPaymentTerms(){
        return this.http.get(this.baseUrl+'seed/payment-terms',this.commonService.jwt()).map((response:Response)=>response.json());
    }

    getPaymentTermsById(id:string){
        return this.http.get(this.baseUrl+'seed/payment-terms/'+id,this.commonService.jwt()).map((response:Response)=>response.json());
    }

    addPaymentTerms(paymentTerms:PaymentTerms){
        return this.http.post(this.baseUrl+'seed/payment-terms',paymentTerms,this.commonService.jwt()).map((response:Response)=>response.json());
    }

    updatePaymentTerms(paymentTerms:PaymentTerms,id:string){
        return this.http.patch(this.baseUrl+'seed/payment-terms/'+id,paymentTerms,this.commonService.jwt()).map((response:Response)=>response.json());
    }

    deletePaymentTerms(id:string){
        return this.http.delete(this.baseUrl+'seed/payment-terms/'+id,this.commonService.jwt()).map((response:Response)=>response);
    }
}
