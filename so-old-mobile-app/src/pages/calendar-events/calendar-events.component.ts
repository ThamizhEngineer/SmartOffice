import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { AttendanceService } from '../../services/attendance.service';

@Component({
  selector: 'page-calendar-events',
  templateUrl: 'calendar-events.component.html',
})
export class CalendarEventsPage {

  calendarData:any;

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public attendanceService: AttendanceService) {
  }

  ionViewDidLoad() {
    let selectedDate=this.navParams.get('currentDate');
    this.attendanceService.currentMonthEvents(selectedDate.month).subscribe(x=>{
      this.calendarData=x;
    });
  }

}
