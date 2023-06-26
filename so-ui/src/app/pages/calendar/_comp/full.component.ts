import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
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

	calendarOptions: Options;
	events: any;
	modalData: any;
	@ViewChild(CalendarComponent) ucCalendar: CalendarComponent;
	@ViewChild('modalContent') modalContent: TemplateRef<any>;

	ngOnInit() {
		

		this._service.getData().subscribe(x=>{
			x.forEach(item=>{
				item.start = new Date(item.startDate);
				item.end = new Date(item.endDate);
				item.className = colors[item.colorCode];
			});
			this.calendarOptions = {
				editable: true,
				eventLimit: false,
				displayEventTime: false,
				header: {
					left: 'prev,today,next ',
					center: 'title',
					right: 'month,agendaWeek,agendaDay,listMonth'
				},
				events: x
			};
		});
	}
	
	handleEvent($event) {
		this.modalData = $event;
		this.modal.open(this.modalContent, {size: 'lg'});
	}
}

const colors: any = {
	red: 'cal-bg-red',
	blue: 'cal-bg-blue',
	yellow: 'cal-bg-yellow',
	green: 'cal-bg-green'
};