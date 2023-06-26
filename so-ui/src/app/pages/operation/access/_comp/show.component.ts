import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { UserAccessService } from '../access.service';

import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { status_css } from '../../../vo/status'
import { User, AuthUserRole } from '../../../../../app/auth/_models/user';
import { userGroup } from '../../../job/user-group/vo/userGroup';
import { UserService } from '../../../../auth/_services/user.service';
import { AuthApplicant, AuthClient, AuthEmployee, AuthVendor } from '../../../vo/authUsers';


@Component({
	selector: 'user-access-show',
	templateUrl: './show.component.html',
})
export class UserAccessShowComponent implements OnInit {

	@ViewChild('cdelete') cdelete: TemplateRef<any>;
	@ViewChild('cpassword') cpassword: TemplateRef<any>;
	@ViewChild('cuser') cuser: TemplateRef<any>;
	@ViewChild('crole') crole: TemplateRef<any>;
	@ViewChild('alert') alert: TemplateRef<any>;
	constructor(private router: Router, private userService: UserService, private modalService: NgbModal, private _service: UserAccessService) {

	}

	authEmployees: [AuthEmployee];
	authApplicants: [AuthApplicant];
	authClients: [AuthClient];
	authVendors: [AuthVendor];
	statuses: any = status_css;
	rows: [User];
	authUsers: [User];
	roles: any = [];

	loginUser: User;
	access: boolean = false;
	user: User;
	rolesCount = 1;
	authRole: User;
	authUserRole: AuthUserRole;
	changeActive: string = 'Employees';

	employee: any = [];
	view: string;
	errorMsg: any;

	epageSize: number = 10
	epage: number = 1;
	apageSize: number = 10
	apage: number = 1;
	cpageSize: number = 10
	cpage: number = 1;
	vpageSize: number = 10
	vpage: number = 1;
	saveMsg: any;
	Applicants:any=[];
	id:any;
	type:string;
	userName:string;

	ngOnInit() {
		this.rows = [new User];
		this.authApplicants = [new AuthApplicant];
		this.authEmployees = [new AuthEmployee];
		this.authClients = [new AuthClient];
		this.authVendors = [new AuthVendor];
		this.getAuthUsers();
		this.getUsers();
		this.user = new User();
		this.authRole = new User();
		this.authRole.authUserRoles = [new AuthUserRole];
		this.loginUser = this.userService.getCurrentUser();
		this.getRoles();

		if (this.loginUser.userType == 'SU') {
			this.view = 'SU'
		} else {
			this.loginUser.authUserRoles.forEach(element => {
				if (element.authRoleCode == 'HR1' || element.authRoleCode == 'HR2') {
					this.view = 'HR'
				} else if (element.authRoleCode == 'ADMIN') {
					this.view = 'ADMIN'
				}
			});
		}

	}


	getAuthUsers() {
		this._service.getAuthApplicant().subscribe(app => {
			app.forEach(element => {
				element.isEnabledFlag = (element.userStatus == 'INACTIVE' ? true : false);
			});
			this.authApplicants = app;
		});

		this._service.getAuthEmployee().subscribe(emp => {
			emp.forEach(element => {
				element.isEnabledFlag = (element.userStatus == 'INACTIVE' ? true : false);
			});
			this.authEmployees = emp;
			console.log(this.authEmployees)
		})
		this._service.getAuthClient().subscribe(client => {
			client.forEach(element => {
				element.isEnabledFlag = (element.userStatus == 'INACTIVE' ? true : false);
			});
			this.authClients = client;
		});
		this._service.getAuthVendor().subscribe(vendor => {
			vendor.forEach(element => {
				element.isEnabledFlag = (element.userStatus == 'INACTIVE' ? true : false);
			});
			this.authVendors = vendor;
		});
	}


