import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { UtilsService } from '../../services/utils.service';

@Injectable({
  providedIn: 'root'
})
export class ExpClaimService {
  private loading: any;
  public error: string;

  baseUrl: string = environment.baseUrl + environment.serviceUrl;
  documentApiUrl: string = environment.baseUrl + environment.documentApiUrl;

  constructor(private http: HttpClient, public utils: UtilsService) { }

  private jwt() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var currentToken = this.utils.getCurrentToken(currentUser)
    const httpHeaders = new HttpHeaders({
      'Authorization': currentToken
    });
    return { headers: httpHeaders };
  }

  getExpenseClaimData() {
    return this.http.get(this.baseUrl + 'transaction/expense-claims/my-expense-claims', this.jwt());
  }

  getExpenseClaimDataById(id: any) {
    return this.http.get(this.baseUrl + 'transaction/expense-claims/' + id, this.jwt());
  }

  getCostCenters() {
    return this.http.get(this.baseUrl + 'master/cost-centers', this.jwt());
  }

  getJobCodes(empId) {
    return this.http.get(this.baseUrl + 'temp/temp-job/byEmp?employeeId=' +empId, this.jwt());
  }

  applyExpenseClaim(data: any){
		return this.http.post(this.baseUrl + 'transaction/expense-claims/apply', data, this.jwt());
  }
  
  getDocument(docId: any){
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var currentToken = this.utils.getCurrentToken(currentUser)
		let headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', currentToken);
		return this.http.get(this.documentApiUrl + 'dm/' + docId, {responseType: 'arraybuffer', headers:headers} );
  }
  
  postImage(formData: FormData): Observable<any> {
    let urlss=this.documentApiUrl + "dm/upload/EXPENSE-CLAIM-BILL-PROOF";
		return this.http.post<boolean>(urlss, formData, this.jwt())
	  }

}
