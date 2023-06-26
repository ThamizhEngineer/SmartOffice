import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';
import { environment } from './../../../../environments/environment';
import { TempJob } from './vo/temp-job';
@Injectable()
export class TempJobService {
    baseUrl:string=environment.serviceApiUrl;
    xapikey:string=environment.xapikey;
    clientId:string=environment.clientId;

    constructor(private http:Http,private commonService:CommonService) { }
    getTempJob(){
        return this.http.get(this.baseUrl+'temp/temp-job',this.commonService.jwt()).map((response:Response)=>response.json());
    }
    getTempJobById(id:any){
        return this.http.get(this.baseUrl+'temp/temp-job/job/'+id,this.commonService.jwt()).map((response:Response)=>response.json());
    }
    getTempJobByEmpId(employeeId:string){
        return this.http.get(this.baseUrl+'temp/temp-job/emp/'+employeeId,this.commonService.jwt()).map((response:Response)=>response.json());
    }
    addTempJob(tempjob:TempJob){
        return this.http.post(this.baseUrl+'temp/temp-job',tempjob,this.commonService.jwt()).map((response:Response)=>response.json());
    }
    deleteTempJob(id:any){
        return this.http.delete(this.baseUrl+'temp/temp-job/'+id+'/delete',this.commonService.jwt()).map((response:Response)=>response);
    }
    updateTempJob(id:any,tempjob:TempJob){
        return this.http.patch(this.baseUrl+'temp/temp-job/'+id+'/update',tempjob,this.commonService.jwt()).map((response:Response)=>response.json());
    }
    getOffices(){
        return this.http.get(this.baseUrl + 'master/offices', this.commonService.jwt()).map((response: Response) => response.json());    
    }
    getEmployees() {
        return this.http.get(environment.serviceApiUrl+'master/employees/', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
}