	getUsers() {		
		this._service.getUsers().subscribe(x => {

			this.rows = x;
			this.rows.forEach(emp => {
				this.authEmployees.forEach(authEmp => {
					if (authEmp.employeeId == emp.employeeId) {
						authEmp.password = emp.password;
						authEmp.authUserRoles = [new AuthUserRole]
						authEmp.authUserRoles = authEmp.authUserRoles.concat(emp.authUserRoles);
					}
				});
			});
			this.objBefArr(x);
		});
	}

	get employeeHrAndAdmin() {
		return this.rows.filter(x => x.userType != 'SU' && x.employeeId != null);
	}

	get getEmployee() {
		return this.rows.filter(x => x.employeeId != null);
	}
	get getApplicant() {
		return this.rows.filter(x => x.applicantId != null);
	}
	getRoles() {
		this._service.getRoles().subscribe(_roles => {
			this.roles = _roles;
			// _roles.forEach(r => {
			// 	if(r.roleCode == "SU"){  // only super-users can make another user as super-user
			// 		if (this.loginUser.userType == 'SU') {
			// 			this.roles.push(r);	
			// 		}
			// 	}
			// 	else{
			// 		this.roles.push(r);
			// 	}
			// });			 
		})
	}
	objBefArr(x) {
		this.user.authUserRoles = this.user.authUserRoles ? this.user.authUserRoles : [new AuthUserRole()];
	}
	addRoles() {
		this.rolesCount++;
		let newRole = new AuthUserRole();
		this.authRole.authUserRoles.push(newRole);
	}
	delRoles(i) {
		if(this.authRole.authUserRoles[i].authRoleCode == "SU"){
			if (this.loginUser.userType == 'SU') {
				this.authRole.authUserRoles.splice(i, 1);
			}
			this.saveMsg = { type: 'danger', text: "Only a SuperUser can remove SU access for another SuperUser "};
			return false;
		}
		else{
			this.authRole.authUserRoles.splice(i, 1);
		}
	}
	modalReference: any = null;
	modalData: any;
	deleteUser(user: any) {
		this.modalData = user;
		this.modalReference = this.modalService.open(this.cdelete);
	}

	loginAccess(User) {
		
		let access = User.isEnabledFlag ? 'ACTIVE' : 'INACTIVE'		
		this._service.loginAccess(User.authId,access).subscribe(x => {											
			this.saveMsg = { 'type': 'success', 'text': "Login Access Changed Successfully" }
		}, error => {
			User.isEnabledFlag=!User.isEnabledFlag;
			this.errorMsg = JSON.parse(error._body);
			this.saveMsg = { type: 'danger', text: this.errorMsg.message }
		});
	}

	formAuthUsers(usr) {
		let auth =new User();
		auth.isEnabled = usr.isEnabledFlag ? "N" : "Y" 
		auth.id = usr.authId;
		this.authUsers = [new User];
		this.authUsers.splice(0,1);
		this.authUsers.push(auth);	
		console.log(this.authUsers);	
	}


	
	confirmDelete(data) {

		this._service.deleteUser(data.authId).subscribe(x => {
			this.getUsers();
			this.saveMsg = { 'type': 'success', 'text': "User Deleted Successfully" }
			this.modalReference.close();
		}, e => {
			this.getUsers();
			this.modalReference.close();
			this.saveMsg = { 'type': 'danger', 'text': "Error in Deletion" };
		});
	}
	assignRole(id: string) { 
		this._service.addRoles(id, this.authRole).subscribe(x => {
			this.saveMsg = { 'type': 'success', 'text': "Role Added Successfully" }
			this.getUsers();
			this.modalReference.close();
		})
	}
	currentPwd: string;
	newPword: string;
	cnewPword: string;
	newUserName: string;
	cnewUserName: string;
	changePassword(user: any) {
		console.log(user)
		this.getCurrentPassword(user.authId); 
		this.modalData = user;
		this.newPword = this.cnewPword = "";
		this.modalReference = this.modalService.open(this.cpassword);
	}

