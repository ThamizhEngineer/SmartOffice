import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';
import { QuestionBank } from './vo/question-bank';

@Injectable()
export class QuestionsService {

    baseUrl: string;
    cachedData: any;

    constructor(private http: Http,private commonService:CommonService ) {
        this.baseUrl = environment.apiUrl;
    }

    getQuestions() {
        let url = 'assets/data/questions.json';
        return this.http.get(url).map(res => res.json());
      
    }
    getQuestionBank(){
        return this.http.get(environment.serviceApiUrl + 'test/question-banks', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getQuestionBankById(id:any){
        return this.http.get(environment.serviceApiUrl + 'test/question-banks/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    addQuestionBank(questionBank:QuestionBank){
        return this.http.post(environment.serviceApiUrl+'test/question-banks/', questionBank, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
addQuestionBankOption(questionBank:QuestionBank,id:any){
    return this.http.patch(environment.serviceApiUrl+'test/question-banks/'+id+'/lines', questionBank, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}

    updateQuestionBank(questionBank:QuestionBank,id:any){
        return this.http.patch(environment.serviceApiUrl+'test/question-banks/'+id, questionBank, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    // deleteQuestionBank(id:any){
    //     return this.http.delete(environment.serviceApiUrl + 'test/question-banks/'+id+'/delete',this.commonService.jwt()).map((response) => response);
    // }
		
    private jwt() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({ 'Accept': 'application/json', 'Authorization': currentUser.token });
        return new RequestOptions({ headers: headers });
    }
}
