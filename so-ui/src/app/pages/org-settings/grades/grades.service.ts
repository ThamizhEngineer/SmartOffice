import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response, URLSearchParams } from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { environment } from './../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
@Injectable()
export class GradesService {

    baseUrl: string = environment.serviceApiUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;

    constructor(private http: Http,private commonService:CommonService ) {
    }

    private jwt() {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json' });
		return new RequestOptions({ headers: headers });
    }
	
	getGrades(){
		return this.http.get(this.baseUrl + 'seed/grades',this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	getDesignations(){
		return this.http.get(this.baseUrl + 'seed/designations', this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	getMultipleData() {
        let batches = [];
		batches.push(this.http.get(this.baseUrl + 'seed/grades', this.commonService.jwt() ).map((res: Response) => res.json()));
		batches.push(this.http.get(this.baseUrl + 'seed/designations', this.commonService.jwt() ).map((res: Response) => res.json()));

        return Observable.forkJoin(batches);
    }
	
	deleteGrade(id: string){
		return this.http.delete(this.baseUrl + 'seed/grades/'+ id, this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	updateGrade(id: string, data: any){
		return this.http.patch(this.baseUrl + 'seed/grades/'+id, data, this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	updateGradeOrder(data){
		return this.http.patch(this.baseUrl + 'seed/grades/bulk-update', data, this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	createGrade(data: any){
		return this.http.post(this.baseUrl + 'seed/grades', data, this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	deleteDesignation(id: string){
		return this.http.delete(this.baseUrl + 'seed/designations/'+ id, this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	updateDesignation(id: string, data: any){
		return this.http.patch(this.baseUrl + 'seed/designations/'+id, data, this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	createDesignation(data: any){
		return this.http.post(this.baseUrl + 'seed/designations', data, this.commonService.jwt()).map((response: Response) => response.json());
	}
}