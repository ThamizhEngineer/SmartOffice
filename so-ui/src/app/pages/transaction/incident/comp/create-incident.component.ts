import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { IncidentService } from '../incident.service';
import { Incident,IncidentApplicant,ParticipatingInstitute,IncidentTestTemplate } from '../../../vo/incident';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { WebSiteApplicant } from '../../../vo/web-site-applicant';
import { ActivatedRoute } from '@angular/router';
import { TestTemplate,TestTemplateCatagory } from '../../../knowledge-assessment/test-template/vo/TestTemplate';
import { Router } from '@angular/router'
import { Observable } from 'rxjs/Observable';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { FormGroup, FormBuilder, Validators,FormControl } from '@angular/forms';
import { User } from '../../../../auth/_models';
import { UserService } from '../../../../auth/_services';


@Component({
    selector: 'edit',
    templateUrl: 'create-incident.component.html'
})

export class CreateIncideintComponent implements OnInit {
   
    @ViewChild('importEvent') importTrainingEvent: TemplateRef<any>;

    pageSize :number = 10
	  page :number = 1;
    modalReference:any = null;
    incidentTestTemplates:Array<IncidentTestTemplate>;
    id:any;
    webApplication:any=[];
    code:any;
    summary:any;
    testTemplates:[TestTemplate];
    user:User;
    // incident:Incident;
    incident:Incident;
    isRecruitment:boolean;
    allEmployees:any=[];
    view:string;
    hr1:any=[];
    errorMsg:any;
    saveMsg:any;
    incidents:any=[];

    isTraining:boolean;
    isExam:boolean=false;
    isInterview:boolean=false;
    vacancyRequests:any=[];
    masterSelected:boolean = false;
    searchString: string = "";
    isStillPendingApproval:boolean=false;

    isDir:boolean=false;
    commonGroup:FormGroup;
    myGroup: FormGroup;
    myTrainingGroup: FormGroup;
 

