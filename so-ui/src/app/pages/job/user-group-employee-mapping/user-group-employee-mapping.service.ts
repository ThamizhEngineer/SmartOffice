import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
import { UserGroupEmployeeMapping } from './vo/user-group-employee-mapping';
@Injectable()
export class  UserGroupEmployeeMappingService {

    baseUrl: string;
    cachedData: any;

    constructor(private http: Http, private commonService: CommonService) {
        this.baseUrl = environment.apiUrl;
    }

    
    private jwt() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({ 'Accept': 'application/json', 'Authorization': currentUser.token });
        return new RequestOptions({ headers: headers });
    }

   getAllUserGroupEmployeeMappings(){
    return this.http.get(environment.serviceApiUrl + 'master/user-group-emp-mappings' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
   }
   getUserGroupEmployeeMappingById(id:string){
    return this.http.get(environment.serviceApiUrl + 'master/user-group-emp-mappings/'+id , this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
   }
   getAllEmployees(){
    return this.http.get(environment.serviceApiUrl + 'master/employees/' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
   }
   getAllUserGroups(){
    return this.http.get(environment.serviceApiUrl + 'seed/user-groups' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
   }
  
   createorUpdateUserGroupEmployeeMapping(userGroupEmployeeMapping:any){
  
    return this.http.patch(environment.serviceApiUrl+'master/user-group-emp-mappings/bulk-update',userGroupEmployeeMapping, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}
createUserGroupEmployeeMapping(userGroupEmployeeMapping:UserGroupEmployeeMapping){
  
    return this.http.post(environment.serviceApiUrl+'master/user-group-emp-mappings',userGroupEmployeeMapping, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}
deleteUserGroupEmployeeMapping(id:string){
    return this.http.delete(environment.serviceApiUrl + 'master/user-group-emp-mappings/'+id,this.commonService.jwt()).map((response) => response);
}
}
