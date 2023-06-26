import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';

@Injectable()
export class TestCategoryService {

    constructor(private http: Http,
         private commonService: CommonService) { }

    getTestCategorys() {
        return this.http.get(environment.serviceApiUrl + 'master/test-category', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getTestCategoryById(id) {
        return this.http.get(environment.serviceApiUrl + 'master/test-category/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    addTestCategory(testCategory) {
        return this.http.post(environment.serviceApiUrl + 'master/test-category',testCategory, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    updateTestCategory(id,testCategory) {
        return this.http.patch(environment.serviceApiUrl + 'master/test-category/'+id,testCategory, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    deleteTestCategory(id){
        return this.http.delete(environment.serviceApiUrl + 'master/test-category/'+id,this.commonService.jwt()).map((response) => response);
    }

    //Questions Service 

    getQuestions(categoryId,level) {
        return this.http.get(environment.serviceApiUrl + 'master/questions/get-all?categoryId='+categoryId+"&&level="+level, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getQuestionsByCategoryId(categoryId) {
        return this.http.get(environment.serviceApiUrl + 'master/questions/test/'+categoryId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    addAndUpdateQuestions(questions) {
        return this.http.post(environment.serviceApiUrl + 'master/questions/bulk-update',questions, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    deleteQuestionById(id){
        return this.http.delete(environment.serviceApiUrl+ 'master/questions/'+id,this.commonService.jwt()).map((response: Response) => response).map(res => res);
    }
    deleteQuestionByCategoryId(id){
        return this.http.delete(environment.serviceApiUrl+ 'master/questions/bulk-delete/'+id,this.commonService.jwt()).map((response: Response) => response).map(res => res);
    }

}