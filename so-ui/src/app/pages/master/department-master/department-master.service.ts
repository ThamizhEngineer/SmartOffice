import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';

@Injectable()
export class DepartmentMasterService {
    constructor(private http: Http, private commonService: CommonService) { }

    getAllDepartment(){
        return this.http.get(environment.serviceApiUrl + 'master/departments', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    
    getAllDepartmentById(id:any){
        return this.http.get(environment.serviceApiUrl + 'master/departments/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    createDepartment(department:any){
        return this.http.post(environment.serviceApiUrl+'master/departments' ,department,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    updateDepartment(department:any,id){
        return this.http.patch(environment.serviceApiUrl+'master/departments/'+id ,department,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleteDepartmentById(id:any){
        return this.http.delete(environment.serviceApiUrl +'master/departments/'+id+'/delete', this.commonService.jwt()).map((response) => response);
    }
}