import { Component, OnInit, Input, ViewChild, TemplateRef } from '@angular/core';
import { IncidentService } from '../incident.service';
import { Incident, IncidentApplicant } from '../../../vo/incident';
import { ActivatedRoute, Params } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { saveAs } from 'file-saver';



@Component({
    selector:'',
    templateUrl: 'training-progress.component.html',
    styleUrls:['training-event.css']
})

export class TrainingProgressComponent implements OnInit{

    @ViewChild('certificate') certificate: TemplateRef<any>;
    @ViewChild('edit') edit: TemplateRef<any>;

    incident:Incident;
    incidentApplicant:IncidentApplicant;
  
    select:boolean=false;
    date:Date;
    errorMsg:any;
    saveMsg:any;
    pgNumber:number;

    constructor(
        private activedRouter:ActivatedRoute,
        private service:IncidentService,
        private modalService: NgbModal,
        private route:Router
    ){}

    ngOnInit(){

        this.incident = new Incident();
        this.incidentApplicant = new IncidentApplicant();
       

        if (this.activedRouter.params['_value']['id']) {
            this.activedRouter.params.subscribe(params=>{
                this.pgNumber=params.page;
             this.service.getIncidentById(params['id']).subscribe(x => {
                    this.incident = x;
                    this.incident.incidentApplicants.forEach(element=>{
                        if(element.isAttended=="Y"){
                            element.isAttended="Y";
                        }
                        if(element.certificateIssued=="Y"){
                            element.certificateIssued="Y";
                        }
                    });
                  this.incident.incidentApplicants;
                });
            });
        }
    }

    noShow(){
        this.incident.incidentTestTemplates=[];
        this.incident.incidentTests=[];
        this.incident.incidentApplicants.forEach(element=>{             
            if(element.select==true){
                element.isAttended="Y";
                element.isEligibleForTest="Y"
            }else if(element.isAttended==null){
                element.isAttended="N";
            }
        });
        console.log(this.incident.incidentApplicants);
        this.service.updateIncident(this.incident.id,"attended",this.incident).subscribe(x=>{
            this.navigateToList();
        })
    }
    issueCertificate(){
        this.incident.incidentTestTemplates=[];
        this.incident.incidentApplicants.forEach(element=>{
            if(element.select==true){
                element.certificateIssued="Y";
            }
        });
        this.service.updateIncident(this.incident.id,"certificate-issue",this.incident).subscribe(x=>{
            this.genCertificate(x.incidentApplicants);
        },
        error => {                       
this.errorMsg = JSON.parse(error._body);
this.saveMsg = { type: 'danger', text: this.errorMsg.message }
        })
       
    }

    genCertificate(incidentApplicant){
       
        incidentApplicant.forEach(element=>{
            if(element.certificateIssued=="Y"){
                this.service.generateCertificate(element.id).subscribe(x=>{
                    console.log(x)
                })
            }
        });
      this.navigateToList();
    }

    navigateToList(){
        this.route.navigate(['/recruitment/event/training',{page:this.pgNumber}]);
    }


    modalReference:any = null;
    certificateDetail(incidentApplicant){
        this.incidentApplicant=incidentApplicant;
		this.modalReference = this.modalService.open(this.certificate, { size: 'lg' });
    }

        downloadPdf(incidentApplicant){
            this.incidentApplicant=incidentApplicant;
            if(this.incidentApplicant.docId!=null&&this.incidentApplicant.docId!=""&&this.incidentApplicant.docId!=undefined){
    
                this.service.download(this.incidentApplicant.docId).subscribe(x=>{
                    let content_type = x.headers.get('Content-type');
                    let extension;
					switch(content_type){
						case 'application/jpeg':
						case 'application/jpg':
							extension = 'jpg';
							break;
						case 'application/png':
							extension = 'png';
							break;
						case 'application/gif':
							extension = 'gif';
							break;
						case 'application/pdf':
							extension = 'pdf';
							break;
						default:
							extension = 'pdf';
					}
					let x_filename = "Applicant-"+this.incidentApplicant.firstName+"." + extension;
                    saveAs(x.blob(), x_filename);
                })
    
        } 
        }
    
    moreDetails(incidentApplicant){
        this.incidentApplicant=incidentApplicant;
		this.modalReference = this.modalService.open(this.edit, { size: 'lg' });
    }

    save(incidentApplicant){
        this.select=true;
        this.incidentApplicant=incidentApplicant;
        this.service.updateApplicant(this.incident.id,this.incidentApplicant).subscribe(x=>{
        },
        error => {                       
this.errorMsg = JSON.parse(error._body);
this.saveMsg = { type: 'danger', text: this.errorMsg.message }
        })
    }
}