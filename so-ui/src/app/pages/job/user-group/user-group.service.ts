import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
import { userGroup } from '../user-group/vo/userGroup';
@Injectable()
export class UserGroupMasterService {

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

    getAllUserGroup(){
        return this.http.get(environment.serviceApiUrl + 'seed/user-groups' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
       }

       getUserGroupById(id:string){
        return this.http.get(environment.serviceApiUrl + 'seed/user-groups/'+id , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
       }

       createUserGroup(usergroup:userGroup){
        return this.http.post(environment.serviceApiUrl + 'seed/user-groups/' ,usergroup, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
       }

       createorUpdateUserGroup(usergroup?:any){
        return this.http.patch(environment.serviceApiUrl+'seed/user-groups/bulk-update/',usergroup, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
       }

       deleteUserGroup(id?:string){
        return this.http.delete(environment.serviceApiUrl + 'seed/user-groups/'+id , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
       }

}