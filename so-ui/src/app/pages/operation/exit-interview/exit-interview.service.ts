import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
import{ExitInterview} from '../exit-interview/vo/exit-interview';


@Injectable()
export class ExitInterviewService {

    constructor(private http: Http, private commonService: CommonService) {
    }
    getAlExitInterview(){
        return this.http.get(environment.serviceApiUrl+'transaction/exit-interviews', this.commonService.jwt()).map(res => res.json());
    }
    getExitInterviewById(id:any){
        return this.http.get(environment.serviceApiUrl+'transaction/exit-interviews/'+id, this.commonService.jwt()).map(res => res.json());
    }
    addExitInterview(exitInterview:ExitInterview){
        return this.http.post(environment.serviceApiUrl+'transaction/exit-interviews/', exitInterview, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    updateExitInterview(id:any,action:string,exitInterview:ExitInterview){
        return this.http.patch(environment.serviceApiUrl+'transaction/exit-interviews/'+id+'/'+action, exitInterview, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }
    completeExitInterview(id:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/exit-interviews/'+id+'/complete-exit-interview',  this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }
    deleteExitInterview(id:any){
        return this.http.delete(environment.serviceApiUrl + 'transaction/exit-interviews/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res);
    }
    getEmployees(id) {
        return this.http.get(environment.serviceApiUrl+'master/employees/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    exitInterviewActionView(action){
        return this.http.get(environment.serviceApiUrl+'transaction/exit-interviews/view/'+action, this.commonService.jwt()).map(res => res.json());   
    }

    hrUpdateExitInterview(id,exitInterview){
        return this.http.patch(environment.serviceApiUrl+'transaction/exit-interviews/update/'+id,exitInterview, this.commonService.jwt()).map(res => res.json());      
    }
    

}