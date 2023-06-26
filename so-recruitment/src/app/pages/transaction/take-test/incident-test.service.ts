import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';


@Injectable()
export class IncidentTestService {
  

    constructor(private http: Http,private commonService:CommonService ) {
       
    }
    private jwt() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        currentUser.appToken
        let headers = new Headers({ 'Accept': 'application/json', 'Authorization': currentUser.token });
        return new RequestOptions({ headers: headers });
    }


getFetchAllTest(){
    return this.http.get(environment.serviceApiUrl + 'transaction/incident/test/fetchAllTest', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
getTestById(id){
    return this.http.get(environment.serviceApiUrl + 'transaction/incident/test/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
getStartTest(id:any,dummy:any){
    return this.http.patch(environment.serviceApiUrl + 'transaction/incident/test/'+id+'/start-test',dummy, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
testType(id:any,type,dummy:any){    
    return this.http.patch(environment.serviceApiUrl + 'transaction/incident/test/'+id+'/'+type,dummy, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
startTest(id:any,dummy:any){    
    return this.http.patch(environment.serviceApiUrl + 'transaction/incident/test/'+id+'/start-test',dummy, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
continueTest(id:any,dummy:any){    
    return this.http.patch(environment.serviceApiUrl + 'transaction/incident/test/'+id+'/continue-test',dummy, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
nextQuestion(id:any,questionOrder:any){    
    return this.http.get(environment.serviceApiUrl + 'transaction/incident/test/'+id+'/'+questionOrder+'/next-question', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
previousQuestion(id:any,questionOrder:any){    
    return this.http.get(environment.serviceApiUrl + 'transaction/incident/test/'+id+'/'+questionOrder+'/previous-question', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
answer(id:any,questionOrder:any,userSelected:any,dummy:any){    
    return this.http.patch(environment.serviceApiUrl + 'transaction/incident/test/'+id+'/'+questionOrder+'/'+userSelected+'/answer',dummy, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
submitTest(id:any,dummy:any){    
    return this.http.patch(environment.serviceApiUrl + 'transaction/incident/test/'+id+'/submit-test',dummy, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
calculateRemainingTime(inputTimeString){
    return this.http.get(environment.serviceApiUrl + 'transaction/incident/test/'+inputTimeString+'/remainingTime', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
heartBeat(id){
    return this.http.get(environment.serviceApiUrl + 'transaction/incident/test/'+id+'/heart-beat', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
fetchByApplicantId(applicantId){
    return this.http.get(environment.serviceApiUrl + 'transaction/incident/test/'+applicantId+'/fetchByApplicantId', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}


}
