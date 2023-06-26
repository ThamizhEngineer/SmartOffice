import { Component, OnInit, Input } from '@angular/core';
import { IncidentService } from '../incident.service';
import { Incident, IncidentApplicant } from '../../../vo/incident';
import { ActivatedRoute, Params } from '@angular/router';
import { Router } from '@angular/router';
import { Employee } from '../../../vo/employee';
import { Observable } from 'rxjs/Observable';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { CommonService } from '../../../../shared/common/common.service';
import { ApplicantService } from '../../../operation/applicant/applicant.service';
import { Applicant } from '../../../vo/applicant';
import { WebApplicant } from '../../vo/web-applicant';

import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { saveAs } from 'file-saver';

@Component({
    selector: 'import-applicant',
    templateUrl: 'import-applicant.component.html',
   
})

export class ImportApplicantComponent implements OnInit {
    employee: Employee;
    ngAlert:boolean;
    ngAlertMessage:string="";
    incident: Incident;
    applicantId:string;

    parentMessage:IncidentApplicant
    errorMessage = "";
    webApplications= [];
    webApplicant:WebApplicant;
    secondInterviewerId: any;
    secondInterviewerName:string=null;
    allEmployees: any=[];
    selectedWebApplicant: boolean = false;
    incidentApplicants: Array<IncidentApplicant>;
    incidentApplicant:IncidentApplicant;
    isDisabled: boolean=false;
    tempArrayForIds:string[]=[];
    isStillPendingApproval:boolean=false;
    incidents:any[];
    colleges:any;
    degrees:any;
    courses:any;
    applicant:Applicant;
    applicants:Array<Applicant>;
    view:string;
    pgNumber:number;
    paramId:any;
    institute: any;
    degreeName: any;
    passoutYear: any;
    marks: any;
    historyOfArrears: any;
    isEligibleForTest: any;
    rows: Array<IncidentApplicant>;
    course: any;    
    employees:any=[];
    memp:any;
    page :number = 1;
    pageSize :number = 5;

    page1 :number = 1;
    page1Size :number = 5;
    msg:any;
    errorMsg:any;
    cduplicateName:any=[]
    duplicateName:any=[]
    empCategory=[{name:'Regular'},{name:'Middle-Management'},{name:'Senior-Management'}]
    dropdownSettings= {};
    selectedEmpCategory=[];
    
    
    constructor(private service: IncidentService, 
        private activedRouter: ActivatedRoute, 
        private commonService: CommonService,
        private applicantService:ApplicantService,
        private router: Router) { }

    ngOnInit() {        
        this.incidentApplicants = [];
        this.webApplications = [new WebApplicant];
        this.incidentApplicant = new IncidentApplicant();
        
        this.incident = new Incident();
        this.applicant= new Applicant();
        this.applicants= new Array<Applicant>();
        this.webApplicant = new WebApplicant();
        this.rows= new Array<IncidentApplicant>();

        this.dropdownSettings = {
            singleSelection: false,
            idField: 'name',
            textField: 'name',
            itemsShowLimit: 2,
            allowSearchFilter: true
          };
      

        this.applicantService.getIncidents().subscribe(x=>{
            this.incidents=x;            
        })
        
        this.applicantService.getColleges().subscribe(x=>{
            this.colleges=x;            
        })
        this.applicantService.getDegrees().subscribe(x=>{
            this.degrees=x;
        })
        this.applicantService.getCourses().subscribe(x=>{
            this.courses=x;
        })
            

       
        if (this.activedRouter.params['_value']['id']) {
            this.activedRouter.params.subscribe(params=>{
                this.pgNumber=params.page;
                this.view=params.view;
                this.paramId=params.id;
            });       
        }

        this.service.getIncidentById(this.paramId).subscribe(x => {
            this.incident = x;
           
            if (this.incident.incidentApplicants) {
                this.incidentApplicants = this.incident.incidentApplicants;
                if(this.incidentApplicants.length<1==true){
                    this.addApplicant();
                }
            this.incident.incidentApplicants.forEach(y=>{
                y.isviewApplicantDetails=false;
                y.isApplicantDetails=true;
            })
            this.checkEmp();
            }
            this.service.getWebAppByVCId(this.incident.vcId).subscribe(
                (x) => {
                this.webApplications = x;
                this.webApplications.forEach(element => {
                 if(element.isValidate=='Y'){
                     element.select=null;
                 }   
                });
                this.checkda();   
            },
            (error)=>{
                this.errorMessage = error.message; 
            });
        });

        this.service.getAllEmployee().subscribe(x=>{
            this.allEmployees=x;
            this.employees=x;    
            this.checkEmp();
                 
        },
        (error)=>{
            this.errorMessage = error.message;             
        }
        )
          
    }  

