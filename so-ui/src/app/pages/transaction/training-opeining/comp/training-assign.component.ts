import { Component, OnInit,ViewChild,TemplateRef } from '@angular/core';
import { Incident, IncidentApplicant } from '../../../vo/incident';
import { TrainingOpeningService } from '../training-opeining.service'
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../../../../auth/_models';
import { UserService } from '../../../../auth/_services';
@Component({
    selector: '',
    templateUrl: 'training-assign.component.html'
})

export class AssignTrainingComponent implements OnInit {
    @ViewChild('viewApplicant') viewApplicant: TemplateRef<any>;
    newdate:string;
    training:any=[];    
    view:string='open';
    incident:Incident;
    user:User;
    modalReference:any = null;
    msg:any;
    errorMsg:any;
    incidentApplicant:IncidentApplicant;
    incApp:any=[];
    employees:any=[];
    selectAllemp:boolean=false;

    page :number = 1;    
    pageSize :number = 10

    constructor(
        private service:TrainingOpeningService,
        private _userService:UserService,
        private modalService: NgbModal
    ) { }

    ngOnInit() {
        this.incident=new Incident();
        this.newdate = new Date().toJSON().substr(0,10);
        this.user = this._userService.getCurrentUser(); 
        this.service.getTrainings().subscribe(x=>{
            this.training=x;                        
        });     
     }

     get newTraining(){               
        return this.training.filter(x=>x.trStartDt>=this.newdate)
    }

    get ongoingTraining(){
        return this.training.filter(x=>x.trStartDt<=this.newdate&&x.trEndDt>=this.newdate)
    }
    get completedTraining(){
        return this.training.filter(x=>x.trEndDt<=this.newdate)
    }

    viewIncidentApp(incident){         
        this.incident=incident;
        this.service.getTrainingEmployee(incident.id).subscribe(x=>{
            x.forEach(element => {
             element.select=false;  
             element.employeeId=element.id; 
            });
            this.employees=x;            
        });         
        this.modalReference = this.modalService.open(this.viewApplicant, { size: 'lg' });        
    }

    get selectedEmployee(){
        return this.employees.filter(x=>x.select==true);
    }

    selectAll(){
        this.selectAllemp=!this.selectAllemp;
        this.employees.forEach(element => {
            if(element.isEmpAttend=='N'){
                console.log(this.selectAllemp)
                element.select=this.selectAllemp;
            }
        });
        this.ngOnInit();
    }

    submit(){
     this.service.applyForTraining(this.incident.id,this.selectedEmployee).subscribe(x=>{
        this.modalReference.close();
        this.msg= { type: 'success', text: "Update Successfully" } 
        this.ngOnInit();
    },error=>{
        this.modalReference.close();
        this.errorMsg=JSON.parse(error._body); 
        this.msg= { type: 'danger', text: this.errorMsg.message }   
    })
    }

}