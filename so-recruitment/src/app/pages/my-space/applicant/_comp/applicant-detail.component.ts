import { Component, OnInit } from '@angular/core';
import { NG_VALIDATORS, Validator, Validators, AbstractControl, ValidatorFn } from '@angular/forms';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Applicant,ApplicantAcademicAcheiv,ApplicantEducationalInfo,ApplicantEmergencyContDetails,ApplicantFamilyInfo,ApplicantLanguagesKnown,ApplicantPreviousEmploymentDetails} from '../../vo/applicant';
import { ApplicantService } from '../applicant.service';
import { DateParserService } from '../../../../shared/_services/date-parser.service';
import { Headers, Http, RequestOptions, Response, URLSearchParams,ResponseContentType } from "@angular/http";
import { Observable } from 'rxjs/Observable';
import { UserService } from '../../../../auth/_services';
import{environment} from '../../.././../../environments/environment';
import { saveAs } from 'file-saver';

@Component({
	selector: 'applicant-detail',
	templateUrl: './applicant-detail.component.html'
})
export class ApplicantDetailComponent implements OnInit {

	applicant: Applicant;
	isDateModified: boolean = false;
	isEdit: boolean = false;
	allApplicants: any;
	blood:any=[];
	addScreen: boolean = true;
	flowType = '';
	
	languagesArray: Array<ApplicantLanguagesKnown>;
		
	msg: any;
	isSingle: boolean = false;
	grades: any = [];
	designations: any = [];

	hideOfficial:boolean=false;

	attendanceCodes=[];

	academicCount = 1;

	eduCount = 1;

	bankcount = 1;
	prevcount = 1;
	acccount = 1;
	count = 1;
	children = [];
	languageCount = 1;
	documentApiUrl: string = environment.documentApiUrl;
	familyCount = 1;

	emp = (text$: Observable<string>) =>text$.debounceTime(200).map(term => term === '' ? []
        : this.allApplicants.filter(v => v.empName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10));
	formatter = (x: {empName: string}) => x.empName;

	constructor(

		private activatedRoute: ActivatedRoute,
		private router: Router,
		private _service: ApplicantService,
		private _userService: UserService,
		private http:Http,

		private _dateParser: DateParserService

	) { }
	ngOnInit() {
	    // this.applicant= new Applicant();
		let currentUser=this._userService.getCurrentUser();
		console.log(currentUser.applicantId)
		
		// console.log(currentUser.appToken)
		// console.log(currentUser.userName);
		// if(currentUser.applicantId==applicant)
	
		// if(currentUser){
		// 	this._service.getApplicants().subscribe(x=> {
		// 		this.allApplicants=x.filter((obj)=> {
		// 			console.log(currentUser)
		// 			return obj.id != currentUser.applicantId;
		// 		});
		// 	});
		// }

		

		// this._service.getConfig().subscribe(x=>{
		// 	x.forEach(x=>{ 
		// 			this.attendanceCodes.push(x);
		// 	})
		// });

		// this.activatedRoute.params.switchMap((params: Params) => this._service.getApplicantById(currentUser.applicantId))
		// 	.subscribe( x => {
		// 		console.log(x)
		// 		this.objModify(x);			
		// 	});
		// if (this.activatedRoute.params['_value']['id']) {
		// 	this.isEdit = true;
			
			
		// }


		// this.activatedRoute.queryParams
		// .subscribe(params=>{
		// 	this.flowType = params.flowType;
		// 	this.addScreen = false;
			// if(params.flowType=="My Profile"){
			// 	this.addScreen = false;
			// 	this.hideOfficial=true;
			// }else if(params.flowType=="Edit Applicant"){
			// 	this.addScreen = false;
			// 	this.hideOfficial=false;
			// }else{
			// 	//add applicant
			// 	this.addScreen = true;
			// 	this.hideOfficial=false;
			// }

			if(currentUser.applicantId!=undefined){

		this.applicant = new Applicant();
		this.applicant.applicantName='';
		this.applicant.name='';
	   this.applicant.languages = [new ApplicantLanguagesKnown];
	   this.applicant.academicAcheiv = [new ApplicantAcademicAcheiv];
	   this.applicant.educationalInfo = [new ApplicantEducationalInfo];
	   this.applicant.familyInfo = [new ApplicantFamilyInfo];
	   this.applicant.previousEmployDetails = [new ApplicantPreviousEmploymentDetails];
				this._service.getApplicantById(currentUser.applicantId).subscribe(x=>{
					this.applicant=x;
					// console.log(this.applicant);
					// this.applicant.flowType=params.flowType;
					if(this.applicant.maritalStatus=='N'){
						this.valueIsSingle();
						}
					this.objBefArr(this.applicant);
					this.objModify(this.applicant);
				});
			}
		 
		// })
		
		

		
		this._service.getBlood().subscribe(x=>{
			this.blood=x;
		})
		
			
	}

