import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response,ResponseContentType, URLSearchParams } from "@angular/http";
import "rxjs/add/operator/map";
import { Observable } from 'rxjs/Rx'

import { environment } from './../environments/environment';
import { CommonService } from '../providers/common.service';
import { ExpenseClaim, ExpenseClaimBill } from "../pages/expense/vo/expense-claim";
@Injectable()
export class ExpenseClaimService {

    baseUrl: string = environment.authUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;
    serviceApiUrl: string = environment.serviceApiUrl;

    constructor(private http: Http,private commonService:CommonService ) {
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
	getCurrentUser() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser && currentUser.appToken) return currentUser;
        return null;
    }
	getAllExpenses(){
		return this.http.get(this.serviceApiUrl + 'transaction/expense-claims', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
	}

	getAllExpensesById(id:any){
		return this.http.get(this.serviceApiUrl + 'transaction/expense-claims/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
	}
	getAllExpenseCategory(){
		return this.http.get(this.serviceApiUrl + 'master/expense-category', this.jwt()).map((response: Response) => response.json());
	}

	getAllJobs(){
		return this.http.get(this.serviceApiUrl + 'transaction/jobs', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
	}
	getAllCostCenter(){
		return this.http.get(this.serviceApiUrl + 'master/cost-centers', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
	}
	getCostCenterById(id:any){
		return this.http.get(this.serviceApiUrl + 'master/cost-centers'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
	}
	getJobById(id:any){
		return this.http.get(this.serviceApiUrl + 'transaction/jobs/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
	}
	getDocument(docId){
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({'authorization': currentUser.appToken});	
        let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
        let url =environment.documentApiUrl+'dm/images/'+docId;
          return this.http.get(url, options).catch((err: Response) => Observable.throw(err));
	}
	getAllMerchants(){
		return this.http.get(this.serviceApiUrl+'master/merchants', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
	}

	create(expenseClaim:ExpenseClaim){		
	return this.http.post(this.serviceApiUrl + 'transaction/expense-claims',expenseClaim, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
		}
	
	updateExpense(expenseId:any,expenseClaim:ExpenseClaim){
	if(expenseId!=null){
		return this.http.patch(this.serviceApiUrl + 'transaction/expense-claims/'+expenseId+'/update',expenseClaim, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
	}


}
applyExpense(expenseId:any,expenseClaim:ExpenseClaim){
	if(expenseId!=null){
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let obj = JSON.stringify({  appliedEmpId: currentUser.employeeId})
		return this.http.patch(this.serviceApiUrl + 'transaction/expense-claims/'+expenseId+'/apply',obj, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
	}
	
}

	getById(id:string){
		return this.http.get(this.serviceApiUrl + 'transaction/expense-claims/'+id, this.jwt()).map((response: Response) => response.json());
	}

	getAll(){
		return this.http.get(this.serviceApiUrl + 'transaction/expense-claims', this.jwt()).map((response: Response) => response.json());
	}

	getAllByEmployeeId(id:string){
		return this.http.get(this.serviceApiUrl + 'transaction/expense-claims?employeeId='+id, this.jwt()).map((response: Response) => response.json());
	}

	getAllByCategoryId(id:string){
		return this.http.get(this.serviceApiUrl + 'transaction/expense-claims?categoryId='+id, this.jwt()).map((response: Response) => response.json());
	}
	
	getAllByJobId(id:string){
		return this.http.get(this.serviceApiUrl + 'transaction/expense-claims?jobId='+id, this.jwt()).map((response: Response) => response.json());
	}

	

	

	
	deleteById(id:string){
		return this.http.delete(this.serviceApiUrl + 'transaction/expense-claims/'+id,this.jwt()).map((response: Response) => response).map(res => res);
	}
}