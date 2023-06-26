import { Component, OnInit, ViewChild, TemplateRef, ViewEncapsulation } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { Title }     from '@angular/platform-browser';
import { CalendarService } from './../calendar.service';

import { CalendarComponent } from 'ng-fullcalendar';
import { Options } from 'fullcalendar';

const dateObj = new Date();
const yearMonth = dateObj.getUTCFullYear() + '-' + (dateObj.getUTCMonth() + 1);

@Component({
  selector: 'app-cal-full',
  templateUrl: './full.component.html'
})
export class CalendarFullComponent {

	constructor(private titleService: Title, private _service: CalendarService, private modal: NgbModal) { }

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
	@ViewChild(CalendarComponent) ucCalendar: CalendarComponent;
	@ViewChild('modalContent') modalContent: TemplateRef<any>;

	ngOnInit() {
		let user = this._service.getUser();
		
		this._service.getMultipleData(user.employeeId).subscribe(x=>{
			this.attendance = x[0];
			this.holidays = x[1];
			
			this.attendance.forEach(item => {
				item.start = new Date(item.attendanceDt);
				item.end = new Date(item.attendanceDt);
				item.className = colors['green'];
				item.title = item.firstSession+" && "+item.secondSession;
			})
				
			this.holidays.forEach(item => {
				item.start = new Date(item.holidayDt);
				item.end = new Date(item.holidayDt);
				item.className = colors['yellow'];
				item.title = item.holidayName;
			})
			
			this.loadCalendar([...this.attendance, ...this.holidays]);
		});
	}
	
	attendance: any = [];
	holidays: any = [];
	
	loadCalendar(events){
		this.calendarOptions.events = events;			
		setTimeout(()=>this.ucCalendar.renderEvents(this.calendarOptions.events), 300);
	}
}

const colors: any = {
	red: 'cal-bg-red',
	blue: 'cal-bg-blue',
	yellow: 'cal-bg-yellow',
	green: 'cal-bg-green'
};