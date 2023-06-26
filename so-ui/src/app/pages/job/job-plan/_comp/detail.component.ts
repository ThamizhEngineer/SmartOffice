import { Component, OnInit, ViewEncapsulation, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { JobService } from '../../job.service';
import { JobPlan, JobPlanProfile, JobPlanEmp, JobPlanMaterial, JobPlanTaskType, JobPlanEmpSkill, JobPlanEmpCommitment } from '../../vo/job-plan';
import { Job } from '../../vo/job';
import { SaleOrder, Goods, Services } from '../../vo/sale-order';
import { ProfileFinderJob, MatchedEmployee } from '../../vo/profile-finder-job';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { ResourceSchedulerService } from '../../resource-scheduler/resource-scheduler.service'
import { DatePipe } from '@angular/common';
import { CommonService } from '../../../../shared/common/common.service';
import { SaleOrderModelComponent } from '../../../../shared/_models/sale-order-module/sale-order-model.component';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
	selector: 'job-plan-detail',
	templateUrl: './detail.component.html',
	providers: [ResourceSchedulerService]
})
export class JobPlanDetailComponent implements OnInit {

	@ViewChild('mMulti') mMulti: TemplateRef<any>;
	@ViewChild('saleOrder') saleOrder: TemplateRef<any>;
	@ViewChild('notify') notify: TemplateRef<any>;

