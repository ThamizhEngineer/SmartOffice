import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { JobService } from '../../job.service';
import { Job } from '../../vo/job';
import { JobPlan } from '../../vo/job-plan';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import { CommonService } from '../../../../shared/common/common.service';

@Component({
    selector: 'create-job-model',
    templateUrl: 'create-job-model.component.html'
})

export class CreateJobModelComponent implements OnInit {

    message: string;

    clients: any = [];
    clientsAC = (text$: Observable<string>) =>
        text$.pipe(
            debounceTime(200),
            distinctUntilChanged(),
            map(term => (term === '' ? this.clients
                : this.clients.filter(v => v.clientName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
        );
    client: any = { id: null };
    client_formatter = (x: { clientName: string }) => { x.clientName };

    saleorders: any = [];
    saleOrderAC = (text$: Observable<string>) =>
        text$.pipe(
            debounceTime(200),
            distinctUntilChanged(),
            map(term => (term === '' ? this.saleorders
                : this.saleorders.filter(v => v.saleOrderCode.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
        );
    saleOrder: any = { id: null };
    sale_formatter = (x: { saleOrderCode: string }) => { x.saleOrderCode };

    projects: any = [];
    projectAC = (text$: Observable<string>) => text$.pipe(debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.projects.filter(v => v.projName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)));
    project: any = { id: null };
    project_formatter = (x: { projName: string }) => x.projName;

    jobTypes: any = [];
    jobTypeAC = (text$: Observable<string>) =>
        text$.pipe(
            debounceTime(200),
            distinctUntilChanged(),
            map(term => (term === '' ? this.jobTypes
                : this.jobTypes.filter(v => v.jobTypeName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
        );
    jobType_formatter = (x: { jobTypeName: string }) => { x.jobTypeName };


    siteLocations: any = []
    siteLocationAC = (text$: Observable<string>) =>
        text$.pipe(
            debounceTime(200),
            distinctUntilChanged(),
            map(term => (term === '' ? this.siteLocations
                : this.siteLocations.filter(v => v.siteName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
        );
    site_location_formatter = (x: { siteName: string }) => { x.siteName };

    cjp: JobPlan;
    job: Job;

    saveMsg: any;
    validation: any;
    shifts: any = [];
    saleorder: any=[];

    constructor(public _Service: JobService, private router: Router, public commonService: CommonService) { }

    ngOnInit() {
        this.starter()
    }

    close() {
        //sends an event to list component, when received closes the model
        this._Service.changeMessage("close-model")
    }

    starter() {
        this.job = new Job();
        this.cjp = new JobPlan();

        this._Service.getMasterData('clients').subscribe(x => {
            this.clients = x;
        });

        this._Service.getMasterData('job-types').subscribe(x => {
            this.jobTypes = x.content;
        });

        this._Service.getAttendanceShift().subscribe(x => {
            this.shifts = x;
        });

        this._Service.getSiteLocations().subscribe(x => { //loads site locations for autocomplete
            this.siteLocations = x;
        })
    }

    save() {
        this.client.id = this.job.partnerId;
        this.saleOrder.id = this.job.saleOrderId;
        this.job.jobName = this.job.jobName;
        if (!this.client.id || !this.saleOrder.id || !this.job.jobName || !this.cjp.startDt || !this.cjp.endDt) {
            console.log(this.client.id)
            this.validation = { 'type': 'danger', 'text': "Enter All required values" };
        }
        else {
            this.cjp.partnerId = this.job.partnerId;
            this.cjp.saleOrderId = this.saleOrder.id;
            this.cjp.jobName = this.job.jobName;
            this.cjp.projId = this.job.projId;
            this.cjp.mJobTypeId = this.job.mJobTypeId;
            this._Service.createJobPlan(this.cjp).subscribe(x => {
                this.validation = { 'type': 'success', 'text': "Job Plan Created Successfully" };
                this.close();
                this.router.navigateByUrl("/job/job-plan/detail/" + x.id);
            }, error => {
                console.log(error)
                this.validation = { 'type': 'danger', 'text': "Server Error" };
            });
        }
    }

    //On change/select functions ---------------------------------------------------

    onSiteLocationSelected($event) {
        this.job.siteLocationId = $event.item.id;
        this.job.siteLocationName = $event.item.siteName;
    }

    onClientSelected($event, job:Job) {
        job.partnerId = $event.item.id;
        job.endClientId = $event.item.id;
        job.endClientName = $event.item.clientName;
        this._Service.getSaleOrderByPartnerId(this.job.partnerId).subscribe(x => {
            this.saleorders = x
        });
    }

    onSaleOrderSelected($event) {
        console.log($event)
        this.job.saleOrderId = $event.item.id;
        this.job.partnerId = $event.item.partnerId;
        this.job.saleOrderCode = $event.item.saleOrderCode;
        // this._Service.getProjects().subscribe(x => {
        //     this.projects = x
        //     console.log(this.project)
        // });
        this._Service.getSaleOrderByPartnerId(this.job.partnerId).subscribe(x => {
            this.saleorders = x
        });
    }

    onProjectSelected($event) {
        this.job.projId = $event.item.id;
        this.job.projName = $event.item.projName;
        this.job.jobTypeName
    }

    onJobTypeSelected($event) {
        this.job.mJobTypeId = $event.item.id;
        this.job.jobTypeName = $event.item.jobTypeName;
    }

    // clientOnChange() {
    //     if (this.client.id) {
    //         this._Service.getSaleOrderByPartnerId(this.client.id).subscribe(x => this.saleorders = x);
    //         this._Service.getProjectsByPartnerId(this.client.id).subscribe(x => this.projects = x);
    //     }
    // }
    saleOnChange() {
        if (this.saleOrder.id) this.cjp.jobLocation = this.saleOrder.location;
    }

    projectOnChange() {

    }

}