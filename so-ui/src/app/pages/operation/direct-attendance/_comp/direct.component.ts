import { Component, OnInit, ViewChild, TemplateRef, ViewEncapsulation } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Title }     from '@angular/platform-browser';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';

import { AttendanceService } from './../attendance.service';

import { CalendarComponent } from 'ng-fullcalendar';
import { Options } from 'fullcalendar';

const colors: any = {
	red: 'cal-bg-red',
	blue: 'cal-bg-blue',
	yellow: 'cal-bg-yellow',
	green: 'cal-bg-green'
};

const acodes = [{'code': 'WBL', 'name': "Working Day Billable"}, {'code': 'WNB', 'name': "Working Day Non-Billable"}, {'code': 'HBL', 'name': "Holiday Billable"}, {'code': 'HNB', 'name': "Holiday Non-Billable"}, {'code': 'SL', 'name': "Sick Leave"}, {'code': 'WAL', 'name': "Wedding / Adaption Leave"}, {'code': 'PML', 'name': "Paternety / Maternity Leave	"}, {'code': 'ABS', 'name': "Absent"}];

@Component({
    selector:'transaction-attendance-direct-show',
    templateUrl:'direct.component.html',
	styleUrls:[ 'card.css' ],
	encapsulation: ViewEncapsulation.None 
})

export class AttendanceDirectShowComponent implements OnInit{

	calendarOptions: any = {
		editable: true,
		eventLimit: false,
		displayEventTime: false,
		fixedWeekCount: false,
		showNonCurrentDates: false,
		header: {
			left: 'prev,today,next ',
			center: 'title',
		},
		events: []
	};
	
	modalData: any;
	showCalendar: boolean = false;
	allEmployees: any;
	allCodes: any = acodes;
	user: any;
	curMonth: number;
	curYear: number;
	@ViewChild(CalendarComponent) ucCalendar: CalendarComponent;
	@ViewChild('modalContent') modalContent: TemplateRef<any>;
	
	emp = (text$: Observable<string>) =>text$.debounceTime(200).map(term => term === '' ? []
        : this.allEmployees.filter(v => v.empName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10));
	formatter = (x: {empName: string}) => x.empName;
	
    constructor(private router: Router, private activatedRoute: ActivatedRoute, private titleService: Title, private _service: AttendanceService, private modal: NgbModal) { }
    
	ngOnInit(){
		this.user = this._service.getUser();
		this._service.getEmployees().subscribe(x=> {
			this.allEmployees = x;
			
			this.curMonth = (new Date()).getMonth()+1;
			this.curYear = (new Date()).getFullYear();
			
			if (this.activatedRoute.params['_value']['id']) {
				this.activatedRoute.params.subscribe( (params: Params)=>{
					this.showCalendar = true;
					this.getAttendances(params['id']);
				});
			}
		
		});
		
    }
	
	getAttendances(_id, curMonth?: number, curYear?: number){
		this._service.getDirectAttendance(_id, curMonth, curYear).subscribe(x=>{
		
			this.selectedEmp = this.allEmployees.filter(e=>e.id == _id)[0];
			let y = x.filter(item=>item.employeeId==this.selectedEmp.id);
			y.forEach(item=>{
				item.start = new Date(item.attendanceDt);
				item.end = new Date(item.attendanceDt);
				item.className = colors['yellow'];
				item.title = item.firstSession+" && "+item.secondSession;
			});
			this.calendarOptions.events = y;
			
			setTimeout(()=>this.ucCalendar.renderEvents(this.calendarOptions.events), 300);
		});
	}
	
	saveAttendance(){
		this._service.bulkAttendances(this.newEvents).subscribe(x=>{
			this.newEvents = [];
			this.getAttendances(this.selectedEmp.id, this.curMonth, this.curYear);
			
		});
		
	}
	
	selectedEmp: any;
	selEmp(){
		if(this.selectedEmp && this.selectedEmp.id) {
			this.router.navigateByUrl('/transaction/attendance/direct/'+this.selectedEmp.id);
		}
	}

	modalReference: any;
	newEvents: any = [];
	updateModal(modal: any) {
		let m = this.calendarOptions.events.filter(x=>x.attendanceDt == modal.attendanceDt);
		modal.title = modal.firstSession + " && "+ modal.secondSession;
		modal.className = colors['red'];
		modal.edited = true;
		
		if(m && m.length > 0){
			let ind1;
			this.calendarOptions.events.forEach((x, i)=>{
				if(x.id == modal.id) return ind1 = i;
			});
			if(ind1 > 0) this.calendarOptions.events[ind1] = modal;
			
			let ind;
			this.newEvents.forEach((x, i)=>{
				if(x.id == modal.id) return ind = i;
			});
			modal.proxyEmployeeId = this.user.employeeId;
			if(ind >= 0) this.newEvents.splice(ind, 1);
			this.newEvents.push({id: modal.id, employeeId: modal.employeeId, attendanceDt:  modal.attendanceDt, firstSession: modal.firstSession, secondSession: modal.secondSession, attendanceStatus: modal.attendanceStatus, proxyEmployeeId: this.user.employeeId});
		}
		else {
			this.calendarOptions.events.push(modal);
			let m1 = {employeeId: modal.employeeId, attendanceDt:  modal.attendanceDt, firstSession: modal.firstSession, secondSession: modal.secondSession, attendanceStatus: modal.attendanceStatus, proxyEmployeeId: this.user.employeeId}
			this.newEvents.push(m1);
		}

		this.ucCalendar.renderEvents(this.calendarOptions.events);
		this.modalReference.close();
	}
  
    handleEvent($e) {
		this.modalData = $e.event;
		this.modalReference = this.modal.open(this.modalContent, {size: 'lg'});
	}
	addEvent($e) {
		let d = $e.date._d;
		if(d >= new Date()) return;	
		if(d.getMonth() != (this.curMonth-1)) return;
		
		if($e && $e.event) this.modalData = $e.event;
		else{

			let m = this.calendarOptions.events.filter(x=>x.attendanceDt == d.toJSON().substr(0,10));
			this.modalData = (m && m.length > 0) ? m[0] : {start: d, end: d, attendanceDt: d.toJSON().substr(0,10), firstSession: '', secondSession: '', attendanceStatus: '', employeeId: this.selectedEmp.id, className: colors['red']};
		} 
		
		this.modalReference = this.modal.open(this.modalContent, {size: 'lg'});
	}
	afterRender($e){
		
	}
	clickButton($e){
		let d = $e.data._d;
		this.curMonth = (d.getMonth()+1);
		this.curYear = d.getFullYear();
		this.getAttendances(this.selectedEmp.id, this.curMonth, this.curYear);
	}
}