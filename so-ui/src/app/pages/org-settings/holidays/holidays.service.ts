import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response, URLSearchParams } from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { environment } from './../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
@Injectable()
export class HolidaysService {

    baseUrl: string = environment.serviceApiUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;

    constructor(private http: Http,private commonService:CommonService ) {
    }

    private jwt() {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json' });
		return new RequestOptions({ headers: headers });
    }
	
	getHolidays(){
		return this.http.get(this.baseUrl + 'seed/holidays', this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	deleteHoliday(id: string){
		return this.http.delete(this.baseUrl + 'seed/holidays/'+ id, this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	updateHoliday(id: string, data: any){
		return this.http.patch(this.baseUrl + 'seed/holidays/'+id, data, this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	createHoliday(data: any){
		return this.http.post(this.baseUrl + 'seed/holidays', data, this.commonService.jwt()).map((response: Response) => response.json());
	}
	
}