    jobId:FormControl;
    hrGroupId:FormControl;
    freshOrExp:FormControl;
    incidentName:FormControl;
    incidentDesc:FormControl;
    noOfDays:FormControl;
    selectInOrTs:FormControl;
    startDate:FormControl;
    endDate:FormControl;
    faculty:FormControl;
    contactNo:FormControl;
    skillsAqd:FormControl;
    pgNumber:number;
    mapLocId:FormControl
    incName:string;
    searchTemplates = (text$: Observable<string>) =>
    text$.pipe(
        debounceTime(200),
        distinctUntilChanged(),
        map(term => (term === '' ? this.testTemplates
            : this.testTemplates.filter(v => v.testTemplateName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
    );
    formatter = (x: { testTemplateName: string }) => { x.testTemplateName };

    constructor(
        private service:IncidentService,
        private modalService: NgbModal,
        private route: Router,
        private userService:UserService,
        private activatedRoute:ActivatedRoute
    ) { }

    ngOnInit() {
      this.user = this.userService.getCurrentUser();
      this.incident = new Incident();
      this.incident.incidentTestTemplates = new Array<IncidentTestTemplate>();    
      this.incidentTestTemplates= new Array<IncidentTestTemplate>();               
      console.log(this.user)
      this.user.userGroupMappings.forEach(element => {
        if(element.isDir=='Y')  {
          this.isDir=true;           
        }
      });
        
        this.activatedRoute.params.subscribe(x=>{          
          this.id=x.id;
          this.pgNumber=x.page;
          if(x.view=='recruitment'){
            this.incident.incidentType='Recruitment';
            this.view='Recruitment';
            this.isTraining=false;
            this.onInit();
          }
          if(x.view=='training'){
            this.incident.incidentType='Training';
            this.view='Training';
            this.isRecruitment=true;
            this.onInit();
          }

          
          this.service.getHrL1().subscribe(x=>{
            this.hr1=x;
          })                   
            this.service.getJobRequests().subscribe(x=>{
                this.vacancyRequests=x;
            })
        })
        this.service.getTestTemplates().subscribe(x=>{
          this.testTemplates=x;
        })
        if( this.incident.incidentTestTemplates.length<1){
          this.addTemplate();
        }
        
        this.service.getAllEmployee().subscribe(x=>{
          this.allEmployees=x;
        })
     
        if(this.id != null){
        this.getbyId(this.id);
        }
       

        if(this.incident.firstInterviewerId==null){
          this.incident.firstInterviewerName=null;
        }
        if(this.incident.secondInterviewerId==null){
          this.incident.secondInterviewerName=null;
        }
        if(this.incident.thirdInterviewerId==null){
          this.incident.thirdInterviewerName=null;
        }
        this.validate();
        this.createForm();
     }

     onInit(){
      this.service.getIncidents().subscribe(x => {
        x.forEach(element => {
         if(element.incidentType==this.view){
             this.incidents.push(element);
         } 
        });                                                                               
     })
     }

     importEvents(){
       this.modalReference = this.modalService.open(this.importTrainingEvent, { size: 'lg' });
     }

     getIncident(incident){
       this.modalReference.close();
       console.log(incident.mapLocId);
       this.incident.incidentName=incident.incidentName
       this.incident.incidentDesc=incident.incidentDesc
       this.incident.locName=incident.locName
       this.incident.mapLocId=incident.mapLocId
      //  this.incident.trStartDt=incident.trStartDt
      //  this.incident.trEndDt=incident.trEndDt
      if(this.view=='Training'){
        this.incident.faculty=incident.faculty
        this.incident.contactNo=incident.contactNo
        this.incident.skillsAqd=incident.skillsAqd
      }
      
      if(this.view=='Recruitment'){
        this.incident.vcId=incident.vcId
        this.incident.handlingGroupId=incident.handlingGroupId
        this.incident.isEntryLevel=incident.isEntryLevel=="Y"?"Y":null
        this.incident.isProfessional=incident.isProfessional=="Y"?"Y":null
        this.incident.hasInterview=incident.hasInterview=="Y"?"Y":null
        this.isInterview=incident.hasInterview=="Y"?true:false
        this.incident.firstInterviewerId=incident.firstInterviewerId;
        this.incident.firstInterviewerName=incident.firstInterviewerName;
        this.incident.secondInterviewerId=incident.secondInterviewerId;
        this.incident.secondInterviewerName=incident.secondInterviewerName;
        this.incident.thirdInterviewerId=incident.secondInterviewerId;
        this.incident.thirdInterviewerName=incident.secondInterviewerName;
      }
      this.incident.showScore=incident.showScore=="Y"?"Y":null
       this.incident.hasTest=incident.hasTest=="Y"?"Y":null
       this.isExam=incident.hasTest=="Y"?true:false
       if(incident.hasTest=="Y"){
        incident.incidentTestTemplates.forEach(element => {
          element.incidentId=null;
          element.id=null;
         });
        this.incident.incidentTestTemplates=incident.incidentTestTemplates
       }else{
        this.incident.incidentTestTemplates=[]
       }
       this.checkForm(this.incident);
     }

     templateSelected($event,i){
      this.incident.incidentTestTemplates[i].testTemplateId = $event.item.id;
      this.incident.incidentTestTemplates[i].testTemplateName = $event.item.testTemplateName;
      this.incident.incidentTestTemplates[i].duration = $event.item.duration;
      this.incident.incidentTestTemplates[i].description = $event.item.description;
      this.incident.incidentTestTemplates[i].totalQuestions = $event.item.totalQuestions;
      this.incident.incidentTestTemplates[i].passPercentage = $event.item.passPercentage;
    }

        //tempFix for date
  convertDate(inputDates):Date {
    var day:number=0;var year:number=0;var month:number=0;
    var hrs:number=0;var min:number=0;var seconds:number=0;var miliseconds:number=0;
    var finalDate:Date;
​
    if(inputDates.length>3){
      year=inputDates[0];month=inputDates[1];day=inputDates[2];hrs=inputDates[3];
      min=inputDates[4];seconds=inputDates[5];//miliseconds=inputDates[6];
      finalDate= new Date(year, month-1 , day , hrs , min , seconds);
    }else{
      year=inputDates[0];month=inputDates[1];day=inputDates[2];
      finalDate = new Date(year, month-1, day); 
    }
    return finalDate;
  }

  deleteInc(id){
    this.service.deleteInc(id).subscribe(x=>{
      this.route.navigate(['/recruitment/event/'+this.view.toLowerCase(),{page:this.pgNumber}]);
    })
}

  getbyId(id:any){
    this.service.getIncidentById(id).subscribe(x=>{
      this.incident=x;   
      this.checkForm(this.incident);   
      if(this.incident.hasTest=='Y'){
        this.isExam=true;
      }else{
        this.incident.hasTest=null;
      }
     if(this.incident.hasInterview=='Y'){
      this.isInterview=true;
     }else{
      this.incident.hasInterview=null
     }
     if(this.incident.isEntryLevel=='N'){
      this.incident.isEntryLevel=null;
     }
     if(this.incident.isProfessional=='N'){
      this.incident.isProfessional=null;
     }
     this.incident.showScore=this.incident.showScore=="Y"?"Y":null

     if( this.incident.incidentTestTemplates.length<1){
      this.addTemplate();
     }     
     
  });
  }

  getDays($event){
    console.log($event);
  }

  approve(){
    this.service.updateIncident(this.incident.id,'approval',this.incident).subscribe(x=>{
      this.route.navigate(['/recruitment/event/'+this.view.toLowerCase(),{page:this.pgNumber}]);
    },
    error => {                       
    this.errorMsg = JSON.parse(error._body);
    this.saveMsg = { type: 'danger', text: this.errorMsg.message }
    })
  }
  interviewer($event,id){
    switch(id){
      case 1:
        this.incident.firstInterviewerId=$event.id;
        this.incident.firstInterviewerName=$event.empName;
        break;
      case 2:
        this.incident.secondInterviewerId=$event.id;
        this.incident.secondInterviewerName=$event.empName;
        break;
      case 3:
        this.incident.thirdInterviewerId=$event.id;
        this.incident.thirdInterviewerName=$event.empName;
        break;  
    }
  }

  addTemplate(){
    let testTemplate = new IncidentTestTemplate
    testTemplate.testTemplateName=null;
    this.incident.incidentTestTemplates.push(testTemplate) ;
  }
  delTemplate(i){
    this.incident.incidentTestTemplates.splice(i,1);
  }

  navigateToList(){
    this.route.navigate(['/recruitment/event/'+this.view.toLowerCase(),{page:this.pgNumber}]);
  }

  exam(){   
    if(this.isExam==true){
      this.isExam=false;
    }else if(this.isExam==false){
      this.isExam=true;
    }
  }
  interview(){
    if(this.isInterview==true){
      this.isInterview=false;
    }else if(this.isInterview==false){
      this.isInterview=true;
    }
  }

  getMapLocation($event){
    this.incident.mapLocId=null;
   this.incident.mapLocId=$event.id.toString();
   this.incident.locName=$event.locName;
   this.checkForm(this.incident);
  }

  update(){
    this.incident.isEntryLevel =(this.incident.isEntryLevel?"Y":"N");
    this.incident.hasInterview =(this.incident.hasInterview?"Y":"N");
    this.incident.hasTest =(this.incident.hasTest?"Y":"N");
    this.incident.isProfessional =(this.incident.isProfessional?"Y":"N");
    this.incident.showScore =(this.incident.showScore?"Y":"N");
    console.log(this.incident);
    this.service.updateinc(this.incident.id,this.incident).subscribe(inc=>{
      this.route.navigate(['/recruitment/event/'+this.view.toLowerCase(),{page:this.pgNumber}]);
    },
    error => {                       
this.errorMsg = JSON.parse(error._body);
this.saveMsg = { type: 'danger', text: this.errorMsg.message }
    })
  }

  updateFaculty(){
    this.service.updateIncident(this.incident.id,'facility-update',this.incident).subscribe(x=>{
      this.route.navigate(['/recruitment/event/'+this.view.toLowerCase(),{page:this.pgNumber}]);
    },
    error => {                       
this.errorMsg = JSON.parse(error._body);
this.saveMsg = { type: 'danger', text: this.errorMsg.message }
    })
  }

    submit(){
      this.incident.isEntryLevel =(this.incident.isEntryLevel?"Y":"N");
      this.incident.hasInterview =(this.incident.hasInterview?"Y":"N");
      this.incident.hasTest =(this.incident.hasTest?"Y":"N");
      this.incident.isProfessional =(this.incident.isProfessional?"Y":"N");
      this.incident.showScore =(this.incident.showScore?"Y":"N");        
        this.service.addincident(this.incident).subscribe(x=>{
          this.route.navigate(['/recruitment/event/'+this.view.toLowerCase(),{page:this.pgNumber}]);          
        },
        error => {                       
this.errorMsg = JSON.parse(error._body);
this.saveMsg = { type: 'danger', text: this.errorMsg.message }
        })
    }
    validate(){
      this.incidentName = new FormControl('', [Validators.required]);
      this.incidentDesc = new FormControl('', [Validators.required]);      
      this.mapLocId = new FormControl('', [Validators.required]); 
      this.hrGroupId = new FormControl('', [Validators.required]);
      this.jobId = new FormControl('', [Validators.required]);
      this.selectInOrTs = new FormControl('', [Validators.required]);
     
      this.startDate = new FormControl('', [Validators.required]);
      this.endDate  = new FormControl('', [Validators.required]);
      this.freshOrExp = new FormControl('', [Validators.required]);
      this.noOfDays = new FormControl('', [Validators.required]);
      this.faculty = new FormControl('', [Validators.required]);
      this.contactNo = new FormControl('', [Validators.required]);
      this.skillsAqd = new FormControl('', [Validators.required]);
  }
  checkForm(incident){
    this.myTrainingGroup.patchValue({
      startDate:incident.trStartDt,
      endDate:incident.trEndDt,
      faculty:incident.faculty,
      contactNo:incident.contactNo,
      skillsAqd:incident.skillsAqd
    })
    this.commonGroup.patchValue({
      incidentName:incident.incidentName,
      incidentDesc:incident.incidentDesc,
      mapLocId:incident.mapLocId
    })
  }
  createForm(){   
    this.commonGroup = new FormGroup({
      incidentName:this.incidentName,
      incidentDesc:this.incidentDesc,
      mapLocId:this.mapLocId
    });
    this.myGroup = new FormGroup({       
      hrGroupId:this.hrGroupId,       
      jobId:this.jobId,            
    });	

    this.myTrainingGroup = new FormGroup({
      startDate:this.startDate,
      endDate:this.endDate,
      faculty:this.faculty,
      contactNo:this.contactNo,
      skillsAqd:this.skillsAqd
    });
  }
  
}