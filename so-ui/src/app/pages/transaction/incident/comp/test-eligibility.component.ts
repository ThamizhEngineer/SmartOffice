import { Component, OnInit } from '@angular/core';
import { IncidentService } from '../incident.service';
import{ Incident,IncidentApplicant,ParticipatingInstitute } from '../../../vo/incident';
import { Router } from '@angular/router'
import { status_css } from '../../../vo/status'


import { ActivatedRoute,Params } from '@angular/router';
@Component({
    selector: '',
    templateUrl: 'test-eligibility.component.html',
    styleUrls:['employee-conversion.component.scss']
})

export class TestEligibilityComponent implements OnInit {
    incidentApplicantsFiltered:Array<IncidentApplicant>;
    completed: Array<IncidentApplicant>;
    id:any;
    code:any;
    summary:any;
    incident:Incident;
    incidents:any[];
    advSearch:boolean=false;
    binding:string='true';
    statuses:any=status_css;
    masterSelected:boolean = false;

    institute:any;
    course:any;
    passoutYear:any;
    marks:any;
    historyOfArrears:any;
    isEligibleForTest:any;
    page :number = 1;
    pgNumber:number;
    errorMsg:any;
    saveMsg:any;
    pageSize :number = 10
    searchString: string = "";
    isStillPendingApproval:boolean=false;
    constructor(
        private service:IncidentService,
        private activedRouter:ActivatedRoute,
        private route:Router,
    ) { }

    ngOnInit() {

        this.incident = new Incident();
        this.incident.incidentApplicants = new Array<IncidentApplicant>();
        this.incidentApplicantsFiltered =new Array<IncidentApplicant>();
        this.completed= new Array<IncidentApplicant>();
       
        
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

                  if(element.isEligibleForTest=='N' || element.isEligibleForTest==null){
                      element.isEligibleForTest=null;
                      this.incidentApplicantsFiltered.push(element);
                  }else{
                    this.completed.push(element);
                  }  
                  //this.incidentApplicantsFiltered= this.incident.incidentApplicants;
                });
                
            }); 
        }); 
        }

       

        this.service.getIncidents().subscribe(x=>{
            this.incidents=x;            
        })
               
      
            

     }

     advanceSearch(){
         if(this.advSearch==false){
             this.advSearch=true;
         }else if(this.advSearch==true){
             this.advSearch=false;
         }
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

     submit(){        
        this.incident.incidentApplicants=[];
        this.incident.incidentTestTemplates=[];
        this.incident.participatingInstitutes=[];
        this.incident.incidentTests=[];       
         this.incidentApplicantsFiltered.forEach(element => {
          element.isEligibleForTest = (element.isEligibleForTest?"Y":null);
          this.incident.incidentApplicants.push(element);
         });         
       this.service.updateIncident(this.incident.id,'check-test-eligibility',this.incident).subscribe(x=>{
        this.route.navigate(['/recruitment/event/recruitment',{page:this.pgNumber}]);        
       },
       error => {                       
this.errorMsg = JSON.parse(error._body);
this.saveMsg = { type: 'danger', text: this.errorMsg.message }
       })
     }

     reset(){
        this.institute=null;this.course=null,this.passoutYear=null,this.marks=null,this.historyOfArrears=null,this.isEligibleForTest=null
        this.search();
     }

     search(){
        this.service.advanceSearch(this.incident.id,this.institute,this.course,this.passoutYear,this.marks,this.historyOfArrears,this.isEligibleForTest).subscribe(x=>{
        this.incidentApplicantsFiltered=[];
        x.forEach(element => {
                if(element.isEligibleForTest=='N'){
                    element.isEligibleForTest=null;
                    this.incidentApplicantsFiltered.push(element);
                }else{
                  this.incidentApplicantsFiltered.push(element);
                } 
            });
        })
     }
     navigateToList(){
        this.route.navigate(['/recruitment/event/recruitment',{page:this.pgNumber}]);
     }
     changeActive(id:any){ 
        this.binding=id;
       }
       downloadCsv(){        
      let jsonObj = this.completed[0];
      let columns=Object.keys(jsonObj);      
      this.service.downloadFile(this.completed, 'shortList',columns);
    } 
    checkUncheckAll() {
        for (var i = 0; i < this.incidentApplicantsFiltered.length; i++) {
          this.incidentApplicantsFiltered[i].select = this.masterSelected;
        }
    }
    isAllSelected() {

        this.masterSelected = this.incidentApplicantsFiltered.every(function(mg1:any) {
          return mg1.selected == true;
        })
    }
}