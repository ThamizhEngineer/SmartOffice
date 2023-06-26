import { Component, OnInit } from '@angular/core';
import { ActivatedRoute,Router,Params } from '@angular/router';
import { Incident,IncidentApplicant } from '../../../vo/incident';
import { IncidentService } from '../incident.service';
import { CommonService } from '../../../../shared/common/common.service';
import { Observable } from 'rxjs';

 

@Component({
    selector: '',
    templateUrl: 'add-applicant.component.html'
})

export class AddApplicantComponent implements OnInit {
    
    incident:Incident;
    errorMsg:any;
    saveMsg:any;
    vcApplicant:Array<IncidentApplicant>;
    code:any;
    summary:any;

    constructor(
        private service: IncidentService,
        private activedRouter:ActivatedRoute,
        private commonService: CommonService,
        private route:Router
    ) { }

    ngOnInit() { 
        this.vcApplicant = new Array<IncidentApplicant>();

        if (this.activedRouter.params['_value']['id']) {
            this.activedRouter.params.switchMap((params: Params) => this.service.getIncidentById(params['id'])).
                subscribe(x => {
                    this.incident = x;   
                   this.code =  this.incident.vcCode;
                   this.summary = this.incident.vcSummary;               
                });
        }

        let app = new IncidentApplicant()
        this.vcApplicant.push(app);
    }

    addApplicant(){
        let app = new IncidentApplicant()
        this.vcApplicant.push(app);   
       
    }
    removeApplicant(id){
        this.vcApplicant.splice(id,1);
    }

    uploadResume(index, event, docTypeCode) {
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {            
			 this.commonService.uploadDocument(fileList[0], docTypeCode).map((response: Response) => response)
                .catch(error => Observable.throw(error))
                .subscribe(
                    data => {
                        this.vcApplicant[index].resumeDocId = data[0].docId;                                            
                    },
                    error => console.log(error)
                )
        }
    }

    submit(){
        this.incident.incidentApplicants=this.vcApplicant;
        this.service.addOrUpdateincident(this.incident).subscribe(x=>{
            this.route.navigateByUrl('/recruitment/incident');
        },
        error => {                       
this.errorMsg = JSON.parse(error._body);
this.saveMsg = { type: 'danger', text: this.errorMsg.message }
        })
    }
}