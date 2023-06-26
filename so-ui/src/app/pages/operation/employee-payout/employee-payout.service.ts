import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response, URLSearchParams,ResponseContentType } from "@angular/http";
import "rxjs/add/operator/map";
import { Observable } from 'rxjs/Rx'
import { environment } from './../../../../environments/environment';
import { EmployeePayout } from './../../vo/employee-payout';
import { CommonService } from '../../../shared/common/common.service';

@Injectable()
export class EmployeePayoutService {

	cachedData:any;
    constructor(private http: Http,private commonService: CommonService) {
    }

	getEmployees() {
		if (this.cachedData) {
            return Observable.of(this.cachedData);
        } else {
            return this.http.get(environment.serviceApiUrl+'master/employees/', this.commonService.jwt())
                .map(res => res.json())
                .do((data) => {
                    this.cachedData = data;
                });
        }
    }
   
    getEmployeePayoutById(_id: string) {
        return this.http.get(environment.serviceApiUrl+'transaction/pay/memployee-payouts/' + _id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getEmployeePayouts(employeeId?:string, salaryForMonth?:string, salaryForYear?: string) {
        var url = environment.serviceApiUrl+'transaction/pay/memployee-payouts';

        if( employeeId != "" || salaryForMonth !=undefined || salaryForYear !=undefined ) {
            url = url + "?dummy=1";  
            
            if(employeeId != "" && employeeId != undefined){
                url = url + "&employeeId="+employeeId ;
            }
            if( salaryForMonth != undefined){
                url = url + "&salaryForMonth="+salaryForMonth ;
            }
            if( salaryForYear != undefined){
                url = url + "&salaryForYear="+salaryForYear ;
            }
        }

        return this.http.get(url, this.commonService.jwt()).map((res: Response) => res.json());
    }

    sendEmail(employeePayout: EmployeePayout) {
         var key = employeePayout.id;      
        let body = JSON.stringify(employeePayout);
        let url = environment.serviceApiUrl+'transaction/pay/memployee-payouts/' + key+'/send-email';
        return this.http.patch(url, body, this.commonService.jwt()).map((res: Response) => res);
    }
    getDocument(docId){
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({'authorization': currentUser.appToken});	
        let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
        let url =environment.documentApiUrl+'dm/images/'+docId;
        return this.http.get(url, options).catch((err: Response) => Observable.throw(err));  
	}
}