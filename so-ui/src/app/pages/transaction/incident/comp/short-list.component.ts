import { Component, OnInit } from '@angular/core';
import { IncidentService } from '../incident.service';
import { Incident, IncidentApplicant, ParticipatingInstitute, IncidentApplicantPdf } from '../../../vo/incident';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
    selector: '',
    templateUrl: 'short-list.component.html'
})

export class ShortListComponent implements OnInit {

    incidentApplicantsFiltered: Array<IncidentApplicant>;
    id: any;
    code: any;
    summary: any;
    incident: Incident;
    incidents: any[];
    advSearch:boolean=false;
    completed: Array<IncidentApplicant>;
    binding:string='true';
    incidantApplicantsFiltered:any=[];
    page :number = 1;
    pgNumber:number;
    errorMsg:any;
    saveMsg:any;
    pageSize :number = 10
    institute:any;
    course:any;
    passoutYear:any;
    marks:any;
    historyOfArrears:any;
    isEligibleForTest:any;
    incidentApplicantPdf:IncidentApplicantPdf;
    toggleBool: boolean=true;

    searchString: string = "";
    isStillPendingApproval: boolean = true;
    masterselect: string;



    constructor(
        private service: IncidentService,
        private activedRouter: ActivatedRoute,
        private route:Router,
    ) { }

    ngOnInit() {

        this.incident = new Incident();
        this.incident.incidentApplicants = new Array<IncidentApplicant>();
        this.incidentApplicantsFiltered = new Array<IncidentApplicant>();
        this.completed= new Array<IncidentApplicant>();
        this.incidentApplicantPdf = new IncidentApplicantPdf();
        // this.incidantApplicantsFiltered =new Array<IncidentApplicantPdf>();

        if (this.activedRouter.params['_value']['id']) {
            this.activedRouter.params.subscribe(params=>{
                this.pgNumber=params.page;
            this.service.getIncidentById(params.id).subscribe(x => {
                    this.incident = x;
                    if (this.incident.incidentStatus == "PENDING-APPROVAL") {
                        this.isStillPendingApproval = true;
                    }
                    this.incident.incidentApplicants.forEach(element => {
                        element.isApplicantDetails=false,element.isviewApplicantDetails=true;

                    
                        if (element.isInterviewEligible == 'N'|| element.isInterviewEligible == null) {
                            element.isInterviewEligible = null;
                            this.incidentApplicantsFiltered.push(element);
                        } else {
                            this.completed.push(element);
                        }        
                               
                    });
                
                });
            });
        }
        this.service.getIncidents().subscribe(x => {
            this.incidents = x;        
        })
    }


    textSearch() {
        this.incidentApplicantsFiltered = this.incident.incidentApplicants.filter(e =>
            e.firstName.toLowerCase().includes(this.searchString.toLowerCase())
            || e.lastName.toLowerCase().includes(this.searchString.toLowerCase())
            || e.email.toLowerCase().includes(this.searchString.toLowerCase())
            || e.mobileNumber.toLowerCase().includes(this.searchString.toLowerCase())
            || e.source.toLowerCase().includes(this.searchString.toLowerCase())
        );
    }

    navigateToList(){
        this.route.navigate(['/recruitment/event/recruitment',{page:this.pgNumber}]);
      
    }
   
    checkinterview(){
        for(var i=0; i < this.incidentApplicantsFiltered.length;i++){
            this.incidentApplicantsFiltered[i].isInterviewEligible =this.masterselect;
        }
    }
   
    changeEvent(event) {
        if (event.target.checked) {
            this.toggleBool= false;
        }
        else {
            this.toggleBool= true;
        }
    }

    advanceSearch(){
        if(this.advSearch==false){
            this.advSearch=true;
        }else if(this.advSearch==true){
            this.advSearch=false;
        }
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
  

    masterSelected:boolean = false;
    checkUncheckAll() {
        for (var i = 0; i < this.incidentApplicantsFiltered.length; i++) {
        this.incidentApplicantsFiltered[i].isInterviewEligible = this.masterSelected;
        }
        
        }
        
        isAllSelected() {
        this.masterSelected = this.incidentApplicantsFiltered.every(function(mg1:any) {
        return mg1.isInterviewEligible == true;
        })
        
        }
    submit() {
        this.incident.incidentApplicants = [];
        this.incident.incidentTestTemplates=[];
        this.incident.participatingInstitutes=[];
        this.incident.incidentTests=[];             
        this.incidentApplicantsFiltered.forEach(element => {
            element.isInterviewEligible = (element.isInterviewEligible ? "Y" :null);
            this.incident.incidentApplicants.push(element);
        });
        this.service.updateIncident(this.incident.id, 'interview-eligibility', this.incident).subscribe(x => {            
            this.route.navigate(['/recruitment/event/recruitment',{page:this.pgNumber}]);                       
        },
        error => {                       
        this.errorMsg = JSON.parse(error._body);
            this.saveMsg = { type: 'danger', text: this.errorMsg.message }
        })

    }
    
    changeActive(id:any){ 
        this.binding=id;
       }

       downloadCsv(){        
      let jsonObj = this.completed[0];
      let columns=Object.keys(jsonObj);      
      this.service.downloadFile(this.completed, 'shortList',columns);
    }

}