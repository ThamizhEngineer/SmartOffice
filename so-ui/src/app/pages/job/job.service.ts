import { Injectable, EventEmitter } from '@angular/core';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../environments/environment';
import { CommonService } from '../../shared/common/common.service';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class JobService {

    currentMessageEvent = new EventEmitter();

    constructor(private http: Http, private commonService: CommonService) {
    }

    changeMessage(message) {
        console.log(message)
        this.currentMessageEvent.emit(message)
    }

    getSiteLocations(){
        return this.http.get(environment.serviceApiUrl + 'master/site-location', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    
    getMasterData(type?: string) {
        return this.http.get(environment.serviceApiUrl + 'master/' + type, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getMasterDataById(type?: string, id?: any) {
        return this.http.get(environment.serviceApiUrl+'master/'+ type + '/' +id , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getProducts(){
        return this.http.get(environment.serviceApiUrl + 'master/products?materialCategory=product', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    
    getJobs(type?: string) {
        return this.http.get(environment.serviceApiUrl + 'master/' + type, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getJobById(type?: string, id?: any) {
        return this.http.get(environment.serviceApiUrl+'master/'+ type + '/' +id , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    createJob(type?: string, job?: any) {
        return this.http.post(environment.serviceApiUrl+'master/'+ type + '/', job, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    updateJob(type?: string, job?: any) {
        return this.http.patch(environment.serviceApiUrl+'master/'+ type + '/'+job.id, job, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    deleteJob(type?: string, id?: any) {
        return this.http.delete(environment.serviceApiUrl + 'master/'+ type + '/'+id, this.commonService.jwt()).map((response) => response);
    }

    getJobPlans(){
        return this.http.get(environment.serviceApiUrl + 'transaction/job-plans', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    advSearchJobPlans(partnerId,jobCode,jobName,jobTypeId){
        let url = 'transaction/job-plans?partnerId='+partnerId+'&jobCode='+jobCode+'&jobName='+jobName+'&jobTypeId='+jobTypeId
        return this.http.get(environment.serviceApiUrl + url, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }

    deleteJobPlan(id?: any) {
        return this.http.delete(environment.serviceApiUrl + 'transaction/job-plans/'+ id, this.commonService.jwt()).map((response) => response);
    }

    getSaleOrderByPartnerId(id?: any){
        return this.http.get(environment.serviceApiUrl+'transaction/sale-orders?partnerId=' + id , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getSaleOrderById(id?: any){
        return this.http.get(environment.serviceApiUrl+'transaction/sale-orders/' + id , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getSkillFromProdId(id){
        return this.http.get(environment.serviceApiUrl + 'master/materials/' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    // getProjectsByPartnerId(id?: any){
    //     return this.http.get(environment.serviceApiUrl+'transaction/projects?partnerId=' + id , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    // }

    getProjects(){
        return this.http.get(environment.serviceApiUrl+'transaction/projects'  , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getAttendanceShift(){
        return this.http.get(environment.serviceApiUrl+'master/shift'  , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    createJobPlan(job?: any) {
        return this.http.post(environment.serviceApiUrl+'transaction/job-plans/start-job-plan', job, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    updateJobPlan(job?: any, updateType?: any) {
        return this.http.patch(environment.serviceApiUrl+'transaction/job-plans/'+job.id+'/' + updateType, job, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    confirmJobPlan(job?: any, updateType?: any) {
        return this.http.put(environment.serviceApiUrl+'transaction/job-plans/'+job.id+'/' + updateType, job, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    saveJobPlan(job?: any) {
        return this.http.patch(environment.serviceApiUrl+'transaction/job-plans/'+job.id+'/save-job' , job, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
	
	getJobPlanById(id?: any){
		return this.http.get(environment.serviceApiUrl + 'transaction/job-plans/'+ id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    deleteJobPlanLines(jobId?: any,lineId?: any,lineType?:any){
        return this.http.delete(environment.serviceApiUrl + 'transaction/job-plans/'+ jobId+'/'+lineId+'/delete-job-plan-lines?line-type='+lineType, this.commonService.jwt()).map((response: Response) => response).map(res => res);
    }
    

    getJobStatus(type?: string) {
        return this.http.get(environment.serviceApiUrl + 'report/' + type, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }


    getJobStatusByJobId(id?: any) {
        return this.http.get(environment.serviceApiUrl + 'report/job-statuses/' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getJobReports() {
        return this.http.get(environment.serviceApiUrl + 'transaction/job-reports?jobPlanStatus=COMPLETED', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getJobReportByJobId(jobId?: string) {
        return this.http.get(environment.serviceApiUrl + 'transaction/job-reports/' + jobId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    updateJobReport(job?: any) {
        return this.http.patch(environment.serviceApiUrl+'transaction/job-reports/', job, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getMapLocation() {
        return this.http.get(environment.serviceApiUrl + 'map-locations/all' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getDocument(docId){
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({'authorization': currentUser.appToken});	
        let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
        let url =environment.documentApiUrl+'dm/'+docId;
          return this.http.get(url, options).catch((err: Response) => Observable.throw(err));
    }
    getAllDocumentType(){
        return this.http.get( environment.serviceApiUrl +'dm/document_types' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    deleteJobDocs(jobId?:string,jobDocId?:string){
        return this.http.delete(environment.serviceApiUrl + 'transaction/job-reports/'+ jobId+'/'+jobDocId+'/job-docs-delete', this.commonService.jwt()).map((response: Response) => response).map(res => res);
    }

    // getTaskType() {
    //     return this.http.get(environment.serviceApiUrl + 'master/task-types' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    // }

    getTaskTypeById(taskType?: string, id?: any) {
        return this.http.get(environment.serviceApiUrl+'master/task-types'+ taskType  +id , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    createTaskType(taskType?: string) {
        return this.http.post(environment.serviceApiUrl+'master/task-types'+ taskType , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    updateTaskType(taskType?: string, id?:string) {
        return this.http.patch(environment.serviceApiUrl+'master/task-types'+ taskType +id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    deleteTaskType(id?: any) {
        return this.http.delete(environment.serviceApiUrl + 'master/task-types' +id, this.commonService.jwt()).map((response) => response);
    } 
}