	projectAC = (text$: Observable<string>) =>
		text$.pipe(
			debounceTime(200),
			distinctUntilChanged(),
			map(term => (term === '' ? this.projects
				: this.projects.filter(v => v.projName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
		);
	project_formatter = (x: { projName: string }) => {
		x.projName
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

	materialAC = (text$: Observable<string>) =>
		text$.pipe(
			debounceTime(200),
			distinctUntilChanged(),
			map(term => (term === '' ? this.materials
				: this.materials.filter(v => v.materialName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
		);
	material_formatter = (x: { materialName: string }) => {
		x.materialName
	};

	siteLocations: any = []
	siteLocationAC = (text$: Observable<string>) =>
		text$.pipe(
			debounceTime(200),
			distinctUntilChanged(),
			map(term => (term === '' ? this.siteLocations
				: this.siteLocations.filter(v => v.siteName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
		);
	site_location_formatter = (x: { siteName: string }) => {
		x.siteName
	};

	taskTypeAC = (text$: Observable<string>) => text$.pipe(debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.taskTypes.filter(v => v.taskTypeName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)));
	// jp: JobPlan;
	job: Job;
	modalReference: any = null;
	projects: any = [];
	so: SaleOrder;
	sg: boolean = false;
	ss: boolean = false;
	profiles: any = [];
	profileRows: any = [];
	jobPlanProfile: JobPlanProfile;
	tasktype_formatter = (x: { taskTypeName: string }) => x.taskTypeName;
	profileFinderJob: ProfileFinderJob;
	employees: any = [];
	teamRows: any = [];
	materials: any = [];
	taskTypes: any = [];
	employeeListProfileFinder: any = [];
	jobPlanEmp: JobPlanEmp;
	tempTeam: any;
	shifts: any = [];
	departments: any = [];
	jobPlanMaterial: JobPlanMaterial;
	materialRows: Array<JobPlanMaterial> = [];
	jobPlanTaskType: JobPlanTaskType;
	taskTypeRows: Array<JobPlanTaskType> = [];

	constructor(private router: Router, private activatedRoute: ActivatedRoute, public datepipe: DatePipe, private modalService: NgbModal, private service: JobService,
		private resourceSchedulerService: ResourceSchedulerService, private commonService: CommonService) {
		// this.jp = new JobPlan();
		this.job = new Job();
		this.jobPlanProfile = new JobPlanProfile();
		this.jobPlanEmp = new JobPlanEmp();
		this.jobPlanMaterial = new JobPlanMaterial();
		this.jobPlanTaskType = new JobPlanTaskType();
	}

	ngOnInit() {
		this.service.getMasterData('profiles').subscribe(x => {
			this.profiles = x
			console.log(this.profiles)
		});

		this.service.getMasterData('materials/').subscribe(x => {
			this.materials = x.content;
		});
		this.service.getAttendanceShift().subscribe(x => {
			this.shifts = x;
		})

		this.service.getSiteLocations().subscribe(x => { //loads site locations for autocomplete
			this.siteLocations = x;
		})

		this.service.getMasterData('task-types').subscribe(x => {
			this.taskTypes = x
		});

		this.service.getProjects().subscribe(x => {
			this.projects = x;
		});
		this.profileFinderJob = new ProfileFinderJob();
		this.profileRows.push(Object.assign({}, this.jobPlanProfile));
		this.materialRows.push(Object.assign({}, this.jobPlanMaterial));
		this.taskTypeRows.push(Object.assign({}, this.jobPlanTaskType));

		if (this.activatedRoute.params['_value']['_id']) {
			this.activatedRoute.params.switchMap((params: Params) => this.service.getJobPlanById(params['_id']))
				.subscribe(x => {
					this.loadJobPlan(x);
					console.log(x);					
				});

		}
	}

	siteLocationSelected($event) {
		console.log($event)
		this.job.siteLocationId = $event.item.siteLocationId
		this.job.siteLocationName = $event.item.siteLocationName
	}

	loadJobPlan(job: Job) {
		this.job = job;
		this.profileRows = job.jobPlanProfiles; //ref: JobPlanProfile
		this.materialRows = job.jobPlanMaterials; //ref: JobPlanMaterial
		this.taskTypeRows = job.jobPlanTaskTypes; //ref: JobPlanTaskType
		this.teamRows = job.jobPlanEmps; //ref: JobPlanEmp

		//If any of these array are empty - empty object is being pushed to the array

		if (this.profileRows.length == 0) this.profileRows.push(Object.assign({}, new JobPlanProfile()));
		if (this.materialRows.length == 0) {
			let m = new JobPlanMaterial();
			m.materialName = "";
			this.materialRows.push(Object.assign({}, m))
		};
		if (this.taskTypeRows.length == 0) {
			let t = new JobPlanTaskType();
			t.taskTypeName = "";
			this.taskTypeRows.push(Object.assign({}, t))
		};
	}

	projectSelected($event) {
		this.job.projId = $event.item.id;
		this.job.projName = $event.item.projName;
	}

	addProfileRows() {
		let profile = new JobPlanProfile();
		profile.tJobId = this.job.id;
		this.profileRows.push(Object.assign({}, profile));
	}

	deleteProfileRow(profile?: JobPlanProfile) {
		if (profile.id != null && profile.tJobId != null) {
			this.service.deleteJobPlanLines(this.job.id, profile.id, 'job-plan-profile').subscribe(x => {
			})
		}
	}

	addMaterialRows() {
		let m = new JobPlanMaterial();
		m.materialName = null;
		this.materialRows.push(Object.assign({}, m));
	}

	deleteMaterialRow(material?: JobPlanMaterial) {
		if (material.id != null && material.jobId != null) {
			this.service.deleteJobPlanLines(this.job.id, material.id, 'job-plan-material').subscribe(x => {
			})
		}
	}

	addStageRows() {
		let t = new JobPlanTaskType();
		t.taskTypeName = null;
		t.jobId = this.job.id;
		this.taskTypeRows.push(Object.assign({}, t));
	}

	deleteStageRow(taskType?: JobPlanTaskType) {
		if (taskType.id != null && taskType.jobId != null) {
			this.service.deleteJobPlanLines(this.job.id, taskType.id, 'job-plan-task-type').subscribe(x => {
			})
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

	profileOnChage($event, pr: any) {
		console.log($event)
		// pr.id = $event.item.id;
		// 		pr.profileName = $event.item.profileName;
		this.teamRows.forEach(team => {
			if (team.profileId == $event.item.id) {
				alert("Entry already exist")
				return false
			} else {
				pr.profileId = $event.item.id;
				pr.profileName = $event.item.profileName;
				return true
			}
		});
	}

	materialSelected($event, m?: JobPlanMaterial) {
		console.log($event)
		m.materialId = $event.item.id;
		m.materialName = $event.item.materialName;
		console.log(m)
	}
	taskTypeSelected($event, t?: JobPlanTaskType) {
		t.jobId = this.job.id;
		t.taskTypeId = $event.item.id;
		t.taskTypeName = $event.item.taskTypeName;

	}

	startResourcing() {
		this.filterJobPlans();
		this.service.updateJobPlan(this.job, 'start-resourcing').subscribe(x => {
			this.service.getJobPlanById(this.job.id).subscribe(x => {
				this.loadJobPlan(x);
			});
			// this.jobPlanEmp.startDt=this.job.startDt;
			// this.jobPlanEmp.endDt=this.job.endDt;
			// console.log(this.job.jobPlanEmps)
		});
		// this.populateTeam();
		//this.router.navigateByUrl('/job/job-list');

	}

	cancel() {
		this.router.navigateByUrl('/job/job-list');
	}

	populateTeam() {
		this.profileRows.forEach(x => {
			for (let i = 0; i < x.headCount; i++) {
				let v = Object.assign({}, this.jobPlanEmp);
				v.profileName = x.profile.profileName;

				v.startDt = this.job.startDt;
				v.endDt = this.job.endDt;
				this.teamRows.push(v);

			}
		})
	}
	searchEmployeeId: string;
	searchDeptId: string;
	searchProfileId: string;
	profileName: string='';
	empStartDt: any;
	empEndDt: any;
	searchEmployee() {
		this.profileFinderJob.fromDt = this.job.startDt;
		this.profileFinderJob.toDt = this.job.endDt;
		this.profileFinderJob.mapLocationId = this.job.mapLocationId;
		this.profileFinderJob.mEmployeeId = this.searchEmployeeId;
		this.profileFinderJob.mDepartmentId = this.searchDeptId;
		this.profileFinderJob.mProfileId = this.searchProfileId;

		this.resourceSchedulerService.addProfileFinder(this.profileFinderJob).subscribe(x => {
			this.profileFinderJob = x;
			this.resourceSchedulerService.startProfileFinder(this.profileFinderJob).subscribe(x => {
				this.profileFinderJob = x;
				this.employeeListProfileFinder = this.profileFinderJob.matchedEmployees;
			})
		});
	}

	// On select employee -------------------------------------------------------------------------

	selectEmp(emp?: any) {
		console.log("In select Emp teamRows", this.teamRows);
		for (let index = 0; index < this.teamRows.length; index++) {
			const x = this.teamRows[index];
			var isEmployeeAlreadySelected = this.checkIfEmployeeAlreadyAssigned(x, emp);
			if (isEmployeeAlreadySelected == true) {
				alert("already exists")
				break;
			} else {
				if (x.id == this.tempTeam.id) {
					this.employeeAssigning(x, emp);
					break;
				}
			}
		}
	}

	employeeAssigning(x, emp) {
		let jobPlanEmpSkills = new Array<JobPlanEmpSkill>();
		let jobPlanEmpCommitments = new Array<JobPlanEmpCommitment>();
		x.employeeId = emp.mEmployeeId;
		x.employeeName = emp.mEmployeeName;
		x.comptabilityScore = emp.comptabilityScore;
		x.distanceToLocation = emp.distanceToLocation;

		emp.matchedSkills.forEach(x => {
			let jobPlanEmpSkill = new JobPlanEmpSkill();
			jobPlanEmpSkill.mProfileLineId = x.mProfileLineId;
			jobPlanEmpSkill.isMatched = x.isMatched;
			jobPlanEmpSkills.push(jobPlanEmpSkill);
		})

		emp.employeeCommitments.forEach(x => {
			let jobPlanEmpCommitment = new JobPlanEmpCommitment();
			jobPlanEmpCommitment.commitmentType = x.commitmentType;
			jobPlanEmpCommitment.distanceToLocation = x.distanceToLocation;
			jobPlanEmpCommitment.holidayName = x.holidayName;
			jobPlanEmpCommitment.leaveName = x.leaveName;
			jobPlanEmpCommitment.longs = x.longs;
			jobPlanEmpCommitment.lats = x.lats;
			jobPlanEmpCommitment.fromDt = x.fromDt;
			jobPlanEmpCommitment.toDt = x.toDt;
			jobPlanEmpCommitments.push(jobPlanEmpCommitment);
		})
		x.jobPlanEmpSkills = jobPlanEmpSkills;
		x.jobPlanEmpCommitments = jobPlanEmpCommitments;
		this.employeeListProfileFinder = null;
		this.modalReference.close();
	}

	checkIfEmployeeAlreadyAssigned(team, employee) {
		var result:boolean = false;
		if (employee.mEmployeeId == team.employeeId) {
			var isStart = this.commonService.comparingDates(team.startDt, team.endDt, this.tempTeam.startDt);
			var isEnd = this.commonService.comparingDates(team.startDt, team.endDt, this.tempTeam.endDt);
			if (isStart==false && isEnd==false) {
				result=false
			}else{
				result=true
			}
			return result
		} else {
			return result
		}
	}

	// ---------------------------------------------------------------------------------

	filterJobPlans() {
		this.materialRows.forEach(x => {
			if (x.jobId == undefined || x.jobId == null) {
				this.materialRows.pop()
			}
		});
		this.taskTypeRows.forEach(x => {
			if (x.jobId == undefined || x.jobId == null) {
				this.taskTypeRows.pop()
			}
			x.taskTypeName = null;
		});
		this.profileRows.forEach(x => {
			if (x.tJobId == undefined || x.tJobId == null) {
				this.profileRows.pop()
			}
		});

		this.teamRows.forEach(x => {
			if (x.jobId == undefined || x.jobId == null) {
				this.teamRows.pop();
			}
		});
		this.job.jobPlanEmps = this.teamRows;
		this.job.jobPlanMaterials = this.materialRows;
		this.job.jobPlanTaskTypes = this.taskTypeRows;
		this.job.jobPlanProfiles = this.profileRows;
	}

	saveJob() {
		this.filterJobPlans();



		this.job.projName = null;

		this.service.saveJobPlan(this.job).subscribe(x => {

		});

		this.service.updateJobPlan(this.job, 'save-job-plan-childs').subscribe(x => {

		});
		console.log(this.job.jobPlanEmps)
		this.router.navigateByUrl('/job/job-list');
	}

	confirmJob() {
		this.job.jobPlanEmps = this.teamRows;
		this.job.jobPlanMaterials = this.materialRows;
		this.job.jobPlanTaskTypes = this.taskTypeRows;
		this.service.confirmJobPlan(this.job, 'confirm').subscribe(x => {

		});
	}

	employeeSearch: any = [];

	multiRes(index?: any, team?: JobPlanEmp) {
		console.log(index, team)
		console.log(this.teamRows)
		this.searchProfileId = team.profileId;
		this.tempTeam = team;
		this.profileName = team.profileName;

		this.service.getMasterData('departments').subscribe(x => {
			this.departments = x;
		})
		this.service.getMasterData('employees').subscribe(x => this.employeeSearch = x);
		this.modalReference = this.modalService.open(this.mMulti, { size: 'lg' });
	}

	saleOrderDetail() {
		if (this.job.saleOrderId) {
			this.service.getSaleOrderById(this.job.saleOrderId).subscribe(x => {
				this.so = x;
				this.sg = this.so.saleGoods && this.so.saleGoods.length ? true : false;
				this.ss = this.so.saleServices && this.so.saleServices.length ? true : false;
				this.modalReference = this.modalService.open(this.saleOrder, { size: 'lg' });
			});
		}
	}

	notifiContent() {
		this.modalReference = this.modalService.open(this.notify);
	}

	openSaleOrderModel(saleOrderId?:string){		
		console.log(saleOrderId)
		this.modalReference = this.modalService.open(SaleOrderModelComponent, {size: 'lg'});	
		this.modalReference.componentInstance.id=saleOrderId;
	}
}
