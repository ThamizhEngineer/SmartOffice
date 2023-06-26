import { Component } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Headers, Http, RequestOptions, Response } from "@angular/http";
import { Contractor,BankDetails,EmployeeSkill } from '../../../vo/contractor';
import { ContractorService } from '../contractor.service';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';import { DateParserService } from '../../../../shared/_services/date-parser.service';
import{environment} from '../../.././../../environments/environment';
import { Observable } from 'rxjs';
import { UserService } from '../../../../auth/_services';
import { JobService} from '../../../job/job.service';
import { saveAs } from 'file-saver';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
    selector: 'contractor-detail',
    templateUrl: './contractor-detail.component.html'
})
export class ContractorDetailComponent {
	//Parthiban Changes
	contractor: Contractor;
	constructorBank:BankDetails;
	isDateModified: boolean = false;
	isEdit: boolean = false;
	allContractor: any;
	allEmployees:any;

	addScreen: boolean = true;
	flowType = '';
	
	skillCode: any=[];
		
	msg: any;
	
	productList: any = [];
	productNames: any = [];
	serviceList: any = [];
	serviceNames: any = [];
	jobType="products";
	service="services";

	hideOfficial:boolean=false;
	offices:any =[];
	attendanceCodes=[];

	academicCount = 1;

	eduCount = 1;
	bankcount = 1;
	prevcount = 1;
	acccount = 1;
	
	documentApiUrl: string = environment.documentApiUrl;

	constructor(

		private activatedRoute: ActivatedRoute,
		private router: Router,
		private _service: ContractorService,
		private _userService: UserService,
		private http:Http,
		private jobService:JobService,
		private _dateParser: DateParserService

	) { }
	ngOnInit() {
		let currentUser=this._userService.getCurrentUser();
		if(currentUser){
			this._service.getContractors().subscribe(x=> {
				this.allContractor=x.filter((obj)=> {
					return obj.id != currentUser.employeeId;
				});
			});
		}

		this._service.getSkill().subscribe(x=>{
			this.skillCode=x;
		})

		this.jobService.getJobs(this.jobType).subscribe(x=>{
			this.productList.push(x)
			this.productList.forEach(x=>{
				this.productNames=x.content;
			})
			
		});
		this.jobService.getJobs(this.service).subscribe(x=>{
			this.serviceList.push(x);
			console.log(this.serviceList);
			this.serviceList.forEach(x=>{
				this.serviceNames=x;
				console.log(this.serviceNames);
			})
			
		});

		if(currentUser){
			this._service.getEmployees().subscribe(x=> {
				this.allEmployees=x
			});
		}
		this._service.getOffices().subscribe(x=>{
			this.offices=x;
		})

		this.contractor = new Contractor();
 		this.contractor.firstName='';
 		this.contractor.lastName='';
		this.contractor.bankDetails = [new BankDetails];
		this.contractor.employeeSkills = [new EmployeeSkill]

		for(let list of this.contractor.employeeSkills){
			if(list.skillLevelCode==null){
				list.skillLevelCode='1';
				this.contractor.employeeSkills[0]=list;
			}
		}
		if(this.contractor.managerId==null || this.contractor.managerId==''){
			this.contractor.managerName='';
		}	
		

		if (this.activatedRoute.params['_value']['id']) {
			this.isEdit = true;
			
			this.activatedRoute.params.switchMap((params: Params) => this._service.getContractorById(params['id']))
			.subscribe( x => {
				this.objModify(x);	
				
			});
		}


		this.activatedRoute.queryParams
		.subscribe(params=>{
			this.flowType = params.flowType;
			this.addScreen = false;
			if(params.flowType=="My Profile"){
				this.addScreen = false;
				this.hideOfficial=true;
			}else if(params.flowType=="Edit Contractor"){
				this.addScreen = false;
				this.hideOfficial=false;
			}else{
				
				this.addScreen = true;
				this.hideOfficial=false;
			}

			if(!this.addScreen){
			
				this._service.getContractorById(params.id).subscribe(x=>{
					this.contractor=x;
					console.log(this.contractor);
					this.objModify(x);
				});
			}
		 
		})
		
		console.log("addScreen"+this.addScreen)

		console.log("this.employee",this.contractor);

		
			
	}

	objModify(contractor:Contractor){
		this.contractor.vDob=this.frameDate(this.contractor.dob);
		this.contractor.vDoj=this.frameDate(this.contractor.doj);
		this.contractor.vNextIncr=this.frameDate(this.contractor.nextIncr);

		
	}

