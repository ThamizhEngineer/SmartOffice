import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response, URLSearchParams,ResponseContentType } from "@angular/http";
import "rxjs/add/operator/map";
import { environment } from './../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';
import { Observable } from 'rxjs/Rx'

@Injectable()
export class ExpenseClaimService {

    baseUrl: string = environment.authUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;
	serviceApiUrl: string = environment.serviceApiUrl;
	documentApiUrl: string = environment.documentApiUrl;

    constructor(private http: Http,private commonService: CommonService) {
    }

    private jwt(paramArr?: any) {
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json','authorization': currentUser.appToken});
		if(paramArr){
			let myParams = new URLSearchParams();
			paramArr.forEach( x => myParams.append(x.key, x.value) );
			return new RequestOptions({ headers: headers, params: myParams });
		}
        else return new RequestOptions({ headers: headers });
	}
	

	apply(expense){
		return this.http.post(this.serviceApiUrl + 'transaction/expense-claims/apply',expense,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}

	getById(id:string){
		return this.http.get(this.serviceApiUrl + 'transaction/expense-claims/'+id, this.commonService.jwt()).map((response: Response) => response.json());
	}

	getAll(){		
        return this.http.get(this.serviceApiUrl + 'transaction/expense-claims/my-expense-claims', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	getAllByEmployeeId(id:string){
		return this.http.get(this.serviceApiUrl + 'transaction/expense-claims/'+id, this.commonService.jwt()).map((response: Response) => response.json());
	}
	
	getAllByCustomerId(id:string){
		return this.http.get(this.serviceApiUrl + 'transaction/expense-claims?customerId='+id, this.commonService.jwt()).map((response: Response) => response.json());
	}

	getAllByMerchantId(id:string){
		return this.http.get(this.serviceApiUrl + 'transaction/expense-claims?merchantId='+id, this.commonService.jwt()).map((response: Response) => response.json());
	}

	getJobCode(id){
        return this.http.get(environment.serviceApiUrl + 'transaction/job-plans/job-emp/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getCostCode(){
        return this.http.get(environment.serviceApiUrl + 'master/cost-centers', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
	}
	UpdateExpenseReq(id:any,action:any,expense:any){
        return this.http.patch(environment.serviceApiUrl+'transaction/expense-claims/'+id+'/'+action, expense, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
	}
	checkIn(doc:any){
		console.log(doc);
		return this.http.post(environment.documentApiUrl+'dm/checkin', doc, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    	
	}
	subCategory(){
		return this.http.get(environment.serviceApiUrl + 'master/expense-category/sub-category', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
	}
	printExpense(id:any){
		return this.http.get(environment.documentApiUrl+'document/prints/expense-claim/'+id+'/generate-pdf',this.commonService.jwt()).map(res => res);
	}

	getAcc1GrpEmployees() {
        return this.http.get(environment.serviceApiUrl+'transaction/expense-claims/my-acc1-create-claims', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

	getDocument(docId){
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({'authorization': currentUser.appToken, 'Content-Type': 'image/jpeg'});	
        let options = {responseType: ResponseContentType.Blob ,headers:headers};
        let url =environment.documentApiUrl+'dm/'+docId;
        return this.http.get(url, options).catch((err: Response) => Observable.throw(err));  
	}


    getReportData(purpose:string,fromDt:string,endDt:string){
        let url =environment.serviceApiUrl + 'transaction/expense-claims/bank-advise-report-expense-claim/'
        if (fromDt != null&&endDt!=null) {
            url = url + purpose+"/" +fromDt+"/"+endDt;
        }
    console.log(url)
        return this.http.get(url, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    exportAsExcel(purpose:string,fromDt:string,endDt:string){
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let url =environment.serviceApiUrl + 'transaction/expense-claims/bank-advise-report-expense-claim/'
        let headers = new Headers({'authorization': currentUser.appToken, 'Content-Type': 'image/jpeg'});
        let options = {headers:headers};
        if (purpose!=null&&fromDt != null&&endDt!=null) {
            url = url + purpose+"/"+ fromDt+"/"+endDt+".xls";
          
        }
        console.log(url)
        return this.http.get(url,{responseType: ResponseContentType.Blob,headers:headers }).map(
            (res) => {
                return new Blob([res.blob()], { type: 'application/vnd.ms-excel' })
            })
            // window.open(this.restUrl, "_blank");
    }
    getExcel(fromDt:string,endDt:string){
        let url =environment.serviceApiUrl + 'transaction/expense-claims/bank-advise-report-expense-claim/'
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (fromDt != null&&endDt!=null) {
            url = url +  fromDt+"/"+endDt+".xls";
          
        }
        console.log(url)
        let sUrl:string = url ;
        let headers = new Headers({'authorization': currentUser.appToken});
        return this.http.get(sUrl, { headers:headers, responseType: ResponseContentType.Blob});
      }
}