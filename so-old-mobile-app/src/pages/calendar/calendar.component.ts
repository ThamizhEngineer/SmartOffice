import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { Calendar } from '@ionic-native/calendar';

import { AttendanceService } from '../../services/attendance.service';
import { CalendarEventsPage } from '../calendar-events/calendar-events.component';

@Component({
  selector: 'page-calendar',
  templateUrl: 'calendar.component.html',
})
export class CalendarPage {

  date: any;
  daysInThisMonth: any;
  daysInLastMonth: any;
  daysInNextMonth: any;
  monthNames: string[];
  currentMonth:any;
  currentMonthInAlpha: any;
  allEvents:any;
  attendanceMonth:any;
  currentYear: any;
  currentDate: any;
  eventList: any;
  selectedEvent: any;
  isSelected: any;
  todayDate: string;
  calendarData:any;

  constructor(public navCtrl: NavController, 
              public navParams: NavParams, 
              public calendar: Calendar,
              public attendanceService: AttendanceService) {
                  
  }

  ionViewDidLoad() {
    this.date = new Date();
    this.todayDate=new Date().toISOString();
    console.log("this.todayDate",this.todayDate);
    this.monthNames = ["January","February","March","April","May","June","July","August","September","October","November","December"];
    this.getDaysOfMonth();
  }

  loadCurrentMonthAllEvents(attendanceMonth){
    this.attendanceService.currentMonthEvents(attendanceMonth).subscribe(x=>{
      this.allEvents=x;
    });
  }


  loadMyCalendar(currentDate:any){
    let selectedDate=new Date(currentDate.year,(currentDate.month-1),(currentDate.date+1)).toISOString();
    this.attendanceService.myCalendar(selectedDate).subscribe(x=>{
      this.calendarData=x;
    });
    this.navCtrl.push(CalendarEventsPage,{
      'currentDate':currentDate
    });
  }

  

  getDaysOfMonth() {
    this.daysInThisMonth = new Array();
    this.daysInLastMonth = new Array();
    this.daysInNextMonth = new Array();
    this.currentMonth=this.date.getMonth()+1;
    this.currentMonthInAlpha = this.monthNames[this.date.getMonth()];
    this.currentYear = this.date.getFullYear();
    this.loadCurrentMonthAllEvents(this.currentMonth); 
    if(this.date.getMonth() === new Date().getMonth()) {
      this.currentDate = new Date().getDate();
    } else {
      this.currentDate = 999;
    }
  
    //Last Month
    var firstDayThisMonth = new Date(this.date.getFullYear(), this.date.getMonth(), 1).getDay();
    var prevNumOfDays = new Date(this.date.getFullYear(), this.date.getMonth(), 0).getDate();
    for(let i = prevNumOfDays-(firstDayThisMonth-1); i <= prevNumOfDays; i++) {
      this.daysInLastMonth.push(i);
    }
  
    //This Month
    var thisNumOfDays = new Date(this.date.getFullYear(), this.date.getMonth()+1, 0).getDate();
    for (let i = 1; i <= thisNumOfDays; i++) {
      this.daysInThisMonth.push({date:i, month:this.currentMonth,year:this.currentYear,event:''});
      let tempDate =this.date.getFullYear()+"-"+((this.date.getMonth()+1)<10 ? "0"+(this.date.getMonth()+1) :(this.date.getMonth()+1)) +"-"+(i<10 ? "0"+i : i);
      if(this.allEvents){
        for(let event of this.allEvents){
          if(tempDate==event.attendanceDt){
            let index=this.daysInThisMonth.indexOf(i);
            this.daysInThisMonth.splice(index,1);
            this.daysInThisMonth.push({
              date:i, 
              month:this.currentMonth,
              year:this.currentYear,
              event:(event.checkIn)?("CheckIn :"+ event.checkIn+";"+"CheckOut :"+ event.checkOut ):event.firstSessionCodeDesription
            });
          }
        }
      }
    }

    //Next Month
  
    var lastDayThisMonth = new Date(this.date.getFullYear(), this.date.getMonth()+1, 0).getDay();
    for (let i = 0; i < (6-lastDayThisMonth); i++) {
      this.daysInNextMonth.push(i+1);
    }
    var totalDays = this.daysInLastMonth.length+this.daysInThisMonth.length+this.daysInNextMonth.length;
    if(totalDays<36) {
      for(let i = (7-lastDayThisMonth); i < ((7-lastDayThisMonth)+7); i++) {
        this.daysInNextMonth.push(i);
      }
    }
    
  }

  goToNextMonth() {
    this.date = new Date(this.date.getFullYear(), this.date.getMonth()+2, 0);
    this.getDaysOfMonth();
    this.loadCurrentMonthAllEvents(this.currentMonth);
  }

  goToLastMonth() {
    this.date = new Date(this.date.getFullYear(), this.date.getMonth(), 0);
    this.getDaysOfMonth();
    this.loadCurrentMonthAllEvents(this.currentMonth);
  }

  loadEventThisMonth() {
    this.eventList = new Array();
    var startDate = new Date(this.date.getFullYear(), this.date.getMonth(), 1);
    var endDate = new Date(this.date.getFullYear(), this.date.getMonth()+1, 0);
    this.calendar.listEventsInRange(startDate, endDate).then(
      (msg) => {
        msg.forEach(item => {
          this.eventList.push(item);
        });
      },
      (err) => {
        console.log(err);
      }
    );
  }

  checkEvent(day) {
    var hasEvent = false;
    var thisDate1 = this.date.getFullYear()+"-"+(this.date.getMonth()+1)+"-"+day+" 00:00:00";
    var thisDate2 = this.date.getFullYear()+"-"+(this.date.getMonth()+1)+"-"+day+" 23:59:59";
    this.eventList.forEach(event => {
      if(((event.startDate >= thisDate1) && (event.startDate <= thisDate2)) || ((event.endDate >= thisDate1) && (event.endDate <= thisDate2))) {
        hasEvent = true;
      }
    });
    return hasEvent;
  }

  selectDate(day) {
    this.isSelected = false;
    this.selectedEvent = new Array();
    var thisDate1 = this.date.getFullYear()+"-"+(this.date.getMonth()+1)+"-"+day+" 00:00:00";
    var thisDate2 = this.date.getFullYear()+"-"+(this.date.getMonth()+1)+"-"+day+" 23:59:59";
    this.eventList.forEach(event => {
      if(((event.startDate >= thisDate1) && (event.startDate <= thisDate2)) || ((event.endDate >= thisDate1) && (event.endDate <= thisDate2))) {
        this.isSelected = true;
        this.selectedEvent.push(event);
      }
    });
  }
}