	getCurrentPassword(authUserId: string) {
		this._service.getAuthUserPassword(authUserId).subscribe(_result => {
			this.currentPwd = _result;
		}, e => {			
			this.saveMsg = { 'type': 'danger', 'text': "Error in fetching password" };
		});
	}

	updatePassword(user: any) {
		let id = user.authId;
		let obj = { password: this.newPword };
		this._service.updatePassword(id, obj).subscribe(y => {
			this.newPword = this.cnewPword = '';
			this.saveMsg = { 'type': 'success', 'text': "Password updated Successfully" };
			this.modalReference.close();
		}, e => {
			this.modalReference.close();
			this.saveMsg = { 'type': 'danger', 'text': "Error in updating password" };
		});
	}

	changeUser(user: any) {
		this.modalData = user;
		this.newUserName = this.cnewUserName = "";
		this.modalReference = this.modalService.open(this.cuser);
	}

	updateUser(user: any) {
		user.userName = this.newUserName;
		console.log(user)
		this._service.updateRole(user).subscribe(y => {
			this.newUserName = this.cnewUserName = '';
			this.getUsers();
			this.saveMsg = { 'type': 'success', 'text': "User Name updated Successfully" };
			this.modalReference.close();
		}, e => {
			this.modalReference.close();
			this.saveMsg = { 'type': 'danger', 'text': "Error in updating User Name" };
		});
	}

	changeRole(user: any) {
		let role = new AuthUserRole();
		this._service.getUserById(user.authId).subscribe(x => {
			this.authRole = x;
			if (this.authRole.authUserRoles[0] == null) {
				this.authRole.authUserRoles.push(role);
			}
		})
		this.modalData = user;
		this.modalReference = this.modalService.open(this.crole);
	}

	createUserAlert(type,id,name){
		this.userName=name;
		this.type=type;
		switch (type) {
			case 'Employee':
				this.id=id;
				break;
			case 'Applicant':
				this._service.getApplicantById(id).subscribe(x=>{
					this.Applicants.push(x);
				});
				break;
			case 'Client':
				this.id=id;
				break;
			case 'Vendor':
				this.id=id;
				break;			
		}
		this.modalReference = this.modalService.open(this.alert, {size: 'sm'});
	}

	createAuthEmployee(){
		this.modalReference.close();
		this._service.createAuthEmployee(this.id).subscribe(x=>{
			if(x.message=='Login Access created Successfully'){
				this.saveMsg = { 'type': 'success', 'text': x.message }			
			}else{
				this.saveMsg = { 'type': 'danger', 'text': x.message }	
			}	
			this.ngOnInit();			
		})
	}

	createAuthApplicant(){
		this.modalReference.close();
		this._service.createAuthApplicant(this.Applicants).subscribe(app=>{
			this.saveMsg = { 'type': 'success', 'text': "Login Access created Successfully" }
		},error=>{
			this.saveMsg = { 'type': 'danger', 'text': "Error While Create Login Access" }
		})
		this.ngOnInit();
	}

	createAuthVendor(){
		this.modalReference.close();
		this._service.createAuthVendor(this.id).subscribe(app=>{
			this.saveMsg = { 'type': 'success', 'text': "Login Access created Successfully" }
		},error=>{
			this.saveMsg = { 'type': 'danger', 'text': "Error While Create Login Access" }
		})
		this.ngOnInit();
	}

	createAuthClient(){
		this.modalReference.close();
		this._service.createAuthClient(this.id).subscribe(app=>{
			this.saveMsg = { 'type': 'success', 'text': "Login Access created Successfully" }
		},error=>{
			this.saveMsg = { 'type': 'danger', 'text': "Error While Create Login Access" }
		})
		this.ngOnInit();
	}

	updateRole(data) {
		this._service.updateRole(data).subscribe(x => {
			this.saveMsg = { 'type': 'success', 'text': "User Role Successfully" }
			this.modalReference.close();
		}, e => {
			this.modalReference.close();
			this.saveMsg = { 'type': 'danger', 'text': "Error in Updating Role" };
		});
	}
}
