import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators,FormControl } from '@angular/forms';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Employee, LanguagesKnown, FamilyInfo, EmergencyContDetails,EmployeeSkill, EducationalInfo, PreviousEmploymentDetails, Department, BankDetails, AcademicAcheiv } from '../../../vo/employee';
import { EmployeeConventionService } from '../employee-convention.service';
import { DateParserService } from '../../../../shared/_services/date-parser.service';
import { JobService} from '../../../job/job.service';

import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { Headers, Http, RequestOptions, Response, URLSearchParams,ResponseContentType } from "@angular/http";
import { Observable } from 'rxjs/Observable';
import { UserService } from '../../../../auth/_services';
import{environment} from '../../../../../environments/environment';
import { saveAs } from 'file-saver';
import { DocMetadata, DocInfo } from '../../../vo/doc-info';
import { CommonService } from '../../../../shared/common/common.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { status_css } from '../../../vo/status'


@Component({
	selector: '',
	templateUrl: './employee-convention-detail.component.html'
})
export class EmployeeConventionDetailComponent implements OnInit {
	//Parthiban Changes

	@ViewChild('Create') Create: TemplateRef<any>;
    modalReference:any;
	employee: Employee;
	empCod:boolean;
	form: FormGroup;
	statuses:any=status_css;
	isDateModified: boolean = false;
	isEdit: boolean = false;
	allEmployees: any=[];
	docName:any;
	docLocation:any;
	addScreen: boolean = true;
	flowType = '';
	docMetadata:DocMetadata;

	docsForCheckin: DocInfo[] = []; 
	docIdsForCheckin: DocInfo[] = [];

	docInfos:Array<DocInfo>;
	languagesArray: Array<LanguagesKnown>;
	// languagesArray: Array<LanguagesKnown>;
	msg: any;
	hr1:any=[];
	hr2:any=[];
	acc1:any=[];
	acc2:any=[];
	dir:any=[];
	offices:any=[];
	department:any=[];
	grades: any = [];
	designations: any = [];
	isSingle: boolean = false;
	hideOfficial:boolean=false;

	attendanceCodes=[];

	academicCount = 1;

	eduCount = 1;

	bankcount = 1;
	prevcount = 1;
	acccount = 1;
	count = 1;
	children = [];
	skillCode: any=[];
	languageCount = 1;

	deptId:any;
	diretId:any;

	familyCount = 1;
	productList: any = [];
	productNames: any = [];
	serviceList: any = [];
	serviceNames: any = [];
	empSkillCount = 1;
	documentApiUrl: string = environment.documentApiUrl;
	jobType="products";
	service="services";

	ename:FormControl;
	lename:FormControl;
	cno:FormControl;
	dob:FormControl;
	emailId:FormControl;
	gender:FormControl;
	design:FormControl;
	hrgroup1:FormControl;
	hrgroup2:FormControl;
	accgroup1:FormControl;
	accgroup2:FormControl;
	director:FormControl;
	pfNo:FormControl;
	esiNo:FormControl;
	uanNo:FormControl;
	dept:FormControl;
	office:FormControl;
	managerName:FormControl;
	reviewManagerName:FormControl;
	bankinfo:any=[];

