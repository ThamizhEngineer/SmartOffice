import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { WebApplicationsService } from '../web-applications.service';
import { WebSiteApplicant } from '../../../vo/web-site-applicant';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonService } from '../../../../shared/common/common.service';
import { elementAt } from 'rxjs/operator/elementAt';
import { Applicant } from '../../../vo/applicant';
import { ApplicantService } from '../../../operation/applicant/applicant.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';


@Component({
    selector: '',
    templateUrl: 'validate-applications.component.html'
})

export class ValidateApplicationsComponent implements OnInit {

    @ViewChild('edit') edit: TemplateRef<any>;

    webApplicants:any=[];
    completed: Array<WebSiteApplicant>;
    user:Array<WebSiteApplicant>;
    jrApplicantsFiltered: Array<WebSiteApplicant>;
    id: any;
    code: any;
    summary: any;
    totalApp: any;
    validateApp: any;
    jobRequest: any = [];
    searchString:string;
    checked: string='true';
    select:boolean=false;
    incidents:any[];
    colleges:any;
    degrees:any;
    courses:any;
    applicant:Applicant;
    modalReference:any = null;
    pageSize :number = 10
	    page :number = 1;

    constructor(
        private service: WebApplicationsService,
        private activatedRoute: ActivatedRoute,
        private commonService: CommonService,
        private applicantService:ApplicantService,
        private route:Router,
        private modalService: NgbModal
    ) { }

    ngOnInit() {


        this.webApplicants = new Array<WebSiteApplicant>();
        this.completed= new Array<WebSiteApplicant>();
        this.jrApplicantsFiltered = new Array<WebSiteApplicant>();
        this.applicant= new Applicant();

        this.service.getJobrequests().subscribe(x => {
            this.jobRequest = x;
        })

        this.applicantService.getIncidents().subscribe(x=>{
            this.incidents=x;

        })
        
        this.applicantService.getColleges().subscribe(x=>{
            this.colleges=x;
            console.log(this.colleges)
        })
        this.applicantService.getDegrees().subscribe(x=>{
            this.degrees=x;
        })
        this.applicantService.getCourses().subscribe(x=>{
            this.courses=x;
        })
    }

    webApplicant() {
        this.jrApplicantsFiltered=[];
        this.webApplicants=[];
        this.service.getWebApplications(this.id).subscribe(x=>{
            this.jrApplicantsFiltered=x;
            console.log(this.jrApplicantsFiltered)
            this.jrApplicantsFiltered.forEach(element=>{
                this.webApplicants.push(element);
            });
        })
    }

    textSearch() {
        console.log(this.jrApplicantsFiltered)
        this.webApplicants=this.jrApplicantsFiltered.filter(e =>
            e.firstName.toLowerCase().includes(this.searchString.toLowerCase())
            || e.lastName.toLowerCase().includes(this.searchString.toLowerCase())
            || e.email.toLowerCase().includes(this.searchString.toLowerCase())
            || e.mobNum.toLowerCase().includes(this.searchString.toLowerCase())
        );
    }

    download(docId, docFileName) {

        if (docId != null && docId != "" && docId != undefined) {
            this.commonService.downloadDocument(docId, docFileName);
        }
    }

    validate(){
        console.log(this.jrApplicantsFiltered)
        this.jrApplicantsFiltered=[];
        this.webApplicants.forEach(element=>{
            if(element.select==true && element.institute!='others' && element.degreeName!='others' && element.course!='others'){
                element.isValidate="Y";
                this.jrApplicantsFiltered.push(element);
            }
        });
        console.log(this.webApplicants)
        this.service.updateWebApplication(this.jrApplicantsFiltered).subscribe(x=>{

        })
        this.ngOnInit;
    }

    inValidate(){
        this.jrApplicantsFiltered=[];
        this.webApplicants.forEach(element=>{
            if(element.select==true){
                console.log(element)
                element.isValidate="N";
                this.jrApplicantsFiltered.push(element);
            }
        });
        console.log(this.jrApplicantsFiltered)
        this.service.updateWebApplication(this.jrApplicantsFiltered).subscribe(x=>{

        })
    }
    getEdit(user){
        this.user=user;
        // console.log(this.user)
        this.modalReference = this.modalService.open(this.edit, { size: 'lg' });
    }

    changeActive(id:any,data){ 
        console.log(data)
        this.checked=id;
        console.log(this.checked)
       }

}