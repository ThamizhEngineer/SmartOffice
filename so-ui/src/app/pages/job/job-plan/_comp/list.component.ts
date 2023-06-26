import { Component, OnInit, ViewEncapsulation, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { JobService } from '../../job.service';
import { JobPlan } from '../../vo/job-plan';
import { Job } from '../../vo/job';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import { CommonService } from '../../../../shared/common/common.service';

const locations = ["Chennai", "Gujarat", "Ahmedabad"];

@Component({
	selector: 'job-plan-list',
	templateUrl: './list.component.html'
})
export class JobPlanListComponent implements OnInit {

	@ViewChild('jdetail') cdetail: TemplateRef<any>;

	clientsAC = (text$: Observable<string>) =>
	text$.pipe(
		debounceTime(200),
		distinctUntilChanged(),
		map(term => (term === '' ? this.clients
			: this.clients.filter(v => v.clientName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10)));
			client_formatter = (x: { clientName: string }) => { x.clientName };

	clients: any = [];
	client: any = { id: null };

    saleOrderAC = (text$: Observable<string>) =>
        text$.pipe(
            debounceTime(200),
            distinctUntilChanged(),
            map(term => (term === '' ? this.saleorders
                : this.saleorders.filter(v => v.saleOrderCode.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10)));
		sale_formatter = (x: { saleOrderCode: string }) => { x.saleOrderCode };

	saleorders: any = [];
	saleOrder: any = { id: null };

	projectAC = (text$: Observable<string>) =>
	text$.pipe(
		debounceTime(200),
		distinctUntilChanged(),
		map(term => (term === '' ? this.projects
			: this.projects.filter(v => v.projName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10)));
			project_formatter = (x: { projName: string }) => { x.projName };

	projects: any = [];
	project: any = { id: null };

	jobTypeAC = (text$: Observable<string>) =>
	text$.pipe(
		debounceTime(200),
		distinctUntilChanged(),
		map(term => (term === '' ? this.jobTypes
			: this.jobTypes.filter(v => v.jobTypeName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10)));
			jobType_formatter = (x: { jobTypeName: string }) => { x.jobTypeName };


	locationAC = (text$: Observable<string>) => text$.pipe(debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.locations.filter(v => v.locName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)));
	// project: any = {id: null};
	location_formatter = (x: { locName: string }) => x.locName;
	modalReference: any = null;
	saveMsg: any;
	validation: any;
	job: Job;
	locations: any = [];
	jobTypes: any = [];
	shifts:any=[];
	mapLocationFlag: boolean = false;

	page :number = 1;
    pageSize :number = 10;
	// jp: Array<JobPlan>;
	jp:any=[];
	cjp: JobPlan;

	constructor(private router: Router, private route: ActivatedRoute, private modalService: NgbModal,private commonService: CommonService, private service: JobService) { }

	ngOnInit() {
		this.cjp = new JobPlan();
		this.getJobPlans();
		this.service.getMasterData('job-types').subscribe(x => {
			this.jobTypes = x.content;
		});
		this.service.getProjects().subscribe(x => {
            this.projects = x
		});
		
		this.service.getAttendanceShift().subscribe(x=>{
			this.shifts=x;
		})
		this.getMapLocations();
	}

	getMapLocations() {
		this.service.getMapLocation().subscribe(x => {
			// console.log(x)
			this.locations = x;
		})
	}
	hide() {

		if (this.mapLocationFlag) {
			this.mapLocationFlag = false;
			this.getMapLocations();
		} else {
			this.mapLocationFlag = true;
		}


	}

	getJobPlans() {
		this.service.getJobPlans().subscribe(x => {
			this.jp = x;

		})
	}



	createJob() {
		this.service.getMasterData('clients').subscribe(x => {
			this.clients = x;
			this.job = new Job();
			this.modalReference = this.modalService.open(this.cdetail, { size: 'lg' });
		});
	}
	onClientSelected($event) {
		this.job.partnerId = $event.item.id;
		this.job.endClientId = $event.item.id;
		this.job.endClientName = $event.item.clientName;
		this.service.getSaleOrderByPartnerId(this.job.partnerId).subscribe(x => {
			this.saleorders = x
		});

	}
	onSaleOrderSelected($event) {
        console.log($event)
        this.job.saleOrderId = $event.item.id;
        this.job.partnerId = $event.item.partnerId;
        this.job.saleOrderCode = $event.item.saleOrderCode;
        // this.service.getProjects().subscribe(x => {
        //     this.projects = x
        //     console.log(this.project)
        // });
        this.service.getSaleOrderByPartnerId(this.job.partnerId).subscribe(x => {
            this.saleorders = x
        });
    }


	onProjectSelected($event) {
		console.log($event)
		this.job.projId = $event.item.id;
		this.job.projName = $event.item.projName;
		// this.job.jobTypeName
	}
	
	onJobTypeSelected($event) {
        this.job.mJobTypeId = $event.item.id;
        this.job.jobTypeName = $event.item.jobTypeName;
    }

	onLocationSelected($event) {
		this.job.mapLocationId = $event.id;
		this.job.mapLocationName = $event.locName;
	}
	deleteRow(id?: any) {
		this.service.deleteJobPlan(id).subscribe(x => {
			this.saveMsg = { 'type': 'success', 'text': "Job Plan Deleted Successfully" };
			this.getJobPlans();
		}, error => {
			this.saveMsg = { 'type': 'danger', 'text': "Server Error" };
		});
	}

	// clientOnChange() {
	// 	if (this.client.id) {
	// 		this.service.getSaleOrderByPartnerId(this.client.id).subscribe(x => this.saleorders = x);
	// 		this.service.getProjects().subscribe(x => this.projects = x);
	// 	}
	// }

	saleOnChange() {
		if (this.saleOrder.id) this.cjp.jobLocation = this.saleOrder.location;
	}

	projectOnChange() {

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
			this.cjp.mapLocationId = this.job.mapLocationId;
			this.cjp.jobName = this.job.jobName;
			this.cjp.projId = this.job.projId;
			this.cjp.mJobTypeId = this.job.mJobTypeId;
			this.service.createJobPlan(this.cjp).subscribe(x => {
				this.validation = { 'type': 'success', 'text': "Job Plan Created Successfully" };
				this.modalReference.close();
				this.router.navigateByUrl("/job/job-plan/detail/" + x.id);
			}, error => {
				this.validation = { 'type': 'danger', 'text': "Server Error" };
			});
		}
	}

	saveJob() {
		// console.log(this.job)
		this.job.endClientName = null;
		this.job.jobTypeName = null;
		this.job.mapLocationName = null;
		this.job.projName = null;
		this.job.saleOrderCode = null;

		this.service.createJobPlan(this.job).subscribe(x => {
			// this.validation = {'type': 'success', 'text': "Job Plan Created Successfully"};
			this.modalReference.close();
			//this.getJobPlans();
			// this.router.navigateByUrl("/job/job-plan/detail/"+x.id);
		}, error => {
			// this.validation = {'type': 'danger', 'text': "Server Error"};
		});

		this.modalReference.close();
	}
}
