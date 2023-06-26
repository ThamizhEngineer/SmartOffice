import { Component, OnInit } from '@angular/core';
import { IncidentService } from '../incident.service';
import{ Incident,IncidentApplicant} from '../../../vo/incident';
import { ActivatedRoute,Params, Router } from '@angular/router';
import { status_css } from '../../../vo/status'


@Component({
    selector:'',
    templateUrl:'employee-conversion.component.html',
    styleUrls:['employee-conversion.component.scss']
})

export class EmployeeConversionComponent implements OnInit{

    incident:Incident;
    IncidentApplicants:Array<IncidentApplicant>;
    incidentApplicantsFiltered:Array<IncidentApplicant>;
    approve:string="N";
    reject:string="N";
    onhold:string="N";
    finalDecision:any;
    map = new Map();
    appIds:Array<any>;finalInterviewStatus: any;
    finalTestStatus: any;
    report: any;
    errorMsg:any;
    saveMsg:any;
    statuses:any=status_css;
    page :number = 1;
    pageSize :number = 10
    pgNumber:number;   
    
    constructor(
        private activedRouter:ActivatedRoute,
        private service:IncidentService,
        private route:Router
        
    ){}
        ngOnInit(){
        this.saveMsg=null;
        this.incident = new Incident();
        this.IncidentApplicants = new Array<IncidentApplicant>();
        this.incidentApplicantsFiltered =new Array<IncidentApplicant>();
        this.appIds=new Array<any>();

        if(this.activedRouter.params['_value']['id']){
            this.activedRouter.params.subscribe(params=>{
            this.pgNumber=params.page;
            this.service.getIncidentById(params.id).subscribe(x=>{
                this.incident=x;
                console.log(this.incident)
                this.incident.incidentApplicants.forEach(element => {
                    element.isApplicantDetails=false,element.isviewApplicantDetails=true;  
                });
                this.incidentApplicantsFiltered = this.incident.incidentApplicants;
            });
        });
        }
        }
        
        selected(clickedButton){
            this.incidentApplicantsFiltered.forEach(element => {
                element.finalDecision=clickedButton;
            });


            if (clickedButton=="onboard") {
                this.incidentApplicantsFiltered.forEach(element => {
                    element.approve = (element.approve?"Y":null); 
                    if (element.approve=="Y") {
                        if (element.id!=null || element.id!=undefined) {
                            this.appIds.push(element.id)
                        }
                    }                   
                });
                this.service.convertToEmployee(this.incident.id,this.appIds).subscribe(x=>{                   
                    this.incident=x;
                    this.ngOnInit();
                    this.route.navigate(['/recruitment/event/recruitment',{page:this.pgNumber}]);
                    },
                    error => {                       
            this.errorMsg = JSON.parse(error._body);
            this.saveMsg = { type: 'danger', text: this.errorMsg.message }
                    })
                
            } else {

                if (clickedButton=="select") {
                    this.incidentApplicantsFiltered.forEach(element => {
                        element.approve = (element.approve?"Y":null);     
                        if (element.approve=="Y") {
                            element.finalDecision="Approved";
                        }
                    });
                }
                if (clickedButton=="reject") {
                    this.incidentApplicantsFiltered.forEach(element => {
                        element.approve = (element.approve?"Y":null);     
                        if (element.approve=="Y") {
                            element.finalDecision="Rejected";
                        }
                    });
                }
                if (clickedButton=="hold") {
                    this.incidentApplicantsFiltered.forEach(element => {
                        element.approve = (element.approve?"Y":null);     
                        if (element.approve=="Y") {
                            element.finalDecision="on-hold";
                        }
                    });
                }

                this.service.updateEmployeeConversion(this.incident.id,this.incidentApplicantsFiltered).subscribe(x=>{                
                this.ngOnInit();
                },
                error => {                       
        this.errorMsg = JSON.parse(error._body);
        this.saveMsg = { type: 'danger', text: this.errorMsg.message }
                })
            }            
        }

        navigateToList(){
            this.route.navigate(['/recruitment/event/recruitment',{page:this.pgNumber}]);
        }

        onClickCheckBox(row){
            if (row.employeeCode!=null) {
                return false
            }
        }

            search(){
        this.service.search(this.finalTestStatus, this.finalInterviewStatus,this.finalDecision).subscribe(x=>{
        this.incidentApplicantsFiltered=x;   
        console.log(this.finalTestStatus)
        console.log(this.finalInterviewStatus)
        console.log(this.finalDecision)
        console.log(this.incidentApplicantsFiltered)
        })
     }
     reset(){
         this.finalTestStatus=null, this.finalInterviewStatus=null, this.finalDecision=null
        this.search();
     }
}



