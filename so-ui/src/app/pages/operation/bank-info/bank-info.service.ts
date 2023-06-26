import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';
import { environment } from './../../../../environments/environment';
import { BankInfo } from './vo/bank-info';

@Injectable()
export class BankInfoService {
    baseUrl:string=environment.serviceApiUrl;
    xapikey:string=environment.xapikey;
    clientId:string=environment.clientId;

    constructor(private http:Http,private commonService:CommonService) { }
    getBankInfo(){
        return this.http.get(this.baseUrl+'seed/bankinfo',this.commonService.jwt()).map((response:Response)=>response.json());
    }
    getBankInfoById(id:string){
        return this.http.get(this.baseUrl+'seed/bankinfo/'+id,this.commonService.jwt()).map((response:Response)=>response.json());
    }
    addBankInfo(bankinfo:BankInfo){
        return this.http.post(this.baseUrl+'seed/bankinfo',bankinfo,this.commonService.jwt()).map((response:Response)=>response.json());
    }
    deleteBankInfo(id:string){
        return this.http.delete(this.baseUrl+'seed/bankinfo/'+id+'/delete',this.commonService.jwt()).map((response:Response)=>response);
    }
    updateBankInfo(bankinfo:BankInfo,id:string){
        return this.http.patch(this.baseUrl+'seed/bankinfo/'+id+'/update',bankinfo,this.commonService.jwt()).map((response:Response)=>response.json());
    }

}