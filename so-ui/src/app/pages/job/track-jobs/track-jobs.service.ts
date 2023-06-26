import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType, RequestMethod } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
@Injectable()
export class TrackJobService {

    baseUrl: string;
    cachedData: any;

    constructor(private http: Http, private commonService: CommonService) {
        this.baseUrl = environment.apiUrl;
    }

    getData() {
        let url = 'assets/data/tickets.json';
        return this.http.get(url).map(res => res.json());
    }
	
	getTicketDetail() {
        let url = 'assets/data/ticket-detail.json';
        return this.http.get(url).map(res => res.json());
    }
	
    private jwt() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({ 'Accept': 'application/json', 'Authorization': currentUser.token });
        return new RequestOptions({ headers: headers });
    }

    getJobTracks() {
        return this.http.get(environment.serviceApiUrl + 'transaction/job-tracks/', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getJobTracksByJobId(jobId:string) {
        return this.http.get(environment.serviceApiUrl + 'transaction/job-tracks/'+jobId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    jobPtwReceived(jobId?: string,job?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/job-tracks/'+ jobId + '/received-ptw', job, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    jobSiteSurveyCompleted(jobId?: string,job?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/job-tracks/'+ jobId + '/site-survey-completed', job, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    jobMomPrepared(jobId?: string,job?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/job-tracks/'+ jobId + '/mom-draft-prepared', job, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    jobMomCompleted(jobId?: string,job?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/job-tracks/'+ jobId + '/mom-draft-approved', job, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    saveWorkspace(jobId?: string,job?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/job-tracks/'+ jobId + '/workspace-setup-save', job, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    resetTasks(jobId?: string,dummy?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/job-tracks/'+ jobId + '/reset-tasks', dummy, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    jobAssetSave(jobId?: string,job?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/job-tracks/'+ jobId + '/job-asset-save', job, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    getAllAsset(){
        return this.http.get(environment.serviceApiUrl + 'master/materials', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    deleteJobAsset(jobId?: string,jobAssetId?:string){
        return this.http.delete(environment.serviceApiUrl + 'transaction/job-tracks/'+ jobId + '/'+  jobAssetId +'/job-asset-delete',  this.commonService.jwt()).map((response: Response) => response).map(res => res);
    }

    getAllComments(jobId?: string,taskId?:string){
     
        console.log(taskId)
         return this.http.get(environment.serviceApiUrl + 'transaction/comments?jobId='+jobId+'&taskId='+taskId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getTasks(jobId?: string,taskId?:string){
     
        console.log(taskId)
         return this.http.get(environment.serviceApiUrl + 'transaction/tasks/'+taskId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    addComment(comment?:any){

        return this.http.post(environment.serviceApiUrl+'transaction/comments',comment, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 

    }

    addTask(task?:any){
        return this.http.post(environment.serviceApiUrl+'transaction/tasks/create-task',task, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    updateTask(task?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/tasks/'+task.id+'/update-task-data',task, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
  
    assignEmployeeToTask(task?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/tasks/'+task.id+'/assign/'+task.assignedToUserId,task, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    updateTaskDelayStatus(task?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/tasks/'+task.id+'/update-delay-status',task, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    updateTaskBlockStatus(task?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/tasks/'+task.id+'/update-block-status',task, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    completeTask(task?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/tasks/'+task.id+'/completed',task, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    deleteTask(task?:any){
        return this.http.delete(environment.serviceApiUrl+'transaction/tasks/delete/'+task.id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    saveSubTask(taskId?:string,task?:any){
        return this.http.post(environment.serviceApiUrl+'transaction/tasks/'+taskId+'/sub-tasks',task, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    updateSubTaskData(taskId?:string,subTask?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/tasks/'+taskId+'/sub-tasks/'+subTask.id,subTask, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    completeSubTask(taskId?:string,subTask?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/tasks/'+taskId+'/sub-tasks/'+subTask.id+'/completed',subTask, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    deleteSubTask(subTaskId?:string){
        return this.http.delete(environment.serviceApiUrl+'transaction/tasks/delete/sub-task/'+subTaskId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    updateJobStatus(jobId?:any,dummy?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/job-tracks/'+jobId+'/update-job-status',dummy, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
}
