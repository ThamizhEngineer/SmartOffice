import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { VacancyOpeningService } from '../vacancy-opening.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { VacancyRequest } from '../../../vo/vacancy-request';
import { VacancyDescription,VacancyDescriptionSkill } from '../../../vo/vacancy-description'
import { Observable } from 'rxjs';

import { WebSiteApplicant } from '../../../vo/web-site-applicant';
import { CommonService } from '../../../../shared/common/common.service';
import { FormGroup, FormBuilder, Validators,FormControl, Form } from '@angular/forms';


@Component({
    selector: '',
    templateUrl: 'vacancy-opening.component.html'
})

export class VacancyComponent implements OnInit {
   
    @ViewChild('apply') apply: TemplateRef<any>;
    @ViewChild('createApp') createApp: TemplateRef<any>;

    newdate:string;
    vacancy:any=[];
    vcCode:any;
    vcId:string;
    modalReference:any = null;
    vacancyDesc:VacancyDescription;
    applicant:WebSiteApplicant;
 
    myGroup: FormGroup;
    fresherGroup:FormGroup;
    experiencGroup:FormGroup;

    page :number = 1;
    pageSize :number = 4;

    fName:FormControl;
    lName:FormControl;
    dob:FormControl;
    email:FormControl;
    mNumber:FormControl;
    cLocation:FormControl;
    cLetter:FormControl;
    oCollege:FormControl;
    oCourse:FormControl;
    oDegree:FormControl;
    //Fresher
    instution:FormControl;
    course:FormControl;
    degree:FormControl;
    pYear:FormControl;
    arrears:FormControl;
    marks:FormControl;

    //Experienced
    cCompany:FormControl;
    cDesignation:FormControl;
    cExperience:FormControl;
    cSalary:FormControl;
    colleges:any;
    degrees:any;
    courses:any;
    // cno:FormControl;

    constructor(
        private service:VacancyOpeningService,
        private modalService: NgbModal,
        private commonService: CommonService,

    ) { }

    ngOnInit() { 
        this.applicant = new WebSiteApplicant();
        this.newdate = new Date().toJSON().substr(0,10);
        console.log(this.newdate);
        
        this.vacancyDesc = new VacancyDescription();
        this.service.getVacancyOpenings().subscribe(x=>{
            this.vacancy=x;
            console.log(this.vacancy)
        })
		this.validate();
        this.createForm();
        this.service.getIncidents().subscribe(x=>{
            this.colleges=x;
            console.log(this.colleges)
        })
        
        this.service.getColleges().subscribe(x=>{
            this.colleges=x;
            console.log(this.colleges)
        })
        this.service.getDegrees().subscribe(x=>{
            this.degrees=x;
        })
        this.service.getCourses().subscribe(x=>{
            this.courses=x;
        })
    }

    saveMsg: any;
    
    application(){
        this.modalReference.close(this.apply);
		this.modalReference = this.modalService.open(this.apply, {size: 'lg'});		
    }

    discriptionView(id,code,vcId){
        this.vcId=vcId;
        this.vcCode=code;
        this.service.getVacancyDescriptions().subscribe(x=>{

            for(let list of x){
                if(list.id==id){
                    this.vacancyDesc=list;
                    console.log(this.vacancyDesc);
                }
            }

        })
        this.modalReference = this.modalService.open(this.createApp, {size: 'lg'});		   
    }

    uploadResume(event, docTypeCode) {
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            
			 this.commonService.uploadDocument(fileList[0], docTypeCode).map((response: Response) => response)
                .catch(error => Observable.throw(error)).subscribe(
                    data => {
                        this.applicant.resumeDocId = data[0].docId;
                    },
                    error => console.log(error)
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
        console.log(this.applicant);
        this.service.addWebSiteApplicant(this.applicant).subscribe(x=>{
            this.modalReference.close();
            this.saveMsg = {'type': 'success', 'text': x.eligibleRemarks}
        })
    }
}

    get getBehavioralSkills(){
        if(this.vacancyDesc && this.vacancyDesc.vacancyDescriptionSkills){
            return this.vacancyDesc.vacancyDescriptionSkills.filter( x => x.type == 'BEHAVIORAL');
        }
        else
           return [];
    }
    
    get getFunctionalSkills(){
       if(this.vacancyDesc && this.vacancyDesc.vacancyDescriptionSkills){
           return this.vacancyDesc.vacancyDescriptionSkills.filter( x => x.type == 'FUNCTIONAL');
       }
       else
          return [];
   }
   validate(){
    this.fName = new FormControl('', [Validators.required]);
    this.lName = new FormControl('', [Validators.required]);
    this.dob = new FormControl('', [Validators.required]);
    this.email = new FormControl('', [Validators.required]);
    this.mNumber = new FormControl('', [Validators.required,Validators.pattern('[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}')]);
    this.cLocation = new FormControl('', [Validators.required]);
    this.cLetter = new FormControl('', [Validators.required]);

    this.instution = new FormControl('', [Validators.required]);
    this.course = new FormControl('', [Validators.required]);
    this.degree = new FormControl('', [Validators.required]);
    this.pYear = new FormControl('', [Validators.required]);
    this.arrears = new FormControl('', [Validators.required]);
    this.marks = new FormControl('', [Validators.required]);

    this.cCompany = new FormControl('', [Validators.required]);
    this.cDesignation = new FormControl('', [Validators.required]);
    this.cExperience = new FormControl('', [Validators.required]);
    this.cSalary = new FormControl('', [Validators.required]); 
  
}
createForm(){
    this.myGroup = new FormGroup({
        fName:this.fName,
        lName:this.lName,
        dob:this.dob,
        email:this.email,
        mNumber:this.mNumber,
        cLocation:this.cLocation,
        cLetter:this.cLetter
    });		
    this.fresherGroup = new FormGroup({
        instution:this.instution,
        course:this.course,
        degree:this.degree,
        pYear:this.pYear,
        arrears:this.arrears,
        marks:this.marks
    });	   

    this.experiencGroup = new FormGroup({
        cCompany:this.cCompany,
        cDesignation:this.cDesignation,
        cExperience:this.cExperience,
        cSalary:this.cSalary
    });	 
}
    back(){
        this.modalReference.close();
    }
}