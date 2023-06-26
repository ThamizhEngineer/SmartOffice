import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
@Injectable()
export class ResourceSchedulerService {

    baseUrl: string;
    cachedData: any;
    dummy?:any;
    constructor(private http: Http, private commonService: CommonService) {
        this.baseUrl = environment.apiUrl;
    }

    getData() {
        let url = 'assets/data/jobjson.json';
        return this.http.get(url).map(res => res.json());
    }
	
	getTravelData() {
        let url = 'assets/data/exec-travel.json';
        return this.http.get(url).map(res => res.json());
    }
	
	getAccomodationData() {
        let url = 'assets/data/exec-accomodation.json';
        return this.http.get(url).map(res => res.json());
    }
	
    private jwt() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({ 'Accept': 'application/json', 'Authorization': currentUser.token });
        return new RequestOptions({ headers: headers });
    }

    getMasterData(type?: string) {
        return this.http.get(environment.serviceApiUrl + 'master/' + type, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getJobLogistics() {
        return this.http.get(environment.serviceApiUrl + 'transaction/job-logistics?jobPlanStatus=COMPLETED', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getJobLogisticsByJobId(jobId?: string) {
        return this.http.get(environment.serviceApiUrl + 'transaction/job-logistics/' + jobId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    addAndUpdateTravel(jobId?: string,jobEmpId?: string,jobTravels?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/job-logistics/'+ jobId + '/'+jobEmpId+'/emp-travel', jobTravels, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    completeTravel(jobId?: string,jobEmpId?: string){
        return this.http.patch(environment.serviceApiUrl+'transaction/job-logistics/'+ jobId + '/'+jobEmpId+'/emp-travel-complete',this.dummy, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleteTravel(jobId?: string,jobEmpId?: string,travelId?:string){
        return this.http.delete(environment.serviceApiUrl+'transaction/job-logistics/'+ jobId + '/'+jobEmpId+'/emp-travel/'+travelId, this.commonService.jwt()).map((response: Response) => response).map(res => res);
    }

    addAndupdateAccommodation(jobId?: string,jobEmpId?: string,jobAccomdations?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/job-logistics/'+ jobId + '/'+jobEmpId+'/emp-accommodation', jobAccomdations, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    completeAccommodation(jobId?: string,jobEmpId?: string){
       
        return this.http.patch(environment.serviceApiUrl+'transaction/job-logistics/'+ jobId + '/'+jobEmpId+'/emp-accommodation-complete',this.dummy, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleteAccommodation(jobId?: string,jobEmpId?: string,accomdationId?:string){
        return this.http.delete(environment.serviceApiUrl+'transaction/job-logistics/'+ jobId + '/'+jobEmpId+'/emp-accommodation/'+accomdationId, this.commonService.jwt()).map((response: Response) => response).map(res => res);
    }

    reported(jobId?: string,jobEmpId?: string,dummy?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/job-logistics/'+ jobId + '/'+jobEmpId+'/reported',this.dummy, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    departed(jobId?: string,jobEmpId?: string,dummy?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/job-logistics/'+ jobId + '/'+jobEmpId+'/departed',this.dummy, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    addEmployee(jobId?: string,job?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/job-logistics/'+ jobId + '/add-update-memployee', job, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    deleteEmployee(jobId?: string,jobEmpId?: string){
        return this.http.delete(environment.serviceApiUrl+'transaction/job-logistics/'+ jobId + '/'+jobEmpId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    // saveJob(jobId?: string,job?:any){
    //     return this.http.patch(environment.serviceApiUrl+'transaction/job-logistics/'+ jobId + '/job-details', job, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    // }

    addProfileFinder(profileFinderJob?:any){
        return this.http.post(environment.serviceApiUrl+'transaction/profile-finder', profileFinderJob, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    startProfileFinder(profileFinderJob?:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/profile-finder/'+profileFinderJob.id+'/start', profileFinderJob, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getAllDepartments(){
        return this.http.get(environment.serviceApiUrl + 'master/departments', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getProfileFinder(id?:any){
        return this.http.get(environment.serviceApiUrl + 'transaction/profile-finder/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

}
