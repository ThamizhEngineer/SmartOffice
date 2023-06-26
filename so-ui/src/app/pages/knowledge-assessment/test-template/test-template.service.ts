import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';

@Injectable()
export class TestTemplateService {

    constructor(private http: Http, private commonService: CommonService) {
    }
    private jwt() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({ 'Accept': 'application/json', 'Authorization': currentUser.appToken });
        return new RequestOptions({ headers: headers });
    }
   
    getAllTestTemplate(){
        return this.http.get(environment.serviceApiUrl + 'master/testtemplate', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getAllTestTemplateById(id:any){
        return this.http.get(environment.serviceApiUrl + 'master/testtemplate/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    createTemplate(testtemplate:any){
        return this.http.post(environment.serviceApiUrl+'master/testtemplate' ,testtemplate,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    updateTemplate(testtemplate:any,id){
        return this.http.patch(environment.serviceApiUrl+'master/testtemplate/'+id ,testtemplate,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleteTemplateById(id:any){
        return this.http.delete(environment.serviceApiUrl +'master/testtemplate/'+id, this.commonService.jwt()).map((response) => response);
    }
    getAllTestCategory(){
        return this.http.get(environment.serviceApiUrl + 'master/test-category', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getTestTemplates() {
        return this.http.get(environment.serviceApiUrl + 'master/testtemplate', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getByCategoryIdAndLevel(id:any,level:any) {
        return this.http.get(environment.serviceApiUrl + 'master/questions/'+id+'/'+level, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
       
    
}
