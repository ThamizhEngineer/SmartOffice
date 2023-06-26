import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';

import { Project } from './../vo/project';

@Injectable()
export class ProjectService {
    constructor(private http: Http,private commonService: CommonService) {
    }
    getProjects() {
        return this.http.get(environment.serviceApiUrl+'transaction/projects' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getProjectById(id?: any){
        return this.http.get(environment.serviceApiUrl+'transaction/projects/'+ id , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    createProject(po:Project){
        return this.http.post(environment.serviceApiUrl+'transaction/projects/', po, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    updateProject(project:Project) {
        return this.http.patch(environment.serviceApiUrl+'transaction/projects/'+project.id, project, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleteProject( id?: any) {
        return this.http.delete(environment.serviceApiUrl + 'transaction/projects/'+id,this.commonService.jwt()).map((response) => response);
    }
    getCountries(){
        return this.http.get(environment.serviceApiUrl+'seed/countries',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getPartners(){
        return this.http.get(environment.serviceApiUrl+'master/clients',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getPartnerById(id?: any){
        return this.http.get(environment.serviceApiUrl+'master/clients/'+id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
   
}