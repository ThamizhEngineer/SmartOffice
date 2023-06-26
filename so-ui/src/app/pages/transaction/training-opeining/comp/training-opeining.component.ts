import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { Incident, IncidentApplicant } from '../../../vo/incident';
import { TrainingOpeningService } from '../training-opeining.service'
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../../../../auth/_models';
import { UserService } from '../../../../auth/_services';

@Component({
    selector: '',
    templateUrl: 'training-opeining.component.html'
})

export class TrainingOpeiningComponent implements OnInit {
    @ViewChild('submitWarining') submitWarining: TemplateRef<any>;
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
    pageSize :number = 10;
	page :number = 1;
    constructor(
        private service:TrainingOpeningService,
        private _userService:UserService,
        private modalService: NgbModal
    ) { }

    ngOnInit() { 
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

    apply(incident){
        this.incident=new Incident();
        this.incident=incident;
        this.modalReference = this.modalService.open(this.submitWarining, { size: 'lg' });
    }

    submit(){        
        this.incidentApplicant= new IncidentApplicant();        
        this.incidentApplicant.employeeId = this.user.employeeId;        
        this.incApp.push(this.incidentApplicant);        
        this.service.applyForTraining(this.incident.id,this.incApp).subscribe(x=>{
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