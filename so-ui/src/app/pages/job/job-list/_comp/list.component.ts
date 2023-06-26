import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { JobService } from '../../job.service';
import { Job } from '../../vo/job';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CreateJobModelComponent } from './create-job-model.component';
import { JobStatusModelComponent } from '../../../../shared/_models/job-status/job-status-model.component';

import { Observable } from 'rxjs/Observable';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';


@Component({
    selector: 'job',
    templateUrl: 'list.component.html'
})

export class JobListComponent implements OnInit {



    searchClient = (text$: Observable<string>) => text$.pipe(debounceTime(200),distinctUntilChanged(),map(term => (term === '' ? this.clientProfile: this.clientProfile.filter(v => v.clientName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10)));    
    clientFormatter = (x: { clientName: string }) => { x.clientName };


    jobTypeAC = (text$: Observable<string>) => text$.pipe(debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.jobTypes.filter(v => v.jobTypeName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)));       
	jobType_formatter = (x: { jobTypeName: string }) => x.jobTypeName;

    page = 1;
    pageSize = 6;
    collectionSize:any;
    isAdvSearch:boolean=false;

    jobs: Job[];
    jobsFilter: Job[]
    clients: any = [];
    job: Job

    clientProfile:any=[];
    jobTypes:any=[];
    saveMsg: { 'type': string; 'text': string; };

    // saveMsg:any
    modalReference: any = null;
    message: string;
    searchString:string='';

    partnerId:string='';
    partnerName:string='';
    jobCode:string='';
    jobName:string='';
    jobTypeId:string='';
    jobTypeName:string='';
    displayHelp:boolean=false;
    new: Job[];

    constructor(public _Service: JobService, private router: Router, private modalService: NgbModal) { }

    ngOnInit() {
        this.fetchJobPlans();
        this.triggerReceiveDataEvent()

        this._Service.getMasterData('clients').subscribe(x=>{
            this.clientProfile=x;
        });
        this._Service.getMasterData('job-types').subscribe(x => {
			this.jobTypes = x.content;

		});
    }

    openHelp(){            
        this.displayHelp=true;
    }
    closeHelp(){
        this.displayHelp=false;
    }

    textSearch(){
        this.jobs = this.jobsFilter.filter(e =>
           e.jobCode.toLowerCase().includes(this.searchString.toLowerCase())
           || e.jobName.toLowerCase().includes(this.searchString.toLowerCase())
           || e.jobTypeName.toLowerCase().includes(this.searchString.toLowerCase())
           || e.endClientName.toLowerCase().includes(this.searchString.toLowerCase())
           || e.mapLocationName.toLowerCase().includes(this.searchString.toLowerCase())
           || e.startDt.toLowerCase().includes(this.searchString.toLowerCase())
        );            
    }

    triggerReceiveDataEvent() {
        console.log('Data receiver event triggered')
        this._Service.currentMessageEvent.subscribe((data) => {
            this.modalReference.close()
        });
    }

    createJob() {
        this._Service.getMasterData('clients').subscribe(x => {
            this.clients = x;
            this.job = new Job();
            this.modalReference = this.modalService.open(CreateJobModelComponent, { size: 'lg' });
        });
    }

    deleteRow(job,id) {
        if(job.jobPlanStatus==null){
		this._Service.deleteJobPlan(id).subscribe(x => {
			this.saveMsg = { 'type': 'success', 'text': "Job Plan Deleted Successfully" };
			this.fetchJobPlans();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
    }
        else {
            this.saveMsg = { 'type': 'danger', 'text': "Job cannot be deleted after confirmation" };
        }
    }
    
    onClientSelect($event){
        this.partnerId=$event.item.id;
        this.partnerName=$event.item.clientName;
    }

    onJobSelect($event){
        this.jobTypeId = $event.item.id;
		this.jobTypeName = $event.item.jobTypeName;
    }

    reset(){
        this.partnerId='';
        this.partnerName='';
        this.jobCode='';
        this.jobName='';
        this.jobTypeId='';
        this.jobTypeName='';   
        this.ngOnInit();
    }

    fetchJobPlans() {
        this._Service.getJobPlans().subscribe(x => {
            if (x) {
                this.collectionSize = x.length
                this.jobs = x;
                this.new = this.jobs
                console.log(this.jobs)
                this.jobsFilter=x;
            }
        }, (error => {
            console.log(error)
        }));
    }

    advanceSearch(){
        this._Service.advSearchJobPlans(this.partnerId,this.jobCode,this.jobName,this.jobTypeId).subscribe(x=>{
            if (x) {
                this.collectionSize = x.length
                this.jobs = x;
                this.jobsFilter=x;
            }
        }, (error => {
            console.log(error)
        }));
    }

    routeHelper(key, id) {
        switch (key) {
            case 'plan':
                this.router.navigate(['/job/job-plan/detail', id]);
                break;
            case 'tracker':
                this.router.navigate(['job/track-jobs/detail/', id]);
                break;
            case 'logistics':
                this.router.navigate(['/job/resource-scheduler/detail/', id]);
                break;
            case 'status':
                this.modalReference = null;
                this.modalReference = this.modalService.open(JobStatusModelComponent, { size: 'lg' });
                this.modalReference.componentInstance.id = id;
                break;
            case 'report':
                this.router.navigate(['/job/job-report/job-report-detail/', id]);
                break;
            default:
                break;
        }

    }
}

