import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";
import "rxjs/add/operator/map";

import { environment } from './../environments/environment';
import { CommonService } from '../providers/common.service';

@Injectable()
export class TasksService {

    subUrl: string = 'transaction/tasks/';

    constructor(private http: Http, private commonService: CommonService) { }

    getCurrentUser() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser && currentUser.appToken) return currentUser;
        return null;
    }

    getAllUserAllTasks(){ 
        return this.http.get(environment.serviceApiUrl + this.subUrl + 'summaries', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }

    getCurrentUserAllTasks(){ 
        return this.http.get(environment.serviceApiUrl + 'my-space/my-tasks', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }

    getTaskById(task?:any){
        return this.http.get(environment.serviceApiUrl + this.subUrl + task.id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    createTask(task?: any){
        return this.http.post(environment.serviceApiUrl + this.subUrl + 'create-task', task, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
     }

    updateDelayStatus(task?: any){ 
        return this.http.patch(environment.serviceApiUrl + this.subUrl + task.id + '/update-delay-status', task, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    updateBlockStatus(task?: any){
        return this.http.patch(environment.serviceApiUrl + this.subUrl + task.id + '/update-block-status', task, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
     }

    completeTask(task?: any){ 
        return this.http.patch(environment.serviceApiUrl + this.subUrl + task.id + '/completed', task, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    updateTaskData(task?: any){ 
        return this.http.patch(environment.serviceApiUrl + this.subUrl + task.id + '/update-task-data', task, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    assignUser(task?: any,user?: any){ 
        return this.http.patch(environment.serviceApiUrl + this.subUrl + task.id + '/assign/assignedUserId', task, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    //SubTask

    createSubTask(task? :any,subTask?: any){
        return this.http.post(environment.serviceApiUrl + this.subUrl + task.id + '/sub-tasks', subTask, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
     }

    completeSubTask(task? :any,subTask?: any){
        return this.http.patch(environment.serviceApiUrl + this.subUrl + task.id + '/sub-tasks/' + subTask.id + '/completed', subTask , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
     }

    saveSubTask(task? :any,subTask?: any){
        return this.http.patch(environment.serviceApiUrl + this.subUrl + task.id + '/sub-tasks/' + subTask.id , subTask , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
     }
}