import { Component, OnInit } from '@angular/core';
import { IncidentService } from '../incident.service';

import{ Incident,IncidentApplicant,ParticipatingInstitute } from '../../../vo/incident';
import { Observable } from 'rxjs/Observable';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { ActivatedRoute,Params, Router } from '@angular/router';
import { status_css } from '../../../vo/status'
import { FormGroup, FormBuilder, Validators,FormControl, Form } from '@angular/forms';
import { DatePipe } from '@angular/common';



@Component({
    selector: '',
    templateUrl: 'schedule-interview.component.html'
})

export class ScheduleInterivewComponent implements OnInit {
    IncidentApplicants:Array<IncidentApplicant>
    completed: Array<IncidentApplicant>;
    id:any;
    code:any;
    summary:any;
    incidentApplicantsFiltered:Array<IncidentApplicant>
    firstInterviewerId:any;
    firstInterviewerName:string=null;
    secondInterviewerId: any;
    secondInterviewerName:string=null;
    thirdInterviewerId:any;
    thirdInterviewerName:string=null;
    binding:string='true';
    allEmployees: any=[];
    page :number = 1;
    pgNumber:number;
    actualDate:string;
    errorMsg:any;
    saveMsg:any;
    pageSize :number = 10
    searchString:string;
    date:Date;
    secondDate:Date;
    thirdDate:Date; 

    incident:Incident;
    isStillPendingApproval:boolean=false;
    toggleBool: boolean=true;

    statuses:any=status_css;
    myGroup: FormGroup;
    ScheduleDate:FormControl;


    // currentDate = new Date(); 

    settings = {
        bigBanner: true,
        timePicker: true,
        minTime: '10:00 am',
        format: 'dd/MM/yyy, hh:mm a',
        defaultOpen: false,
        closeOnSelect:false
    }
   



    constructor(
        private service:IncidentService,
        private activedRouter:ActivatedRoute,
        private route:Router,
        private datePipe: DatePipe

    ) {}

    ngOnInit() {
        let dt = new Date();
        //this.date = new Date();    
        this.date = new Date(dt.getFullYear(), dt.getMonth(), dt.getDate(), 10, 0, 0);
        this.secondDate=new Date(dt.getFullYear(), dt.getMonth(), dt.getDate(), 10, 0, 0);
        this.thirdDate=new Date(dt.getFullYear(), dt.getMonth(), dt.getDate(), 10, 0, 0);
        this.incident = new Incident();
        this.incident.incidentApplicants = new Array<IncidentApplicant>();
        this.incidentApplicantsFiltered =new Array<IncidentApplicant>();
        this.completed= new Array<IncidentApplicant>();
        this.service.getAllEmployee().subscribe(x=>{
            this.allEmployees=x;
        })

        this.validate();
        this.createForm();

        // var date = new FormControl(new Date());
        

        // var currentDate = new Date(); 
        // console.log(currentDate);
        // var n = currentDate.toLocaleString();
        // var newDate = date.split(",")[0];
        // console.log(newDate.toString())
        // this.actualDate = newDate.toString();
        // console.log(n)
        // var newDate = this.datePipe.transform(currentDate, 'yyyy/MM/dd hh:mm a');
        // this.actualDate = this.datePipe.transform(currentDate, 'yyyy-MM-dd','HH:MM');
        // console.log(newDate)

        if (this.activedRouter.params['_value']['id']){
            this.activedRouter.params.subscribe(params=>{
            this.pgNumber=params.page;
             this.service.getIncidentById(params.id).subscribe(x=>{
                this.incident=x;
                if(this.incident.incidentStatus=="PENDING-APPROVAL"){
                    this.isStillPendingApproval=true;
                }
                this.incident.incidentApplicants.forEach(element => {
                    element.isApplicantDetails=false,element.isviewApplicantDetails=true;

                    if(element.isInterviewEligible == 'Y'){
                     if(element.isInterviewScheduled =='N' || element.isInterviewScheduled==null ){
                        element.isInterviewScheduled=null;
                        this.incidentApplicantsFiltered.push(element);
                    }else{
                      this.completed.push(element);
                    }    
                }                    
                });
            });
            });   
        }   
      
     }

