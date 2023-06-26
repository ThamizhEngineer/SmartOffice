import { Injectable } from '@angular/core';
import { environment } from './../../../../environments/environment';
import { Http, Response } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';
import { TaskType } from '../vo/task-type';

@Injectable()
export class  TaskTypeService {
    baseUrl:string=environment.serviceApiUrl;
    xapikey:string=environment.xapikey;
    clientId:string=environment.clientId;

    constructor(private http:Http,private commonService:CommonService) { }


    getTaskType() {
        return this.http.get(environment.serviceApiUrl + 'master/task-types' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getTaskTypeById(id:string){
        return this.http.get(this.baseUrl+'master/task-types/' +id,this.commonService.jwt()).map((response:Response)=>response.json());
    }

    addTaskType(taskType:TaskType){
        return this.http.post(this.baseUrl+'master/task-types',taskType,this.commonService.jwt()).map((response:Response)=>response.json());
    }

    updateTaskType(taskType:TaskType,id:string){
        return this.http.patch(this.baseUrl+'master/task-types/'+id,taskType,this.commonService.jwt()).map((response:Response)=>response.json());
    }

    deleteTaskType(id:string){
        return this.http.delete(this.baseUrl+'master/task-types/'+id,this.commonService.jwt()).map((response:Response)=>response);
    }
}