	emp = (text$: Observable<string>) =>text$.debounceTime(200).map(term => term === '' ? []
        : this.allEmployees.filter(v => v.empName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10));
	formatter = (x: {empName: string}) => x.empName;
	profileAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.allEmployees.filter(v => v.empName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
	constructor(

		private activatedRoute: ActivatedRoute,
		private router: Router,
		private modalService: NgbModal,
		private _service: EmployeeConventionService,
		private formBuilder: FormBuilder,
		private _userService: UserService,
		private http:Http,
		private commonService: CommonService,
		private _dateParser: DateParserService,
		private jobService:JobService

	) { }
	imageToShow: any;

	
	ngOnInit() {
		this.createFormControls();
		this.getData();
     	this.createForm();
		let currentUser=this._userService.getCurrentUser();
		this.docMetadata= new DocMetadata();
		this.docInfos=[];
		if(currentUser){
			this._service.getEmployees().subscribe(x=> {
				this.allEmployees=x;
				
			});
		}

		this._service.getSkill().subscribe(x=>{
			this.skillCode=x;
		})
		this._service.getHrL1().subscribe(x=>{
			this.hr1=x;
		})
		this._service.getHrL2().subscribe(x=>{
			this.hr2=x;
			
		})
		this._service.getAcc1().subscribe(x=>{
			this.acc1=x;
			
		})
		this._service.getAcc2().subscribe(x=>{
			this.acc2=x;
		})
		this._service.getDir().subscribe(x=>{
			for(let list of x){
				if(list.isDir=='Y'){
					this.dir.push(list);
				}							
			}
			this.diretId=this.dir[0].id;	
			
		})
		this._service.getOffices().subscribe(x=>{
			this.offices=x;
		})
		this._service.getDepartment().subscribe(dept=>{
			this.department=dept;
			this.deptId=this.department[0].id
			this.check();
		})

		
		this.employee = new Employee();
 		this.employee.empName='';
 		this.employee.name='';
		this.employee.languages = [new LanguagesKnown];
		this.employee.academicAcheiv = [new AcademicAcheiv];
		this.employee.educationalInfo = [new EducationalInfo];
		this.employee.familyInfo = [new FamilyInfo];
		this.employee.previousEmployDetails = [new PreviousEmploymentDetails];
		this.employee.bankDetails = [new BankDetails];
		this.employee.employeeSkills= [new EmployeeSkill];

		
		
		this._service.getConfig().subscribe(x=>{
			x.forEach(x=>{
					this.attendanceCodes.push(x);
			})
		});

		
		if (this.activatedRoute.params['_value']['id']) {
			this.isEdit = true;
			
			this.activatedRoute.params.switchMap((params: Params) => this._service.getEmployeeById(params['id']))
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
			}else if(params.flowType=="Edit Employee" || params.flowType=="On Board Profile"){
				this.addScreen = false;
				this.hideOfficial=false;
			}else{
				//add employee
				this.addScreen = true;
				this.hideOfficial=false;
			}

			if(!this.addScreen){
				this._service.getEmployeeById(params.id).subscribe(x=>{
					this.employee=x;
				this.docName=x.docName;									
					if(this.employee.maritalStatus=='N'){
						this.valueIsSingle();
					}
					this.employee.flowType=params.flowType;
					
					this.objBefArr(x);
					this.objModify(x);
				});
			}
		 
		})
		
		this._service.getGrades().subscribe(x=>this.grades = x);
		this._service.getDesignations().subscribe(x=>this.designations = x);

