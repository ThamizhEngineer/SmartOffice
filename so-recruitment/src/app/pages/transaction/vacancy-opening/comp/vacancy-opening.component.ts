import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { VacancyOpeningService } from '../vacancy-opening.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { VacancyDescription } from '../vo/vacancy-description'
import { VacancyRequest } from '../vo/vacancy-request';
import { Observable } from 'rxjs';
import { UserService } from '../../../../auth/_services/user.service';
import { User } from '../../../../auth/_models/user';
import { WebSiteApplicant } from '../vo/web-site-applicant';
import { CommonService } from '../../../../shared/common/common.service';
import { FormGroup, FormBuilder, Validators,FormControl } from '@angular/forms';


@Component({
    selector: '',
    templateUrl: 'vacancy-opening.component.html'
})

export class VacancyComponent implements OnInit {
   
    @ViewChild('apply') apply: TemplateRef<any>;
    @ViewChild('createApp') createApp: TemplateRef<any>;

    newdate:string;
    job:any=[];
    jrCode:any;
    vacancyRequest:VacancyRequest;
    vcId:string;
    modalReference:any = null;
    jobDesc:VacancyDescription;
    applicant:WebSiteApplicant;
 
    myGroup: FormGroup;
    fresherGroup:FormGroup;
    experiencGroup:FormGroup;

    page :number = 1;
    pageSize :number = 4;

    fName:FormControl;
    lName:FormControl;
    dob:FormControl;
    gender:FormControl;
    email:FormControl;
    mNumber:FormControl;
    cLocation:FormControl;
    cLetter:FormControl;

    //Fresher
    collegeName:FormControl;
    // instution:FormControl;
    course:FormControl;
    degree:FormControl;
    passedOut:FormControl;
    arrears:FormControl;
    mark:FormControl;
    //Experienced
    currCompany:FormControl;
    designation:FormControl;
    currExperience:FormControl;
    currSalary:FormControl;

    dummyApplicant:any;

    user: User

    wsApplicants:any=[];

    colleges:any;
    degrees:any;
    courses:any;

    saveMsg: any;
    errorMsg:any;

    docErrorMsg:any;

    constructor(
        private service:VacancyOpeningService,
        private modalService: NgbModal,
        private userService: UserService,
        private commonService: CommonService

    ) { }

    ngOnInit() { 
        this.applicant = new WebSiteApplicant();
        this.user = new User();
        this.user = this.userService.getCurrentUser();
        this.newdate = new Date().toJSON().substr(0,10);
        // console.log(this.newdate);
        
        this.jobDesc = new VacancyDescription();
        this.service.getJobOpenings().subscribe(x=>{
            this.job=x;
            // console.log(this.job)
        })
		this.validate();
        this.createForm();

        this.service.getColleges().subscribe(x=>{
            this.colleges=x;
            // console.log(this.colleges)
        })
        this.service.getDegrees().subscribe(x=>{
            this.degrees=x;
        })
        this.service.getCourses().subscribe(x=>{
            this.courses=x;
        })
    }

    get EduName(){
		return this.colleges.filter(x=>x.abbreviation!='SCHOOL');
	}

    
    
    application(){
       this.dummyApplicant=null;
        let isPresent:string='N';
        this.service.getWebSiteApplicant(this.user.emailId,'',this.user.mobileNumber).subscribe(x=>{
            this.wsApplicants=x;    
            console.log(this.wsApplicants);      
            for(let element of this.wsApplicants){                              
               if(element.vcId==this.vcId){                   
                   isPresent='Y'
                   this.applicant=element;
                //    console.log(this.applicant);                       
               }else{
                   element.id=null;
                this.dummyApplicant=element;                
               }
            }
            if(isPresent=='N'){
                if(this.dummyApplicant!=null){
                    this.applicant=this.dummyApplicant;
                }                
            }
        });
        console.log(this.wsApplicants);
       
        this.modalReference.close(this.apply);
		this.modalReference = this.modalService.open(this.apply, {size: 'lg'});		
    }

    discriptionView(id,vacancyRequest,vcId){
       this.vcId=vcId;
       this.vacancyRequest=new VacancyRequest();
       this.vacancyRequest=vacancyRequest
        this.service.getJobDescriptions(id).subscribe(x=>{
                    this.jobDesc=x;                                
            console.log(this.jobDesc);
        })
        this.modalReference = this.modalService.open(this.createApp, {size: 'lg'});		   
    }

