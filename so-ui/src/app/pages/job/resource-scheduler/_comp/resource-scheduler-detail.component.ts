import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Title } from '@angular/platform-browser';
import { ResourceSchedulerService } from '../resource-scheduler.service';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { Job, JobTravel, JobAccomdation, JobEmployee } from '../../vo/job';
import { Employee } from '../../../vo/employee';
import { ProfileFinderJob } from '../../vo/profile-finder-job'
import { Router, ActivatedRoute, Params } from '@angular/router';
import { DatePipe } from '@angular/common';
import { JobService } from '../../job.service';
import { SaleOrderModelComponent } from '../../../../shared/_models/sale-order-module/sale-order-model.component';
const travels = { 'pending': { 'icon': 'fa-times-circle', 'name': "Yet to Book", 'style': 'text-danger' }, 'booked': { 'icon': 'fa-check-circle', 'name': "Booked", 'style': 'text-success' }, 'progress': { 'icon': 'fa-info-circle', 'name': "Booking In Progress", 'style': 'text-dark' }, 'remote': { 'icon': 'fa-minus', 'name': "Remote", 'style': 'text-dark' } };

const accomodations = { 'pending': { 'icon': 'fa-times-circle', 'name': "Yet to Book", 'style': 'text-danger' }, 'booked': { 'icon': 'fa-check-circle', 'name': "Booked", 'style': 'text-success' }, 'progress': { 'icon': 'fa-info-circle', 'name': "Booking In Progress", 'style': 'text-dark' }, 'remote': { 'icon': 'fa-minus', 'name': "Remote", 'style': 'text-dark' } };


const reporteds = { 'remote': { 'icon': 'fa-minus', 'name': "Remote", 'style': 'text-dark' }, 'no': { 'icon': 'fa-window-close', 'name': "Not Reported", 'style': 'text-danger' }, 'yes': { 'icon': 'fa-check-square', 'name': "Reported", 'style': 'text-success' } };
const departeds = { 'remote': { 'icon': 'fa-minus', 'name': "Remote", 'style': 'text-dark' }, 'no': { 'icon': 'fa-window-close', 'name': "Not Departed", 'style': 'text-danger' }, 'yes': { 'icon': 'fa-check-square', 'name': "Departed", 'style': 'text-success' } };
const projects = ["Project 1", "Project 2", "Project 3"];
const locations = ["Chennai", "Gujarat", "Ahmedabad"];
const profiles = ["Field Engineer Expert", "Surveillance Engineer", "Onsite Project Coordinater", "Profile 1", "Profile 2", "Profile 3"];
@Component({
	selector: 'app-resource-scheduler-detail',
	templateUrl: './resource-scheduler-detail.component.html',
	providers: [DatePipe, JobService]
})
export class ResourceSchedulerDetailComponent implements OnInit {

	details: any;
	@ViewChild('maccomodation') maccomodation: TemplateRef<any>;
	@ViewChild('mtravel') mtravel: TemplateRef<any>;
	@ViewChild('reportStatus') reportStatus: TemplateRef<any>;
	@ViewChild('mMulti') mMulti: TemplateRef<any>;
	accomodation: any;
	travel: any;
	travels = travels;
	profileFinderJob: ProfileFinderJob;
	accomodations = accomodations;
	reporteds = reporteds;
	departeds = departeds;
	profileId: string;
	isTravelComplete: boolean = false;
	isAccomdationComplete: boolean = false;
	isReportedComplete: boolean = false;
	isDepartedComplete: boolean = false;
	employee: Employee;
	job: Job;
	jobTravels: Array<JobTravel>;
	jobAccomdations: Array<JobAccomdation>;
	jobEmployee: JobEmployee;
	projects: any = [];
	prof: any = [];
	jobEmployeeId: any;
	

