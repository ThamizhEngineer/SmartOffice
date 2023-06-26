import { Injectable } from '@angular/core';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';

@Injectable()
export class ManagerSwapService {

    constructor(private http: Http,private commonService:CommonService) { }

    private jwt() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        currentUser.appToken
        let headers = new Headers({ 'Accept': 'application/json', 'Authorization': currentUser.token });
        return new RequestOptions({ headers: headers });
    }

    getManager(managerId:string){
        
        let url =environment.serviceApiUrl + 'master/employees/manager-swap/fetch-team'
            if (managerId != null) {
                url = url + "?n1EmpId=" + managerId;
            }else{
                url = url + "?n1EmpId=";
            }
        console.log(url)
        return this.http.get(url, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    
    }

    getAllManager(){
        
        let url =environment.serviceApiUrl + 'master/employees'
        return this.http.get(url, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    
    }

    batchUpdate(managerSwap:any){
        console.log("output"+managerSwap)
        return this.http.patch(environment.serviceApiUrl+'master/employees/batch-update', managerSwap, this.commonService.jwt()).map((response: Response) => response);    
    }

}