	selectedEmp: any;
	selEmp(){
		if(this.selectedEmp && this.selectedEmp.id) {
			this.router.navigateByUrl('/transaction/attendance/direct/'+this.selectedEmp.id);
		}
	}


	addEmployeeSkills() {
		let empSkill = new EmployeeSkill();
		if(empSkill.skillLevelCode==null){
			empSkill.skillLevelCode='1'
		}
		this.contractor.employeeSkills.push(empSkill);

	}	

	delEmployeeSkills(i) {

		this.contractor.employeeSkills.splice(i, 1);
	}

	projectSelected($event){
		this.contractor.managerId = $event.id;
	}

	addbankRows() {
		this.bankcount++;
		let bd = new BankDetails();
		this.contractor.bankDetails.push(bd);
	}
	delbankRow(i) {

		this.contractor.bankDetails.splice(i, 1);
	}


	

	saveEmployee() {

		
		this.contractor.dob =this.contractor.dob.substr(0,10);
		this.contractor.doj =this.contractor.doj.substr(0,10);

		console.log(this.contractor)

		if (this.addScreen) {
			this.addEmployee();
		} else {
			this.updateEmployee();
		}
	}

	listEmployee(){
		this.router.navigateByUrl("/operation/contractor/contractor-list");
	}
	addEmployee() {

		this._service.addContractor(this.contractor).subscribe(x => {

			this.msg = { type: 'success', text: "Added successfully" }
			this.router.navigateByUrl("/operation/contractor/contractor-list");
		}, error => {
			this.msg = { type: 'danger', text: "Error in Addition" }
			this.router.navigateByUrl("/operation/contractor/contractor-list");
		});
	}

	updateEmployee() {

		console.log(this.contractor);
		this._service.updateContractor(this.contractor).subscribe(x => {

			this.msg = { type: 'success', text: "Updated successfully" }
			this.router.navigateByUrl("/operation/contractor/contractor-list");
		}, error => {
			this.msg = { type: 'danger', text: "Error in Updation" }
			this.router.navigateByUrl("/operation/contractor/contractor-list");
		});
	}

	
	
	printPage(){
		var originalContents = document.body.innerHTML;
		var printReport= document.getElementById('printSection').innerHTML;
		document.body.innerHTML = printReport;
		window.print();
		document.body.innerHTML = originalContents;
	}

	updateDate($e, obj, param){
		let e = new Date($e.year+'-'+$e.month+'-'+$e.day);
		obj[param] = new Date(e.getTime() - (e.getTimezoneOffset() * 60000)).toJSON();
	}

	
	
	frameDate(str?: string, obj?: any){
		let year:any; let month:any; let day:any; let o:any;
		if(str){
			year = new Date(str).getFullYear();
			month = new Date(str).getMonth() + 1;
			day = new Date(str).getDate();
			
		}
		o = {year: year, month: month, day: day};
		
		if(obj) obj = o;
		else return o;
	}
	

	private jwt(paramArr?: any) {
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		let headers = new Headers({ 'Accept': 'application/json', 'enctype': 'multipart/form-data','authorization': currentUser.appToken});
		if(paramArr){
			let myParams = new URLSearchParams();
			paramArr.forEach( x => myParams.append(x.key, x.value) );
			return new RequestOptions({ headers: headers, params: myParams });
		}
		else return new RequestOptions({ headers: headers });
	}


	fileChange(event,type:any) {
		let fileList: FileList = event.target.files;
	 
	  
		if (fileList.length > 0) {
			let files: File = fileList[0];

			let formData: FormData = new FormData();
			formData.append('uploadingFiles', files, files.name);
			let headers = new Headers();

			headers.append('Accept', 'application/json');
			headers.append('enctype', 'multipart/form-data');
			let options = new RequestOptions({ headers: headers });
			let documentUrl=this.documentApiUrl + "dm/upload/"+type
			this.http.post(documentUrl, formData, this.jwt()).map((response: Response) => response)
				.map(res => res.json())
				.catch(error => Observable.throw(error))
				.subscribe(
					data => {
			   
						   this.contractor.docId=data[0].docId;
					 console.log(this.contractor.docId);

					

					},

					error => console.log(error)
				)
		}
		
	}


	download(docId){

		if(docId!=null&&docId!=""&&docId!=undefined){

			this._service.getDocument(docId).subscribe(x=>{
				saveAs(x.blob());
			})

	} 	
}


}