    updateApplicant(){
     
        if(this.myGroup.valid){
            this.applicant.vcId=this.vcId;
            this.applicant.applicantId=this.user.applicantId;            
            this.service.updateWebSiteApplicant(this.applicant).subscribe(x=>{
                this.modalReference.close();
                this.saveMsg = {'type': 'success', 'text': 'Updated Successfully '}
            },error=>{
                this.errorMsg = {'type': 'danger', 'text': 'Server Error'}
            });
        }
        
    }

    uploadResume(event, docTypeCode) {
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            
			 this.commonService.uploadDocument(fileList[0], docTypeCode).map((response: Response) => response)
                .catch(error => Observable.throw(error)).subscribe(
                    data => {
                        this.applicant.resumeDocId = data[0].docId;
                    },
                    error =>{
                        this.docErrorMsg = {'type': 'danger', 'text': 'Please Upload Your Resume in Pdf or DOC'}  
                        console.log(error)
                    } 
                )
        }
    }

    download(docId,docFileName){

		if(docId!=null&&docId!=""&&docId!=undefined){
			this.commonService.downloadDocument(docId,docFileName);
	} 	
}

    create(){
        if(this.myGroup.valid){
        this.applicant.vcId=this.vcId;
        this.applicant.applicantId=this.user.applicantId;
        this.applicant.isValidate='N';
        console.log(this.applicant);
        this.service.addWebSiteApplicant(this.applicant).subscribe(x=>{
            this.modalReference.close();
            this.saveMsg = {'type': 'success', 'text': 'Applied successfully '}
        },error=>{
            this.errorMsg = {'type': 'danger', 'text': 'You have Already Applied for this Job Request'}
        })
    }
}

    get getBehavioralSkills(){
        if(this.jobDesc && this.jobDesc.vacancyDescriptionSkills){
            return this.jobDesc.vacancyDescriptionSkills.filter( x => x.type == 'BEHAVIORAL');
        }
        else
           return [];
    }
    
    get getFunctionalSkills(){
       if(this.jobDesc && this.jobDesc.vacancyDescriptionSkills){
           return this.jobDesc.vacancyDescriptionSkills.filter( x => x.type == 'FUNCTIONAL');
       }
       else
          return [];
   }
   validate(){
    this.fName = new FormControl('', [Validators.required]);
    this.lName = new FormControl('', [Validators.required]);
    this.dob = new FormControl('', [Validators.required]);
    this.gender= new FormControl('', [Validators.required]);
    this.email = new FormControl('', [Validators.required]);
    this.mNumber = new FormControl('', [Validators.required,Validators.pattern('[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}')]);
    this.cLocation = new FormControl('', [Validators.required]);
    this.cLetter = new FormControl('', [Validators.required]);
    
    this.degree = new FormControl('', [Validators.required]);
    this.collegeName = new FormControl('', [Validators.required]);
    this.course = new FormControl('', [Validators.required]);
    this.passedOut= new FormControl('', [Validators.required,Validators.pattern('[0-9]{4}')]);
    this.arrears = new FormControl('', [Validators.required,Validators.pattern('[0-9]{1,2}')]);
    this.mark = new FormControl('', [Validators.required,Validators.pattern('[0-9]{2,3}')]);

    this.currCompany = new FormControl('', [Validators.required]);
    this.designation = new FormControl('', [Validators.required]);
    this.currExperience = new FormControl('', [Validators.required,Validators.pattern('[0-9]{1,2}')]);
    this.currSalary = new FormControl('', [Validators.required,Validators.pattern('[0-9]{4,8}')]);
}
createForm(){
    this.myGroup = new FormGroup({
        fName:this.fName,
        lName:this.lName,
        dob:this.dob,
        gender:this.gender,
        email:this.email,
        mNumber:this.mNumber,
        cLocation:this.cLocation,
        cLetter:this.cLetter
    });		

    this.fresherGroup = new FormGroup({
        degree:this.degree,
        collegeName:this.collegeName,
        course:this.course,
        passedOut:this.passedOut,
        arrears:this.arrears,
        mark:this.mark
    })

    this.experiencGroup = new FormGroup({
        currCompany:this.currCompany,
        designation:this.designation,
        currExperience:this.currExperience,
        currSalary:this.currSalary
    })

}


    back(){
        this.modalReference.close();
    }
}