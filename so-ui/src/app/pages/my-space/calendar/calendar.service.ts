import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import{CommonService} from '../../../shared/common/common.service';

@Injectable()
export class CalendarService {

    baseUrl: string = environment.serviceApiUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;

    constructor(private http: Http, private commonService:CommonService) {
    }

   
	getUser(){
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		if (currentUser) return currentUser;
	}
	
	getAttendances(id){		
        return this.http.get(this.baseUrl + 'transaction/attendance/direct?employeeId='+id, this.commonService.jwt()).map((response: Response) => response.json());
    }
	
	getMultipleData(id){
		let batches = [];
		batches.push( this.http.get(this.baseUrl + 'transaction/attendance/direct?employeeId='+id, this.commonService.jwt()).map((response: Response) => response.json()) );
		batches.push( this.http.get(this.baseUrl + 'seed/holidays', this.commonService.jwt() ).map((res: Response) => res.json()) );

        return Observable.forkJoin(batches);
	}
}