	projectAC = (text$: Observable<string>) => text$.pipe(debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : projects.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)));
	locationAC = (text$: Observable<string>) => text$.pipe(debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : locations.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)));
	profileAC = (text$: Observable<string>) => text$.pipe(debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : profiles.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)));


	profile = (text$: Observable<string>) =>
		text$.pipe(
			debounceTime(200),
			distinctUntilChanged(),
			map(term => (term === '' ? this.prof
				: this.prof.filter(v => v.profileName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10)));
	// profiles: any;
	productFormatter = (x: { profileName: string }) => { x.profileName };
	accDate: string;
	constructor(private router: Router, private datePipe: DatePipe, private route: ActivatedRoute, private titleService: Title, private _service: ResourceSchedulerService, private modalService: NgbModal,
		private _jobService: JobService) { }

	ngOnInit() {

		this.job = new Job();
		this.jobTravels = [new JobTravel];
		this.jobAccomdations = [new JobAccomdation];
		this.jobEmployee = new JobEmployee();
		this.profileFinderJob = new ProfileFinderJob();
		this.employee = new Employee();
		if (this.route.params['_value']['_id']) {
			this.route.params.switchMap((par: Params) => this._service.getJobLogisticsByJobId(par['_id'])).subscribe(x => {
				this.job = x;
				console.log(this.job)
				if (this.job.jobEmployees.length == 0) {
					var newJobEmployee = new JobEmployee();
					newJobEmployee.tJobId = this.job.id;
					newJobEmployee.isJobAccComplete = "N";
					newJobEmployee.isJobTravelComplete = "N";
					newJobEmployee.hasDeparted = "N";
					newJobEmployee.hasDeparted = "N";
					this.job.jobEmployees.push(newJobEmployee);
				}
			});
		}

		this._service.getMasterData('profiles').subscribe(x => {
			this.prof = x
		});
	}

	modalReference: any = null;
	modalData: any;
	employees: any = [];
	departments: any = [];
	employeeListProfileFinder: any = [];
	selectedProfile: any;

	onProductSelect($event, i) {
		console.log($event)
		this.employee.employeeSkills[i].mproductId = $event.item.id
		this.employee.employeeSkills[i].productName = $event.item.materialName
	}

	itemSelected($event, j: JobEmployee) {
		console.log($event)
		j.tJobProfileId = $event.item.id;
		j.jobProfileName = $event.item.profileName;
	}

	selectEmpDt(jobEmployee: JobEmployee) {
		this._service.addEmployee(this.job.id, this.job).subscribe(x => {
			this.getJobByID();
		});
	}

	
	

	selectProfile(jobEmployee?: JobEmployee, tJobProfileId?: any) {
		if (tJobProfileId.id != null) {
			jobEmployee.tJobProfileId = tJobProfileId.id;
			this._service.addEmployee(this.job.id, this.job).subscribe(x => {
				this.getJobByID();
			});
		}
	}
	empName: string="";
	jobEmpId: string;

	addTRows() {
		let t = new JobTravel();
		t.tJobEmpId = this.jobEmpId;
		t.tJobId = this.job.id;
		this.jobTravels.push(t);
	}


	delTRow(item) {
		this._service.deleteTravel(item.tJobId, item.tJobEmpId, item.id).subscribe(x => {
			this.getJobByID();
		},
			error => {
				this.getJobByID();
			});
		this.modalReference.close();
	}

	travelDetail(jobEmployee?: JobEmployee) {
		this.empName = jobEmployee.employeeName;
		this.jobEmpId = jobEmployee.id;
		if (jobEmployee.isJobTravelComplete == 'Y') {
			this.isTravelComplete = true;
		} else {
			this.isTravelComplete = false;
		}
		this.jobTravels = this.job.jobTravels.filter(x => x.tJobEmpId == jobEmployee.id);
		if (this.jobTravels.length == 0) {
			let t = new JobTravel();
			t.tJobEmpId = jobEmployee.id;
			t.tJobId = this.job.id;
			// console.log(t)
			this.jobTravels.push(t);
		}
		this.modalReference = this.modalService.open(this.mtravel, { size: 'lg' });
	}
	selectTravelTime(jobTravels:JobTravel){
		this.travel = jobTravels;
		console.log(this.travel)
	}

	saveTravel() {
		this.travel.travelTime=this.travel.travelTime+" 00:00:00";
		this._service.addAndUpdateTravel(this.job.id, this.jobEmpId, this.travel).subscribe(x => {
			this.getJobByID();
		});
		this.modalReference.close();
	}

	completeTravel() {
		this._service.completeTravel(this.job.id, this.jobEmpId).subscribe(x => {
			this.getJobByID();
		});
		this.modalReference.close();
	}

	addRows() {
		let a = new JobAccomdation();
		a.jobEmpId = this.jobEmpId;
		a.jobId = this.job.id;
		this.jobAccomdations.push(a);
	}

	delRow(item) {
		this._service.deleteAccommodation(item.jobId, item.jobEmpId, item.id).subscribe(x => {
			this.getJobByID();
		},
			error => {
				this.getJobByID();
			});
		this.modalReference.close();

	}
	delProfile(jobEmployee?: JobEmployee) {
		this._service.deleteEmployee(this.job.id, jobEmployee.id).subscribe(x => {
			this.getJobByID();
		},
			error => {
				this.getJobByID();
			});

	}

	selectaccDate(jobAccomdations:JobAccomdation){
		this.accomodation = jobAccomdations;

	}
	saveAccomdation() {
		this.accomodation.accDate=this.accomodation.accDate+ " 00:00:00"
		this._service.addAndupdateAccommodation(this.job.id, this.jobEmpId, this.accomodation).subscribe(x => {
			this.getJobByID();
		});
		this.modalReference.close();
	}

	completeAccomdation() {

		this._service.completeAccommodation(this.job.id, this.jobEmpId).subscribe(x => {
			this.getJobByID();
		});
		this.modalReference.close();
	}

	accomodationDetail(jobEmployee?: JobEmployee) {
		this.empName = jobEmployee.employeeName;
		this.jobEmpId = jobEmployee.id;
		if (jobEmployee.isJobAccComplete == 'Y') {
			this.isAccomdationComplete = true;
		} else {
			this.isAccomdationComplete = false;
		}
		this.jobAccomdations = this.job.jobAccomodations.filter(x => x.jobEmpId == jobEmployee.id);

		if (this.jobAccomdations.length == 0) {
			let a = new JobAccomdation();
			a.jobEmpId = jobEmployee.id;
			a.jobId = this.job.id;
			this.jobAccomdations.push(a);
		}
		this.modalReference = this.modalService.open(this.maccomodation, { size: 'lg' });
	}

	getJobByID() {
		this._service.getJobLogisticsByJobId(this.job.id).subscribe(x => {
			this.job = x;
		});
	}

	addProfile() {
		var newJobEmployee = new JobEmployee();
		newJobEmployee.tJobId = this.job.id;
		newJobEmployee.isJobAccComplete = "N";
		newJobEmployee.isJobTravelComplete = "N";
		newJobEmployee.hasDeparted = "N";
		newJobEmployee.hasDeparted = "N";
		this.job.jobEmployees.push(newJobEmployee);
	}
	multiRes(jobEmployee?: JobEmployee) {
		this._service.getAllDepartments().subscribe(x => {
			this.departments = x;
		});

		this._jobService.getJobs('employees').subscribe(x => this.employees = x);
		this.jobEmployeeId = jobEmployee.id;
		this.profileName = jobEmployee.jobProfileName;
		this.empStartDt = jobEmployee.expStartDt;
		this.empEndDt = jobEmployee.expEndDt;
		this.job.jobProfiles.forEach(x => {
			if (x.id == jobEmployee.tJobProfileId) {
				this.searchProfileId = x.profileId;
			}
		})
		this.modalReference = this.modalService.open(this.mMulti, { size: 'lg' });
	}

	searchEmployeeId: string;
	searchDeptId: string;
	searchProfileId: string;
	profileName: string;
	empStartDt: any;
	empEndDt: any;
	searchEmployee() {
		// console.log(this.searchEmployeeId)
		this.profileFinderJob.fromDt = this.job.startDt;
		this.profileFinderJob.toDt = this.job.endDt;
		this.profileFinderJob.mapLocationId = this.job.mapLocationId;
		this.profileFinderJob.mEmployeeId = this.searchEmployeeId;
		this.profileFinderJob.mDepartmentId = this.searchDeptId;
		this.profileFinderJob.mProfileId = this.searchProfileId;
		// this.profileFinderJob.mProfileId = 
		this._service.addProfileFinder(this.profileFinderJob).subscribe(x => {


			this.profileFinderJob = x;
			this._service.startProfileFinder(this.profileFinderJob).subscribe(x => {
				// console.log(x)
				this.profileFinderJob = x;
				this.employeeListProfileFinder = this.profileFinderJob.matchedEmployees;

			})
		});

		// this._service.getProfileFinder("95").subscribe(x=>{
		// 	console.log(x)
		// 	this.profileFinderJob =x;
		// 			this.employeeListProfileFinder =this.profileFinderJob.matchedEmployees;
		// })


	}

	selectEmp(emp?: any) {


		this.job.jobEmployees.forEach(e => {
			if (e.id == this.jobEmployeeId) {

				e.employeeId = emp.mEmployeeId;

				this._service.addEmployee(this.job.id, this.job).subscribe(x => {

					this.getJobByID();

				});
			}


		})
		this.modalReference.close();
	}

	saleOrderDetail(saleOrderId?: string) {
		console.log(saleOrderId)
		this.modalReference = this.modalService.open(SaleOrderModelComponent, { size: 'lg' });
		this.modalReference.componentInstance.id = saleOrderId;
	}

	reportedFlag: any;
	reportDetail(jobEmployee?: JobEmployee, type?: any) {
		this.reportedFlag = type;
		this.empName = jobEmployee.employeeName;
		this.jobEmployee = this.job.jobEmployees.filter(x => x.id == jobEmployee.id)[0];
		this.modalReference = this.modalService.open(this.reportStatus, { size: 'lg' });
	}

	reportedSave(jobEmpId?: any) {
		if (this.reportedFlag == 'REPORTED') {
			this._service.reported(this.job.id, jobEmpId).subscribe(x => {
				this.getJobByID();
			});
			this.modalReference.close();
		} else if (this.reportedFlag == 'DEPARTED') {
			this._service.departed(this.job.id, jobEmpId).subscribe(x => {
				this.getJobByID();
			});
			this.modalReference.close();
		}
	}


	reported() {
		this._service.reported(this.job.id, this.jobEmpId).subscribe(x => {
			this.getJobByID();
		});
		this.modalReference.close();
	}

	departed() {
		this._service.departed(this.job.id, this.jobEmpId).subscribe(x => {
			this.getJobByID();
		});
		this.modalReference.close();
	}
	employeeSelected($event){
		this.searchEmployeeId = $event.id;
		this.empName = $event.empName;
		
	}

}