    importEmpByCategory(){
        console.log(this.selectedEmpCategory);

        this.selectedEmpCategory.forEach(category => {
            this.employees.forEach(emp => {
                if(category==emp.empCategory){
                    this.importEmp(emp);
                }
            });
        });
    }
    checkEmp(){        
        this.employees.forEach(emp => {
           this.incidentApplicants.forEach(ia => {              
              if(emp.id==ia.employeeId){
                  emp.isExiste=true;
              } 
           }); 
        });    
    }

    importEmp(employee){             
        let ac = new IncidentApplicant();          
        this.service.getEmployeeById(employee.id).subscribe(x=>{                                  
            ac.firstName=x.firstName;
            ac.lastName=x.lastName;
            ac.email=x.emailId;
            ac.mobileNumber=x.contactMobileNo;
            ac.dob=x.dob;
            ac.employeeId=String(x.id);
            // ac.incidentId=String(this.incident.id);
            ac.isApplicantDetails=true;
            ac.isDocUpload=true;       
        })                         
        this.incidentApplicants.push(ac);   
        for (let index = 0; index < this.employees.length; index++) {           
            if(this.employees[index].id==employee.id){
                this.employees[index].isExiste=true;
            }
        }   
    }

    checkda(){
        this.incidentApplicants.forEach(iAElement => {
            if (this.webApplications) {
                this.webApplications.forEach(wbElement => {
                    var check = iAElement.email==wbElement.email;
                    if (check==true) {
                        wbElement.imported='Yes';
                        wbElement.select="true";
                    }
                });
            }
        });
    }
    
    secondInterviewer($event){        
        this.secondInterviewerId = $event.id;
        this.secondInterviewerName= $event.empName;
    }

    projectSelected1($event){
		this.employee.n2EmpId = $event.item.id;
		this.employee.reviewManagerName= $event.item.empName;
	}	

    //  Add or remove incident Applicant

    addApplicant() { 
        let ac = new Applicant();    
        ac.collegeName=this.applicant.collegeName;
        ac.degreeName=this.applicant.degreeName;
        ac.courseName=this.applicant.courseName;
        ac.passedOut=this.applicant.passedOut;        
        this.applicants.push(ac);

        let app = new IncidentApplicant()
        app.isDocUpload=true;
        app.institute=ac.collegeName;
        app.degreeName=ac.degreeName;
        app.course=ac.courseName;
        app.passoutYear=ac.passedOut;
        app.isApplicantDetails=true;
        this.incidentApplicants.push(app);        
     
    }

   

    uploadResume(index, event, docTypeCode) {
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {            
			 this.commonService.uploadDocument(fileList[0], docTypeCode).map((response: Response) => response)
                .catch(error => Observable.throw(error))
                .subscribe(
                    data => {
                        console.log(data)
                        this.incidentApplicants[index].resumeDocId = data[0].docId;                                            
                        console.log(this.incidentApplicants)
                    },
                    error => console.log(error)
                )
        }
    }

    modifyResume(index, event, docTypeCode,docId) {

        let fileList: FileList = event.target.files;
        if (docId!=null || docId!=undefined) {
            if (fileList.length > 0) {            
                 this.commonService.UpdateDocument(fileList[0], docTypeCode, docId).map((response: Response) => response)
                    .catch(error => Observable.throw(error))
                    .subscribe(
                        data => {
                            this.incidentApplicants[index].resumeDocId = data[0].docId;                                            
                        },
                        error => console.log(error)
                    )
            }
        }
        else{
            if (fileList.length > 0) {            
                 this.commonService.uploadDocument(fileList[0], docTypeCode).map((response: Response) => response)
                    .catch(error => Observable.throw(error))
                    .subscribe(
                        data => {
                            this.incidentApplicants[index].resumeDocId = data[0].docId;                                            
                        },
                        error => console.log(error)
                    )
            }

        }
    }

    /*
    fileChange(event, type: any) {
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            let file: File = fileList[0];
            this.commonService.uploadDocument(file, type)
                .subscribe(
                    data => {
                        this.clientPurchaseOrder.docId = data[0].docId;
                    },
                    error => console.log(error))
        }
    }
    */
 

    removeApplicant(data,index) {
        if(data.id!=null){
            var idString:string=(data.id).toString();
            this.tempArrayForIds.push(idString);
            this.incident.applicantIdsToDelete=this.tempArrayForIds;
            this.incidentApplicants.splice(index, 1);
        }else{
            this.incidentApplicants.splice(index, 1);
        }
        
    }

