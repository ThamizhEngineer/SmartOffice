import { Injectable } from '@angular/core';

import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';

@Injectable()
export class RecruitmentService {
    constructor(private http: Http,
        private commonService: CommonService) { }

        getColleges(){
            return this.http.get(environment.serviceApiUrl + 'master/academic-colleges', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
        }
        getDegrees(){
            return this.http.get(environment.serviceApiUrl + 'master/academic-degrees', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
        }
        getCourses(){
            return this.http.get(environment.serviceApiUrl + 'master/academic-courses', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
        }
        postColleges(data){
            return this.http.post(environment.serviceApiUrl +'master/academic-colleges/multiadd',data, this.commonService.jwt()).map((response: Response) => response); 
        }
        postDegrees(data){
            return this.http.post(environment.serviceApiUrl +'master/academic-degrees/multiadd',data, this.commonService.jwt()).map((response: Response) => response); 
        }
        postCourses(data){
            return this.http.post(environment.serviceApiUrl +'master/academic-courses/multiadd',data, this.commonService.jwt()).map((response: Response) => response); 
        }
        deleteCollege(id){
            return this.http.delete(environment.serviceApiUrl +'master/academic-colleges/'+id+'/delete', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
        }
        deleteDegree(id){
            return this.http.delete(environment.serviceApiUrl +'master/academic-degrees/'+id+'/delete', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
        }
        deleteCourse(id){
            return this.http.delete(environment.serviceApiUrl +'master/academic-courses/'+id+'/delete', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
        }
        
}