     submit(){
        this.incident.incidentApplicants=[];
        this.incident.incidentTestTemplates=[];
        this.incident.participatingInstitutes=[];
        this.incident.incidentTests=[];
      
            this.incidentApplicantsFiltered.forEach(element => {
                element.isInterviewScheduled = (element.isInterviewScheduled ? "Y" :null);   
                    element.firstInterviewerId=this.incident.firstInterviewerId;
                    element.secondInterviewerId=this.incident.secondInterviewerId;
                    element.thirdInterviewerId=this.incident.thirdInterviewerId                
                    //  element.interviewScheduledDt=this.date;
                    //  element.secondInterviewDt=this.secondDate;
                    //  element.thirdInterviewDt=this.thirdDate;
                     element.interviewScheduledDt=this.datePipe.transform(this.date, 'yyyy-MM-dd hh:mm:ss');
                     element.secondInterviewDt=this.datePipe.transform(this.secondDate, 'yyyy-MM-dd hh:mm:ss'); 
                     element.thirdInterviewDt= this.datePipe.transform(this.thirdDate, 'yyyy-MM-dd hh:mm:ss'); 
                        if(this.toggleBool=true && element.isInterviewScheduled=='Y'){
                             this.incident.incidentApplicants.push(element); 
                        }
                        
             });    
        
         this.service.updateIncident(this.incident.id,'interview-schedule',this.incident).subscribe(x=>{
            this.route.navigate(['/recruitment/event/recruitment',{page:this.pgNumber}]);
         },
         error => {                       
            this.errorMsg = JSON.parse(error._body);
            this.saveMsg = { type: 'danger', text: this.errorMsg.message }
         })        
        
     }
   
     navigateToList(){
        this.route.navigate(['/recruitment/event/recruitment',{page:this.pgNumber}]);
     }

      textSearch(){
         this.incidentApplicantsFiltered = this.incident.incidentApplicants.filter(e =>
            e.firstName.toLowerCase().includes(this.searchString.toLowerCase())
            || e.lastName.toLowerCase().includes(this.searchString.toLowerCase())
            || e.email.toLowerCase().includes(this.searchString.toLowerCase())
            || e.mobileNumber.toLowerCase().includes(this.searchString.toLowerCase())
            || e.source.toLowerCase().includes(this.searchString.toLowerCase())
         );            
     }
     masterSelected:boolean = false;
        checkUncheckAll() {
        for (var i = 0; i < this.incidentApplicantsFiltered.length; i++) {
        this.incidentApplicantsFiltered[i].isInterviewScheduled = this.masterSelected;
        }
        
        }
        
        isAllSelected() {
        this.masterSelected = this.incidentApplicantsFiltered.every(function(mg1:any) {
        return mg1.isInterviewScheduled == true;
        })
        
        }
     changeEvent(event) {
        if (event.target.checked) {
            this.toggleBool= false;
        }
        else {
            this.toggleBool= true;
        }
    }
   
     firstInterviewer($event){
        this.incident.firstInterviewerId = $event.id;
        this.incident.firstInterviewerName= $event.empName;
    }
    
    secondInterviewer($event){
        this.incident.secondInterviewerId = $event.id;
        this.incident.secondInterviewerName= $event.empName;
    }
    thirdInterviewer($event){
        this.incident.thirdInterviewerId = $event.id;
        this.incident.thirdInterviewerName= $event.empName;
    }
    changeActive(id:any){ 
        this.binding=id;
       }
       downloadCsv(){    
      let jsonObj = this.completed[0];
      let columns=Object.keys(jsonObj);      
      this.service.downloadFile(this.completed, 'shortList',columns);
    }

    validate(){
        this.ScheduleDate = new FormControl('', [Validators.required]);
    }
    createForm(){
        this.myGroup = new FormGroup({
            ScheduleDate:this.ScheduleDate
        });
    }
}