	this.jobService.getJobs(this.jobType).subscribe(x=>{
		this.productList.push(x)
		this.productList.forEach(x=>{
			this.productNames=x.content;
		})
		
	});
	this.jobService.getJobs(this.service).subscribe(x=>{
		this.serviceList.push(x);
		this.serviceList.forEach(x=>{
			this.serviceNames=x;	
		})
	});	
	
	}

	getData(){
        this._service.getBankInfo().subscribe(x=>{
            this.bankinfo=x;
        });
    }

	check(){

		for(let list of this.employee.employeeSkills){
			if(list.skillLevelCode==null){
				list.skillLevelCode='1';
				this.employee.employeeSkills[0]=list;
			}
		}
		if(this.employee.dirUsrGrpId==null){
			this.employee.dirUsrGrpId=this.diretId;
		}
		if(this.employee.n1EmpId==null){
			this.employee.managerName=null;
		}
		if(this.employee.n2EmpId==null){
			this.employee.reviewManagerName=null;
		}
		if(this.employee.empCategory==null){
			this.employee.empCategory='Regular';
		}
		if(this.employee.deptId==null){
			
			this.employee.deptId=this.deptId
		}
	}

	createFormControls(){
		this.ename = new FormControl('', [Validators.required,Validators.pattern('[a-zA-Z0-9][a-zA-Z0-9 ]+')]);
		this.lename = new FormControl('', [Validators.required,Validators.pattern('[a-zA-Z0-9]+')]);
		this.gender = new FormControl('', [Validators.required]);
		this.managerName = new FormControl('', [Validators.required]);
		this.reviewManagerName = new FormControl('', [Validators.required]);
		this.office = new FormControl('', [Validators.required]);
		this.emailId= new FormControl('', [Validators.required,Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$')]);
		this.cno= new FormControl('', [Validators.required,Validators.pattern('[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}')]);

		this.design = new FormControl('', [Validators.required]);
		this.hrgroup1 = new FormControl('', [Validators.required]);
		this.hrgroup2 = new FormControl('', [Validators.required]);
		this.accgroup1 = new FormControl('', [Validators.required]);
		this.accgroup2 = new FormControl('', [Validators.required]);
		this.director = new FormControl('', [Validators.required]);
		this.pfNo = new FormControl('', [Validators.required]);
		this.esiNo = new FormControl('', [Validators.required]);
		this.uanNo = new FormControl('', [Validators.required]);
		this.dept = new FormControl('', [Validators.required]);
	}

	createForm() {
		this.form = new FormGroup({
		ename:this.ename,
		lename:this.lename,
		emailId:this.emailId,
		cno:this.cno,
		managerName:this.managerName,
		reviewManagerName:this.reviewManagerName,
		gender:this.gender,
		
		design:this.design,
		hrgroup1:this.hrgroup1,
		hrgroup2:this.hrgroup2,
		accgroup1:this.accgroup1,
		accgroup2:this.accgroup2,
		director:this.director,	
		pfNo:this.pfNo,
		esiNo:this.esiNo,
		uanNo:this.uanNo,
		office:this.office,
		dept:this.dept
		});

	}

	create(){
		this.modalReference = this.modalService.open(this.Create, {size: 'lg'});
	}

	getEmployeeById(id:any){
		
			this.isEdit = true;
			
		this._service.getEmployeeById(id).subscribe( x => {
				
				this.objModify(x);			
			});
		
	}
	
	
	objModify(employee:Employee){
	
		// if(this.employee.isHighLevelManager=="N"){
		// 	this.employee.isHighLevelManager=null
		// }
		// if(this.employee.isMiddleLevelManager=="N"){
		// 	this.employee.isMiddleLevelManager=null
		// }
		this.employee.vDob=this.frameDate(this.employee.dob);
		this.employee.vNextIncr=this.frameDate(this.employee.nextIncr);

		this.employee.previousEmployDetails.forEach(x=>{
			x.vFromDt=this.frameDate(x.fromDt);
			x.vToDt=this.frameDate(x.toDt);
		});

		this.employee.academicAcheiv.forEach(x=>{
			x.vFromDt=this.frameDate(x.fromDt);
			x.vToDt=this.frameDate(x.toDt);
		});
		
		this.form.patchValue({
		  managerName: employee.managerName,
		  reviewManagerName: employee.reviewManagerName,
		  design:this.design,
			hrgroup1:this.hrgroup1,
			hrgroup2:this.hrgroup2,
			accgroup1:this.accgroup1,
		  	accgroup2:this.accgroup2,
			director:this.director,	
			pfNo:this.pfNo,
			esiNo:this.esiNo,
			uanNo:this.uanNo,
			office:this.office,
			dept:this.dept
		});
		
	}
	projectSelected($event){
		this.employee.n1EmpId = $event.item.id;
		this.employee.managerName= $event.item.empName;
	}
	projectSelected1($event){
		this.employee.n2EmpId = $event.item.id;
		this.employee.reviewManagerName= $event.item.empName;
	}
	selectedEmp: any;
	selEmp(){
		if(this.selectedEmp && this.selectedEmp.id) {
			this.router.navigateByUrl('/transaction/attendance/direct/'+this.selectedEmp.id);
		}
	}

	// isManager(value){
	// 	if(value=='H'){
	// 		this.employee.isMiddleLevelManager=null;
	// 	}
	// 	if(value=='M'){
	// 		this.employee.isHighLevelManager=null;
	// 	}
	// }

	valueIsMarried(){
		if(this.isSingle){
		
			this.isSingle = false;
		}
	}

	valueIsSingle(){
		
		if(this.isSingle){
			this.isSingle = false;
		}else{
			this.isSingle = true;
		}
	
			}

	addEduRows() {
		this.eduCount++;
		let ei = new EducationalInfo();
		this.employee.educationalInfo.push(ei);
	}
	delEduRow(i) {

		this.employee.educationalInfo.splice(i, 1);
	}
	addAccRows() {
		this.acccount++;
		let ac = new AcademicAcheiv();
		this.employee.academicAcheiv.push(ac);
	}
	delAccRow(i) {

		this.employee.academicAcheiv.splice(i, 1);
	}
	addprevRows() {
		this.prevcount++;
		let pe = new PreviousEmploymentDetails();
		this.employee.previousEmployDetails.push(pe);
	}
	delprevRow(i) {

		this.employee.previousEmployDetails.splice(i, 1);
	}
	addbankRows() {
		this.bankcount++;
		let bd = new BankDetails();
		this.employee.bankDetails.push(bd);
	}
	delbankRow(i) {

		this.employee.bankDetails.splice(i, 1);
	}

	childrenCount($e) {
		this.children = [];
		for (let i = 0; i < $e; i++) {
			this.children.push(i);
		}
	}

	addLanguageRows() {
		this.languageCount++;
		let ts = new LanguagesKnown();
		this.employee.languages.push(ts);

	}

	delLanguageRow(i) {
		this.employee.languages.splice(i, 1);
	}
	addFamilyRows() {
		this.familyCount++;
		let fi = new FamilyInfo();
		this.employee.familyInfo.push(fi);
	}
	addEmployeeSkills() {
		
			let empSkill = new EmployeeSkill();
		if(empSkill.skillLevelCode==null){
			empSkill.skillLevelCode='1'
		}
		this.employee.employeeSkills.push(empSkill);
		
		
	}
	delFamilyRow(i) {

		this.employee.familyInfo.splice(i, 1);
	}
	delEmployeeSkills(i) {

		this.employee.employeeSkills.splice(i, 1);
	}

	saveEmployee() {
		this.employee.isMiddleLevelManager =(this.employee.isMiddleLevelManager?"Y":"N");
		this.employee.isHighLevelManager =(this.employee.isHighLevelManager?"Y":"N");
			this.employee.relInPodhigai =(this.employee.relInPodhigai?"Y":"N");
		if(this.employee.languages.length)
		this.employee.languages.forEach(language => {
			language.langRead = (language.langRead ? "Y" : "N");
			language.langSpeak = (language.langSpeak ? "Y" : "N");
			language.langWrite = (language.langWrite ? "Y" : "N");
		});
		this.employee.dob =this.employee.dob.substr(0,10);
		// console.log(this.employee)

		this.employee.empName=this.employee.firstName+" "+this.employee.lastName;
		if(this.form.valid){
			if (this.addScreen) {
				this.addEmployee();
			} else {
				this.updateEmployee();
			}
		}else{
			console.log(this.form);
			this.msg = { type: 'danger', text: "Please fill required Data" }
		}
		

		
	}

	printEmpProfile(){
		this._service.printEmpProfile(this.employee.id).subscribe(x=>{
			window.location.reload();
		})
	}

	listEmployee(){
			this.router.navigateByUrl("/operation/employee-convention/list");
	}
	addEmployee() {

		this._service.addEmployee(this.employee).subscribe(x => {
			this.employee=x;
			this.prepareDocInfosForCheckin();
			this.CheckIn();
			if(this.flowType=='My Profile'){
				this.msg = { type: 'success', text: "Added successfully" }
				this.router.navigateByUrl("/index");
			}else{
				this.employee=x;
			}

		}, error => {
			if(this.flowType=='My Profile'){
				this.router.navigateByUrl("/index");
			}else{
				this.router.navigateByUrl("/operation/employee-convention/list");
			}

		});
	}

	onSameCurrentAddress($event) {
		this.employee.permAddress = this.employee.curAddress;
	

	}

	updateEmployee() {

		
		this._service.updateEmployee(this.employee,this.employee.id).subscribe(x => {
			this.employee=x;
			this.prepareDocInfosForCheckin();
			this.CheckIn();

			if(this.flowType=='My Profile'){
				this.msg = { type: 'success', text: "Updated successfully" }
				this.router.navigateByUrl("/index");
			}else{
				this.router.navigateByUrl("/operation/employee-convention/list");
			}
		}, error => {
			if(this.flowType=='My Profile'){
				this.msg = { type: 'success', text: "Updated successfully" }
				this.router.navigateByUrl("/index");
			}else{
				this.msg = { type: 'danger', text: "Error in Updation" }
				this.router.navigateByUrl("/operation/employee-convention/list");
			}
		});
	}

	OnBoardEmployee(action){
		this.employee.isMiddleLevelManager =(this.employee.isMiddleLevelManager?"Y":"N");
		this.employee.isHighLevelManager =(this.employee.isHighLevelManager?"Y":"N");
		this.employee.relInPodhigai =(this.employee.relInPodhigai?"Y":"N");
		this.employee.dob =this.employee.dob.substr(0,10);
		this.employee.emailId=this.employee.contactEmailId;
		this.employee.empName=this.employee.firstName+" "+this.employee.lastName
		console.log(this.employee);
		if(this.form.valid){
			this._service.employeeUpdate(this.employee.id,action,this.employee).subscribe(x=>{
				this.prepareDocInfosForCheckin();
				this.CheckIn();
				if(action=='profile-validate'){
					this.router.navigateByUrl("/operation/employee-convention/list");
				}else if(action=='onboard'){
					this.router.navigateByUrl("/director/on-board-profile");
				}else if(this.flowType=='My Profile'){
					this.router.navigateByUrl("/index");
				}
				
			})
		}
		else{
			if(this.flowType=="Edit Employee" || this.flowType=="On Board Profile"){
				this.msg = { type: 'danger', text: "please fill all mandatory fields" }
			}else{
				this.msg = { type: 'danger', text: "Please contact HR to Update your Offical Information" }
			}
		}
		

	}
	createEmp(){
		this.employee.isMiddleLevelManager =(this.employee.isMiddleLevelManager?"Y":"N");
		this.employee.isHighLevelManager =(this.employee.isHighLevelManager?"Y":"N");
		this.employee.relInPodhigai =(this.employee.relInPodhigai?"Y":"N");
		this.employee.dob =this.employee.dob.substr(0,10);
		this.employee.emailId=this.employee.contactEmailId;
		this.employee.empName=this.employee.firstName+" "+this.employee.lastName
		if(this.form.valid){
			this._service.createProfile(this.employee).subscribe(x=>{
				this.employee=x.memployee;			
				this.prepareDocInfosForCheckin();
				this.CheckIn();
				this.router.navigate(['/operation/employee-convention/detail/'] ,{queryParams:{flowType:"Edit Employee",id:this.employee.id}});
				this.modalReference.close(); 
			})
		}else{
			this.msg= { type: 'danger', text: "Please fill all the personal & offical info fields" }
		}
		
	}

	objBefArr(x) {


		this.employee.languages = this.employee.languages.length ? this.employee.languages : [new LanguagesKnown()];
		this.languageCount = this.employee.languages.length ? this.employee.languages.length : 1;
		this.employee.familyInfo = this.employee.familyInfo.length ? this.employee.familyInfo : [new FamilyInfo()];
		this.employee.employeeSkills=this.employee.employeeSkills.length? this.employee.employeeSkills :[new EmployeeSkill()];
		this.familyCount = this.employee.familyInfo.length ? this.employee.familyInfo.length : 1;
		this.empSkillCount = this.employee.employeeSkills.length ? this.employee.employeeSkills.length : 1;
		this.employee.educationalInfo = this.employee.educationalInfo.length ? this.employee.educationalInfo : [new EducationalInfo()];
		this.eduCount = this.employee.educationalInfo.length ? this.employee.educationalInfo.length : 1;


		this.employee.academicAcheiv = this.employee.academicAcheiv.length ? this.employee.academicAcheiv : [new AcademicAcheiv()];
		this.academicCount = this.employee.academicAcheiv.length ? this.employee.academicAcheiv.length : 1;


		this.employee.previousEmployDetails = this.employee.previousEmployDetails.length ? this.employee.previousEmployDetails : [new PreviousEmploymentDetails()];
		this.prevcount = this.employee.previousEmployDetails.length ? this.employee.previousEmployDetails.length : 1;


		this.employee.bankDetails = this.employee.bankDetails.length ? this.employee.bankDetails : [new BankDetails()];
		this.bankcount = this.employee.bankDetails.length ? this.employee.bankDetails.length : 1;
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

	fileChange(event,type:any) {
		let fileList: FileList = event.target.files;  
		if (fileList.length > 0) {
			this.commonService.uploadDocument(fileList[0], type).map((response: Response) => response)
                .catch(error => Observable.throw(error))
                .subscribe(data => {
					 this.employee.docId=data[0].docId;
					 this.docIdsForCheckin.push(data[0].docId);
					 
					},error => console.log(error)
				)}	
	}

	fileChangePanCard(event,type:any) {
		let fileList: FileList = event.target.files;
		if (fileList.length > 0) {
			this.commonService.uploadDocument(fileList[0], type).map((response: Response) => response)
                .catch(error => Observable.throw(error))
                .subscribe(
                    data => {
						   this.employee.panDocId=data[0].docId;
						   this.docIdsForCheckin.push(data[0].docId);
					 
					},
					error => console.log(error)
				)}
	}

	fileChangeAadharCard(event,type:any) {
		let fileList: FileList = event.target.files;
	 
	  
		if (fileList.length > 0) {
			this.commonService.uploadDocument(fileList[0], type).map((response: Response) => response)
                .catch(error => Observable.throw(error))
                .subscribe(
                    data => {
			   
						   this.employee.aadharDocId=data[0].docId;
					 
					 this.docIdsForCheckin.push(data[0].docId);

					

					},

					error => console.log(error)
				)
		}
		
	}

	fileChangeIdProof(event,type:any) {
		let fileList: FileList = event.target.files;
	 
	  
		if (fileList.length > 0) {
			

			this.commonService.uploadDocument(fileList[0], type).map((response: Response) => response)
			.catch(error => Observable.throw(error))
			.subscribe(
				data =>  {
							if(type=='EMP-ADDRESS-PROOF'){
								this.employee.addrProofDocId=data[0].docId;								
								this.docIdsForCheckin.push(data[0].docId);
							}
					      if(type=='EMP-PASSPORT-DOCID'){
								this.employee.passDocId=data[0].docId;								
								this.docIdsForCheckin.push(data[0].docId);
							}
							if(type=='EMP-DRIVING-PROOF'){
								this.employee.drivLicDocId=data[0].docId;								
								this.docIdsForCheckin.push(data[0].docId);
							}					   
					 
					},

					error => console.log(error)
				)
		}
		
	}


	docMetaData(docId:any,empId){
        let docInfo:DocInfo = new DocInfo();
        docInfo.docId = docId;
        docInfo.metadataList = [
        {
        isKey:'Y',
        mdCode:'emp-id',
        mdValue:empId
        }
    ];
        return docInfo; 
	}
	

	prepareDocInfosForCheckin() {

        this.docIdsForCheckin.forEach(docId  => {
            let empId = this.employee.id;
            if(empId!=null){
                this.docsForCheckin.push(this.docMetaData(docId, empId));
            }

        });

        
    }

	
	download(docId,docFileName){

		if(docId!=null&&docId!=""&&docId!=undefined){
			this.commonService.downloadDocument(docId,docFileName);
	} 	
}

CheckIn(){
	
	if(this.docsForCheckin[0]!=null){
		this._service.checkIn(this.docsForCheckin).subscribe(x=>{
		},error=>{
			console.log(error);
		 });
	}
	
}

}