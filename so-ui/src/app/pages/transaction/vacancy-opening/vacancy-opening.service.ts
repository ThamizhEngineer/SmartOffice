import { Injectable } from '@angular/core';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';
import { Http, Response } from '@angular/http';
import { VacancyRequest } from '../../vo/vacancy-request';
import { Observable } from 'rxjs/Rx'

@Injectable()
export class VacancyOpeningService {

  
    constructor(private http: Http,private commonService:CommonService) { }
   
    getVacancyOpenings(){
        return this.http.get(environment.serviceApiUrl + 'transaction/vacancy-openings', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    getVacancyrequestById(id){
        return this.http.get(environment.serviceApiUrl + 'transaction/vacancy-requests/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    addVacancyrequests(VacancyRequest){
        return this.http.post(environment.serviceApiUrl + 'transaction/vacancy-requests/',VacancyRequest, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    
    updateVacancyrequests(VacancyRequest:VacancyRequest){
        return this.http.patch(environment.serviceApiUrl + 'transaction/vacancy-requests/',VacancyRequest, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getVacancyDescriptions() {
        return this.http.get(environment.serviceApiUrl + 'master/vacancy-descriptions/', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    addWebSiteApplicant(webSiteApplicant){
        return this.http.post(environment.serviceApiUrl + 'transaction/web-site-applicants',webSiteApplicant, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getVacancyDescriptionById(id) {
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
    getIncidents(){
        return this.http.get(environment.serviceApiUrl + 'master/academic-colleges', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

}
