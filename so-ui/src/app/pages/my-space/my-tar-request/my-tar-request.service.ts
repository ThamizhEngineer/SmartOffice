import { Injectable } from '@angular/core';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';

@Injectable()
export class MyTarRequestService {

    constructor(private http: Http,private commonService:CommonService) { }

    private jwt() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        currentUser.appToken
        let headers = new Headers({ 'Accept': 'application/json', 'Authorization': currentUser.token });
        return new RequestOptions({ headers: headers });
    }

    getTarById(id:string){
    
        return this.http.get(environment.serviceApiUrl + 'transaction/tars/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    
    }

    getDayRange(){
    
        return this.http.get(environment.serviceApiUrl + 'seed/day-range', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    
    }

    getJobCode(id){
    
        return this.http.get(environment.serviceApiUrl + 'transaction/job-plans/job-emp/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    
    }
    getCostCode(){
    
        return this.http.get(environment.serviceApiUrl + 'master/cost-centers', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    
    }

    getAllTarList(){
        return this.http.get(environment.serviceApiUrl + 'transaction/tars', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    
    }

    CreateMyTarReq(myTarReq:any){
        return this.http.post(environment.serviceApiUrl+'transaction/tars/create', myTarReq, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
    }

    ApplyMyTarReq(myTarReq:any){
        return this.http.post(environment.serviceApiUrl+'transaction/tars/apply', myTarReq, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
    }

    UpdateMyTarReq(id:any,action:any,myTarReq:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/tars/'+id+'/'+action, myTarReq, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
    }
    getTarApproved() {
        return this.http.get(environment.serviceApiUrl + 'transaction/tars/approved', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

}