    sendNotification(){
        this.service.sendNotificationToApplicant(this.incident.id,this.incident).subscribe(x=>{
            this.msg= { type: 'success', text: " email Send Successfully" } 
        })
    }

    upload(event,docTypeCode:any){
        let fileList: FileList = event.target.files;        
        if (fileList.length > 0) {  
            let files: File = fileList[0];          
			 this.commonService.uploadApplicant(files, docTypeCode,this.incident.id).map((response: Response) => response)
                .catch(error => Observable.throw(error))
                .subscribe(
                    data => {                      
                        this.ngOnInit();                                        
                    },
                    error => console.log(error)
                )
        }
      }


    checkBoxChecked(data, index) {
        this.ngAlert=false;
        if (data.select == true) {
           
                for (let incApp of this.incidentApplicants) {
                    if (incApp.email == data.email) {
                        this.ngAlert=true;
                        this.ngAlertMessage="Data Already Exists!";
                        break; 
                    }
                   
                }
                if(this.ngAlert==false){
                    this.addIncidentApplicant(data); 
                }
                
                        

        } 
    }
  
    addIncidentApplicant(data):IncidentApplicant[]{
        let incidentApplicant = new IncidentApplicant()
        incidentApplicant.firstName = data.firstName;
        incidentApplicant.lastName = data.lastName;
        incidentApplicant.email = data.email;
        incidentApplicant.mobileNumber = data.mobNum;
        incidentApplicant.resumeDocId = data.resumeDocId;
        incidentApplicant.dob = data.dob;
        incidentApplicant.gender= data.gender;
        incidentApplicant.coverLetter = data.coverLetterNum;
        incidentApplicant.vcId = data.vcId;
        incidentApplicant.institute=data.institute;
        incidentApplicant.course=data.course;
        incidentApplicant.passoutYear=data.passoutYear;
        incidentApplicant.degreeName=data.degreeName;
        incidentApplicant.historyOfArrears=data.historyOfArrears;
        incidentApplicant.marks=data.marks;
        incidentApplicant.currCompany=data.currCompany;
        incidentApplicant.currDesignation=data.currDesignation;
        incidentApplicant.currExperience=data.currExperience;
        incidentApplicant.expType=data.expType;
        incidentApplicant.currLocation=data.currLocation;
        incidentApplicant.currSalary=data.currSalary;
        incidentApplicant.applicantId=data.applicantId;
        incidentApplicant.source = "campus";
        incidentApplicant.isApplicantDetails = true;        
        this.incidentApplicants.push(incidentApplicant);
        return this.incidentApplicants;
    }

    submit() {
        // this.incident.incidentApplicants = this.incidentApplicants;
        this.incident.incidentApplicants=[];
        this.incident.incidentTestTemplates=[];
        this.incident.participatingInstitutes=[];
        this.incident.incidentTests=[];
        this.checkBeforeSubmit();
        if(this.duplicateName.length==0){
            this.incidentApplicants.forEach(element=>{
                if(element.firstName!=null&&element.lastName!=null&&element.email!=null&&element.mobileNumber!=null){
                    this.incident.incidentApplicants.push(element)
                }           
            }); 

            this.service.addOrUpdateincident(this.incident).subscribe(x => {     
                this.router.navigate(['/recruitment/event/'+this.view.toLowerCase(),{page:this.pgNumber}]);
            },
            error => {                       
    this.errorMsg = JSON.parse(error._body);
    this.msg = { type: 'danger', text: this.msg.message }
            })

        }else{
            this.msg= { type: 'danger', text: this.duplicateName+" email are Already Present" }            
        }                  
    }
 
    checkBeforeSubmit(){  
        this.duplicateName=[]; 
        this.cduplicateName=[];     
        this.incidentApplicants.forEach(app => {
            this.cduplicateName.push(app.email);
        });
       this.duplicateName = this.cduplicateName.filter((item, index) => this.cduplicateName.indexOf(item) != index)     
    }

    redirect() {
        this.router.navigate(['/recruitment/event/'+this.view.toLowerCase(),{page:this.pgNumber}]);
    }
    search(){
        this.service.advanceSearch(this.institute,this.course,this.degreeName,this.passoutYear,this.marks,this.historyOfArrears,this.isEligibleForTest).subscribe(x=>{
            // this.incidentApplicants = this.incident.incidentApplicants;

            this.incidentApplicants=x;       
            // x.forEach(element => {
            //     this.incident.incidentApplicants.push(element);
            // });
        });
    }
    reset(){
        this.institute=null;this.course=null;this.degreeName=null;this.passoutYear=null;this.marks=null;this.historyOfArrears=null;this.isEligibleForTest=null;
        // this.search();
    }
}
