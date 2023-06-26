import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import {Employee,AcademicAcheiv,BankDetails,Department,PreviousEmploymentDetails,EducationalInfo,EmergencyContDetails,FamilyInfo,LanguagesKnown} from '../../vo/employee';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import{User,AuthUserRole} from '../../../../app/auth/_models/user'
import { CommonService } from '../../../shared/common/common.service';
@Injectable()
export class UserAccessService {

	baseUrl: string = environment.serviceApiUrl;
	authUrl: string = environment.authUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;
	
	constructor(private http: Http,private commonService:CommonService) {

    }

	private jwt() {
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json' });
		return new RequestOptions({ headers: headers });
    }

    getUsers(){		
        return this.http.get(this.authUrl + 'auth/users', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	getUserById(id:any){
		return this.http.get(this.authUrl + 'auth/users/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	loginAccess(id,access){
		let dummy
		return this.http.patch(this.authUrl + 'auth/users/login-access/'+id+'/'+access,dummy,this.commonService.jwt()).map((response: Response) => response);
	}
	getRoles(){
		return this.http.get(this.authUrl + 'auth/roles', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	getUserRoles(){
		return this.http.get(this.authUrl + 'auth/users-roles', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	addRoles(id:any,authUserRole:any){
		return this.http.patch(this.authUrl + 'auth/users/'+id,authUserRole, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	deleteUser(id){
		return this.http.delete(this.authUrl + 'auth/users/'+id, this.commonService.jwt());
	}
	deleteUserRole(id){
		return this.http.delete(this.authUrl + 'auth/users-roles/'+id,  this.commonService.jwt()).map((response) => response);
	}
	updatePassword(id, obj){
		return this.http.patch(this.authUrl+"auth/users/" + id + "/change-password", obj, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	
	getAuthUserPassword(id){
		return this.http.get(this.authUrl + "auth/users/" + id + "/fetch-password", this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	getAuthEmployee(){
		return this.http.get(this.authUrl + 'auth/users/auth-employee', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	getAuthApplicant(){
		return this.http.get(this.authUrl + 'auth/users/auth-applicant', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	getAuthClient(){
		return this.http.get(this.authUrl + 'auth/users/auth-client', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	getAuthVendor(){
		return this.http.get(this.authUrl + 'auth/users/auth-vendor', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}

	getApplicantById(id){
		return this.http.get(this.baseUrl + 'transaction/recruitments/applicants/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());	
	}

	createAuthApplicant(applicants){
		return this.http.post(this.baseUrl + 'transaction/recruitments/applicants/create-auth-applicant',applicants, this.commonService.jwt()).map((response: Response) => response);		
	}
	createAuthEmployee(id){
		return this.http.get(this.baseUrl + 'master/emp-profiles/create-auth-emp/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());		
	}

	createAuthVendor(id){
		return this.http.get(this.baseUrl + 'master/vendor/auth-vendor/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
	createAuthClient(id){
		return this.http.get(this.baseUrl + 'master/clients/auth-client/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}

	updateRole(obj){
		console.log(obj);
		return this.http.patch(this.authUrl+"auth/users/" + obj.authId , obj,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
	}
}
