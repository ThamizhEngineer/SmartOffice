import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../environments/environment';
import {catchError, finalize} from 'rxjs/operators';
import {LoadingController, ToastController} from '@ionic/angular';
import {throwError} from 'rxjs';

@Injectable()
export class ExpenseClaimService {
	private loading: any;
	public error: string;

	baseUrl: string = environment.baseUrl + environment.serviceUrl;
	documentApiUrl: string = environment.baseUrl + environment.documentApiUrl;
	
	constructor(private http: HttpClient,
		private readonly loadingCtrl: LoadingController,
              private readonly toastCtrl: ToastController) {
    }
	
	private jwt() {
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		const httpHeaders = new HttpHeaders ({
			 'Authorization': currentUser.appToken
		   });
		return { headers: httpHeaders };
    }
	
	private jwtFile(){
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		const httpHeader = new HttpHeaders().set('Authorization', currentUser.appToken);
		return { headers: httpHeader };
	}
	
	getExpenseClaimData() {
		return this.http.get(this.baseUrl + 'transaction/expense-claims/my-expense-claims', this.jwt());
    }
	
	getExpenseClaimDataById(id: any) {
		return this.http.get(this.baseUrl + 'transaction/expense-claims/'+ id, this.jwt());
    }
	
	getCostCenters(){
		return this.http.get(this.baseUrl + 'master/cost-centers', this.jwt());
	}
	
	getJobCodes(){
		return this.http.get(this.baseUrl + 'temp/temp-job/byEmp', this.jwt());
	}
	
	getDocument(docId: any){
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		let headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization', currentUser.appToken);
		return this.http.get(this.documentApiUrl + 'dm/' + docId, {responseType: 'arraybuffer', headers:headers} );
	}
	
	applyExpenseClaim(data: any){
		return this.http.post(this.baseUrl + 'transaction/expense-claims/apply', data, this.jwt());
	}
	
	fileupload(file){
		let documentUrl=this.documentApiUrl + "dm/upload/EXPENSE-CLAIM-BILL-PROOF";
		const formData = new FormData();
		formData.append('uploadingFiles', new Blob([file]));
		
		return this.http.post(documentUrl, formData, this.jwtFile());
	
	}

// 	postData(formData: FormData) {

// 		let urlss="http://210.18.156.75/so-notification-service/summary/expense-claims/upload";
// 		console.log("hit-"+urlss)

// 		this.http.post<boolean>(urlss, formData, this.jwtFile())
// 		  .pipe(
// 			catchError(e => this.handleError(e)),
// 			finalize(() => this.loading.dismiss())
// 		  )
// 		  .subscribe(ok => this.showToast(ok));
// 	  }

// 	  private handleError(error: any) {
// 		const errMsg = error.message ? error.message : error.toString();
// 		this.error = errMsg;
// 		return throwError(errMsg);
// 	  }


//   private async showToast(ok: boolean | {}) {
//     if (ok === true) {
//       const toast = await this.toastCtrl.create({
//         message: 'Upload successful',
//         duration: 3000,
//         position: 'top'
//       });
//       toast.present();
//     } else {
//       const toast = await this.toastCtrl.create({
//         message: 'Upload failed',
//         duration: 3000,
//         position: 'top'
//       });
//       toast.present();
//     }
//   }
}