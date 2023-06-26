import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { JobService } from './../job.service';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { JobType, JobTypeProfile, JobTypeMaterials, JobTypeTaskType } from '../vo/job-type'
import { CommonService } from '../../../shared/common/common.service';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
	selector: 'job-type-master',
	templateUrl: './job-type-master.component.html'
})
export class JobTypeMasterComponent implements OnInit {
	jobType = 'job-types';
	rows: Array<JobType>;
	max = '100'
	jt: JobType;
	jobTypeTaskType: JobTypeTaskType;
	jobTypeMaterials: JobTypeMaterials;
	jobTypeProfile: JobTypeProfile;
	taskTypeRows: Array<JobTypeTaskType> = [];
	materialRows: Array<JobTypeTaskType> = [];
	profileRows: any = [];
	modalReference: any = null;
	saveMsg: any;
	errorMsg: any;
	materials: any = [];
	taskTypes: any = [];
	profiles: any = [];

	taskTypeProduct = (text$: Observable<string>) =>
		text$.pipe(
			debounceTime(200),
			distinctUntilChanged(),
			map(term => (term === '' ? this.taskTypes
				: this.taskTypes.filter(v => v.taskTypeName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
		);
	tasktype_formatter = (x: { taskTypeName: string }) => {
		x.taskTypeName
	};

	materialProduct = (text$: Observable<string>) =>
		text$.pipe(
			debounceTime(200),
			distinctUntilChanged(),
			map(term => (term === '' ? this.materials
				: this.materials.filter(v => v.materialName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
		);
	material_formatter = (x: { materialName: string }) => {
		x.materialName
	};

	profileAC = (text$: Observable<string>) =>
		text$.pipe(
			debounceTime(200),
			distinctUntilChanged(),
			map(term => (term === '' ? this.profiles
				: this.profiles.filter(v => v.profileName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
		);
	profile_formatter = (x: { profileName: string }) => {
		x.profileName
	};


	@ViewChild('vdetail') vdetail: TemplateRef<any>;

	constructor(private router: Router, private service: JobService, private modalService: NgbModal, private commonService: CommonService) {

	}

	ngOnInit() {
		this.jt = new JobType();
		this.jobTypeTaskType = new JobTypeTaskType();
		this.jobTypeMaterials = new JobTypeMaterials();
		this.jobTypeProfile = new JobTypeProfile();

		this.getJobs();
		this.service.getJobs('materials/').subscribe(x => {
			this.materials = x.content;
		});

		this.service.getJobs('task-types').subscribe(x => {
			this.taskTypes = x
			console.log(this.taskTypes)
		});

		this.service.getJobs('profiles').subscribe(x => {
			this.profiles = x
			console.log(x);
		});

	}

	addStageRows() {
		this.taskTypeRows.push(Object.assign({}, this.jobTypeTaskType));
		console.log(this.taskTypeRows)
	}

	addMaterialRows() {
		this.materialRows.push(Object.assign({}, this.jobTypeMaterials));
	}
	addProfileRows() {
		this.profileRows.push(Object.assign({}, this.jobTypeProfile));
	}
	materialSelected($event, m?: JobTypeMaterials) {
		m.materialId = $event.item.id;
		m.materialCode = $event.item.materialCode;
		m.materialName = $event.item.materialName;
	}

	taskTypeSelected($event, t?: JobTypeTaskType) {
		t.taskTypeId = $event.item.id;
		t.taskTypeName = $event.item.taskTypeName;

	}

	profileOnChage($event, pr: JobTypeProfile) {
		pr.mProfileId = $event.item.id;
		pr.profileName = $event.item.profileName;
		pr.profile = $event.item;
	}

	// profileOnChage(pr?: any){

	// 	if(pr.profile.id){
	// 		pr.mProfileId = pr.profile.id;


	// 	}

	// }


	getJobs() {
		this.service.getJobs(this.jobType).subscribe(x => {
			this.rows = x.content;
		});
	}


	showDetail(id?: any) {
		this.errorMsg=null;
		this.service.getJobById(this.jobType, id).subscribe(x => {
			this.jt = x;
			console.log(this.jt)
			this.taskTypeRows = x.jobTypeTaskTypes;
			this.materialRows = x.jobTypeMaterials;
			this.profileRows = x.jobTypeProfile;
			console.log(this.profileRows)
			// this.profileRows[0].profileName ='';
			if (this.taskTypeRows.length == 0) {
				this.taskTypeRows.push(Object.assign({}, this.jobTypeTaskType));
			}

			if (this.materialRows.length == 0) {
				this.materialRows.push(Object.assign({}, this.jobTypeMaterials));
			}

			if (this.profileRows.length == 0) {
				this.profileRows.push(Object.assign({}, this.jobTypeProfile));
			}

			this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
		});
	}

	createNew() {
		this.jt = new JobType();
		this.taskTypeRows = [];
		this.taskTypeRows.push(Object.assign({}, this.jobTypeTaskType));
		this.materialRows = [];
		this.materialRows.push(Object.assign({}, this.jobTypeMaterials));
		this.profileRows = [];
		this.profileRows.push(Object.assign({}, this.jobTypeProfile));

		this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
	}

	get checkProfile(){
		return	this.profileRows.filter(x=>x.mProfileId==null)		 
	}

	save() {
		this.jt.jobTypeTaskTypes = this.taskTypeRows;
		this.jt.jobTypeMaterials = this.materialRows;
		this.jt.jobTypeProfile = this.profileRows;
		if (this.jt.id) {
			this.service.updateJob(this.jobType, this.jt).subscribe(x => {
				this.saveMsg = { 'type': 'success', 'text': "Job Type Updated Successfully" };

				this.getJobs();
				this.modalReference.close();
			});
		}
		else {
			this.service.createJob(this.jobType, this.jt).subscribe(x => {
				this.saveMsg = { 'type': 'success', 'text': "Job Type Created Successfully" };
				this.getJobs();
				this.modalReference.close();
			}, error => {
				this.errorMsg=JSON.parse(error._body);    		
    			this.errorMsg = { type: 'danger', text: this.errorMsg.message }   
				// this.errorMsg = { 'type': 'danger', 'text': "Job Type Name Already Exists" };
			});
		}
	}

	get findWeightage() {
		let total = 0;
		this.taskTypeRows.forEach(element => {
			total = total + (Number(element.weightage));
		});
		if (total == 100) {
			return true;
		} else {
			return false;
		}

	}

	deleteRow(id?: any) {
		this.service.deleteJob(this.jobType, id).subscribe(x => {
			this.saveMsg = { 'type': 'success', 'text': "Job Type Deleted Successfully" };
			this.getJobs();
		}, error => {
			this.saveMsg = { 'type': 'danger', 'text': "Server Error" };
		});
	}
}