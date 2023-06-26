import { Injectable } from '@angular/core';
import { environment } from './../../../../environments/environment';
import { Http, Response } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';
import { LeaveType } from './vo/leave-type';
import { THROW_IF_NOT_FOUND } from '@angular/core/src/di/injector';

@Injectable()
export class LeaveTypeService {

    baseUrl:string=environment.serviceApiUrl;
    xapikey:string=environment.xapikey;
    clientId:string=environment.clientId;
    constructor(private http:Http,private commonService:CommonService) { }

    getLeaveTypes(){
        return this.http.get(this.baseUrl+'seed/leave-types',this.commonService.jwt()).map((response:Response)=>response.json());
    }
    getLeaveTypeById(id:string){
        return this.http.get(this.baseUrl+'seed/leave-types/'+id,this.commonService.jwt()).map((response:Response)=>response.json());
    }
    addLeaveType(leavetype:LeaveType){
        return this.http.post(this.baseUrl+'seed/leave-types',leavetype,this.commonService.jwt()).map((response:Response)=>response.json());
    }
    updateLeaveTypeById(leavetype:LeaveType,id:string){
        return this.http.patch(this.baseUrl+'seed/leave-types/'+id,leavetype,this.commonService.jwt()).map((response:Response)=>response.json());
    }
    deleteLeaveTypeById(id:string){
        return this.http.delete(this.baseUrl+'seed/leave-types/'+id,this.commonService.jwt()).map((response:Response)=>response);
    }

}