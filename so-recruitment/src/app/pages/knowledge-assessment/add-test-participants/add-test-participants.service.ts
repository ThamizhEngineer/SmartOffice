import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';
import { Applicant } from '../../my-space/vo/applicant';
import { HttpParams } from '@angular/common/http';
import { TestPreparation } from './vo/TestPreparation';
import { TestQuestion, TestQuestionOption } from '../create-test/vo/Test';

@Injectable()
export class AddTestParticipantsService {
  

    constructor(private http: Http,private commonService:CommonService ) {
       
    }
    private jwt() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        currentUser.appToken
        let headers = new Headers({ 'Accept': 'application/json', 'Authorization': currentUser.token });
        return new RequestOptions({ headers: headers });
    }
    getAllTestPreprations(){
        return this.http.get(environment.serviceApiUrl + 'test/test-preparations', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
getAllTestPreparationById(id:any){
    return this.http.get(environment.serviceApiUrl + 'test/test-preparations/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
   
addTestParticipants(testPreparation:TestPreparation){
    return this.http.post(environment.serviceApiUrl+'test/test-preparations/', testPreparation, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
}
updateTestParticipants(testPreparation:TestPreparation,id:any){
    return this.http.patch(environment.serviceApiUrl+'test/test-preparations/'+id, testPreparation, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
}
createTest(id:any,dummy:any){
 
    
   return this.http.post(environment.serviceApiUrl+'test/tests/create?testPrepId='+id, dummy,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
}
takeTest(id:any){
    return this.http.get(environment.serviceApiUrl + 'test/tests/'+id+'/take-test', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
   getMyTest(participantId:any){
    return this.http.get(environment.serviceApiUrl + 'test/tests/my-test?participantId='+participantId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
   }
   getTestById(id:any){
    return this.http.get(environment.serviceApiUrl + 'test/tests/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
   }
   evalTest(testQuestion,id:any){
       return this.http.patch(environment.serviceApiUrl + 'test/tests/'+id+'/eval-test',testQuestion, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
   }
		
   
}
