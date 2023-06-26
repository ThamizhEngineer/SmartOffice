import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
@Injectable()
export class JobFeedbackService {

    baseUrl: string;
    cachedData: any;

    constructor(private http: Http, private commonService: CommonService) {
        this.baseUrl = environment.apiUrl;
    }

    getData() {
        let url = 'assets/data/jobs.json';
        return this.http.get(url).map(res => res.json());
    }
    
    getAllFeedbacks() {
       
            return this.http.get(environment.serviceApiUrl + 'transaction/job-feedbacks?jobTrackStatus=COMPLETED' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    
    }
    getAllFeedbackByJobId(jobId:string) {
       
        return this.http.get(environment.serviceApiUrl + 'transaction/job-feedbacks/'+jobId , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());

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

    updateJob( job?: any) {
        return this.http.patch(environment.serviceApiUrl+'transaction/job-feedbacks', job, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

}
