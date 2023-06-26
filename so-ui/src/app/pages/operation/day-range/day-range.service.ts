import { Injectable } from '@angular/core';
import { environment } from './../../../../environments/environment';
import { Http, Response } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';
import {DayRange} from './vo/day-range';

@Injectable()
export class  DayRangeService {
    baseUrl:string=environment.serviceApiUrl;
    xapikey:string=environment.xapikey;
    clientId:string=environment.clientId;

    constructor(private http:Http,private commonService:CommonService) { }

    getDayRanges(){
        return this.http.get(this.baseUrl+'seed/day-range',this.commonService.jwt()).map((response:Response)=>response.json());
    }
    getDayRangeById(id:string){
        return this.http.get(this.baseUrl+'seed/day-range/'+id,this.commonService.jwt()).map((response:Response)=>response.json());
    }

    addDayRange(dayRange:DayRange){
        return this.http.post(this.baseUrl+'seed/day-range',dayRange,this.commonService.jwt()).map((response:Response)=>response.json());
    }
    deleteDayRange(id:string){
        return this.http.delete(this.baseUrl+'seed/day-range/'+id+'/delete',this.commonService.jwt()).map((response:Response)=>response);
    }
    addOrUpdateLines(dayrange:DayRange,id:string){
        return this.http.patch(this.baseUrl+'seed/day-range/'+id+'/update',dayrange,this.commonService.jwt()).map((response:Response)=>response.json());
    }
}