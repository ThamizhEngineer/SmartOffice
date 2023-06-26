import { Injectable } from '@angular/core';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx'

@Injectable()
export class VacancyOpeningService {

  
    constructor(private http: Http,private commonService:CommonService) { }
   
    getJobOpenings(){
        return this.http.get(environment.serviceApiUrl + 'transaction/vacancy-openings', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    getJobrequestById(id){
        return this.http.get(environment.serviceApiUrl + 'transaction/vacancy-requests/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    addJobrequests(jobRequest){
        return this.http.post(environment.serviceApiUrl + 'transaction/vacancy-requests/',jobRequest, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    
    updateJobrequests(jobRequest){
        return this.http.patch(environment.serviceApiUrl + 'transaction/vacancy-requests/',jobRequest, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getJobDescriptions(id) {
        return this.http.get(environment.serviceApiUrl + 'master/vacancy-descriptions/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    addWebSiteApplicant(webSiteApplicant){
        return this.http.post(environment.serviceApiUrl + 'transaction/vacancy-openings',webSiteApplicant, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    updateWebSiteApplicant(webSiteApplicant){
        return this.http.patch(environment.serviceApiUrl + 'transaction/vacancy-openings',webSiteApplicant, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    getWebSiteApplicant(email,jrId,phNo){
        let url = environment.serviceApiUrl + 'transaction/vacancy-openings/email?email='+email
        if(jrId!=null){
            url = url+'&&vcId='+jrId
        }
        if(phNo!=null){
            url = url+'&&phNo='+phNo
        }
        return this.http.get( url, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getJobDescriptionById(id) {
        return this.http.get(environment.serviceApiUrl + 'master/vacancy-descriptions/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }  
    getColleges(){
        return this.http.get(environment.serviceApiUrl + 'master/academic-colleges', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getDegrees(){
        return this.http.get(environment.serviceApiUrl + 'master/academic-degrees', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
    }
    getCourses(){
        return this.http.get(environment.serviceApiUrl + 'master/academic-courses', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
    } 
}
