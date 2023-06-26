import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';
import { HttpParams } from '@angular/common/http';

@Injectable()
export class TestResulService {
  

    constructor(private http: Http,private commonService:CommonService ) {
       
    }
    private jwt() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({ 'Accept': 'application/json', 'Authorization': currentUser.token });
        return new RequestOptions({ headers: headers });
    }
    getAllTestResulService(){
        return this.http.get(environment.serviceApiUrl + 'test/tests/result', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    
    evalTest(id:any){
 
    
        return this.http.get(environment.serviceApiUrl+'test/tests/'+id+'/ack',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
     }

   
		
   
}