	objModify(applicant:Applicant){
		this.applicant.vDob=this.frameDate(this.applicant.dob);
		this.applicant.vNextIncrRemun=this.frameDate(this.applicant.nextIncrRemun);

		this.applicant.previousEmployDetails.forEach(x=>{
			x.vFromDt=this.frameDate(x.fromDt);
			x.vToDt=this.frameDate(x.toDt);
		});

		this.applicant.academicAcheiv.forEach(x=>{
			x.vFromDt=this.frameDate(x.fromDt);
			x.vToDt=this.frameDate(x.toDt);
		});
		
	}

	selectedEmp: any;
	selEmp(){
		if(this.selectedEmp && this.selectedEmp.id) {
			this.router.navigateByUrl('/my-space/attendance/direct/'+this.selectedEmp.id);
		}
	}


	addEduRows() {
		this.eduCount++;
		let ei = new ApplicantEducationalInfo();
		this.applicant.educationalInfo.push(ei);
	}
	delEduRow(i) {

		this.applicant.educationalInfo.splice(i, 1);
	}
	addAccRows() {
		this.acccount++;
		let ac = new ApplicantAcademicAcheiv();
		this.applicant.academicAcheiv.push(ac);
	}
	delAccRow(i) {

		this.applicant.academicAcheiv.splice(i, 1);
	}
	addprevRows() {
		this.prevcount++;
		let pe = new ApplicantPreviousEmploymentDetails();
		this.applicant.previousEmployDetails.push(pe);
	}
	delprevRow(i) {

		this.applicant.previousEmployDetails.splice(i, 1);
	}
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


	childrenCount($e) {
		this.children = [];
		for (let i = 0; i < $e; i++) {
			this.children.push(i);
		}
	}

	addLanguageRows() {
		this.languageCount++;
		let ts = new ApplicantLanguagesKnown();
		this.applicant.languages.push(ts);

	}

	delLanguageRow(i) {
		this.applicant.languages.splice(i, 1);
	}
	addFamilyRows() {
		this.familyCount++;
		let fi = new ApplicantFamilyInfo();
		this.applicant.familyInfo.push(fi);
	}
	delFamilyRow(i) {

		this.applicant.familyInfo.splice(i, 1);
	}

	saveApplicant() {


		this.applicant.relInPodhigai =(this.applicant.relInPodhigai?"Y":"N");
		if(this.applicant.languages.length)
		this.applicant.languages.forEach(language => {
			language.langRead = (language.langRead ? "Y" : "N");
			language.langSpeak = (language.langSpeak ? "Y" : "N");
			language.langWrite = (language.langWrite ? "Y" : "N");
		});

		console.log("update applicant");
		console.log(this.applicant);
		this._service.updateApplicant(this.applicant.id,this.applicant).subscribe(x => {

			this.msg = { type: 'success', text: "Updated successfully" }
			this.router.navigateByUrl("/index");
		}, error => {
			this.msg = { type: 'danger', text: "Error in Updation" }
			this.router.navigateByUrl("/index");
		});		
	}
	

	objBefArr(x) {


		this.applicant.languages = this.applicant.languages.length ? this.applicant.languages : [new ApplicantLanguagesKnown()];
		this.languageCount = this.applicant.languages.length ? this.applicant.languages.length : 1;
		this.applicant.familyInfo = this.applicant.familyInfo.length ? this.applicant.familyInfo : [new ApplicantFamilyInfo()];
		this.familyCount = this.applicant.familyInfo.length ? this.applicant.familyInfo.length : 1;

		this.applicant.educationalInfo = this.applicant.educationalInfo.length ? this.applicant.educationalInfo : [new ApplicantEducationalInfo()];
		this.eduCount = this.applicant.educationalInfo.length ? this.applicant.educationalInfo.length : 1;


		this.applicant.academicAcheiv = this.applicant.academicAcheiv.length ? this.applicant.academicAcheiv : [new ApplicantAcademicAcheiv()];
		this.academicCount = this.applicant.academicAcheiv.length ? this.applicant.academicAcheiv.length : 1;


		this.applicant.previousEmployDetails = this.applicant.previousEmployDetails.length ? this.applicant.previousEmployDetails : [new ApplicantPreviousEmploymentDetails()];
		this.prevcount = this.applicant.previousEmployDetails.length ? this.applicant.previousEmployDetails.length : 1;


		
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
	onSameCurrentAddress($event) {
		this.applicant.permAddress = this.applicant.curAddress;
	console.log(this.applicant.permAddress);
		// this.job.jobName = $event.item.jobTypeName;

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
	 
		// console.log(jobAsset)
		// console.log(jobAssetDocs)
	  
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
			   
						   
								// jobAssetDocs.docId = data[0].docId;
						   this.applicant.docId=data[0].docId;
					 console.log(this.applicant.docId);

					

					},

					error => console.log(error)
				)
		}
		// console.log(jobAsset)
	}
	applicationForm(){
		this._service.getApplicantFormPdfById(this.applicant.id.toString()).subscribe(x=>{
			this.router.navigateByUrl("/index");	
		});
	}
	download(docId){

		if(docId!=null&&docId!=""&&docId!=undefined){

			this._service.getDocument(docId).subscribe(x=>{
				let content_type = x.headers.get('Content-type');
        let x_filename = "Applicant "+this.applicant.name+".pdf";
        console.log(content_type)
        saveAs(x.blob(), x_filename);
			})

	} 
	}
}