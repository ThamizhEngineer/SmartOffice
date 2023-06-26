import { Component, Input,OnInit, ViewChild, TemplateRef,Output, EventEmitter } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { IncidentApplicant, Incident } from '../../pages/vo/incident';
import { TypeScriptEmitter } from '@angular/compiler';
import { ApplicantService } from '../../pages/operation/applicant/applicant.service';
import { Applicant } from '../../pages/vo/applicant';
import { FormGroup, FormBuilder, Validators,FormControl } from '@angular/forms';
import { CommonService } from '../common/common.service';



@Component({
    selector: 'applicant-detail-edit',
  
    templateUrl: './applicant-detail-edit.component.html',
    
}) export class ApplicantDetailEditComponent implements OnInit {
    @Input() childMessage: IncidentApplicant;
    @ViewChild('apply') apply: TemplateRef<any>;
    @ViewChild('viewApply') viewApply: TemplateRef<any>; 
    modalReference:any = null;
    incidentApplicant:IncidentApplicant;
    incident:Incident
    incidents:any=[]
    colleges:any=[];
    degrees:any=[];
    courses:any=[];
    applicant:Applicant;
    isApplicantDetails:boolean=true;
    isviewApplicantDetails:boolean=true;
    textBoxDisabled = false;
    exampleFlag=true; 
    myGroup: FormGroup;   

    fName:FormControl;
    lName:FormControl;
    gender:FormControl;
    dob:FormControl;
    email:FormControl;
    mNumber:FormControl;


    ngOnInit(){
      this.incident = new Incident();
      this.applicant= new Applicant();
      this.validate();
      this.createForm();
      
   
    }
    constructor(private modalService: NgbModal,
      private commonService: CommonService,
      private applicantService:ApplicantService){

    }

    loadMasterData(){
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
    }
   
  viewApplicantDetails() {
    this.loadMasterData();
    console.log(this.childMessage);
    if (this.childMessage.expType == null) {
      this.childMessage.expType = 'Fresher'
    }
    this.modalReference = this.modalService.open(this.viewApply, { size: 'lg' });
    console.log("In chld", this.childMessage);
  }

  applicantDetails() {
    this.loadMasterData();
    console.log(this.childMessage);
    if (this.childMessage.expType == null) {
      this.childMessage.expType = 'Fresher'
    }
    this.modalReference = this.modalService.open(this.apply, { size: 'lg' });
    console.log("In chld", this.childMessage);
  } 
 
localClick(x) {
  if(x==1){
    this.exampleFlag=true;
  }
  else{
    this.exampleFlag=false;
  }
}
validate(){
    this.fName = new FormControl('', [Validators.required]);
    this.lName = new FormControl('', [Validators.required]);
    this.dob = new FormControl('', [Validators.required]);
    this.email = new FormControl('', [Validators.required]);
    this.gender = new FormControl('', [Validators.required]);
    this.mNumber = new FormControl('', [Validators.required,Validators.pattern('[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}')]);
}
createForm(){
  this.myGroup = new FormGroup({
      fName:this.fName,
      lName:this.lName,
      dob:this.dob,
      gender:this.gender,
      email:this.email,
      mNumber:this.mNumber
  });	
}

}