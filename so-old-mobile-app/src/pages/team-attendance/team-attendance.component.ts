import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { AttendanceService } from '../../services/attendance.service';

@Component({
  selector: 'page-team-attendance',
  templateUrl: 'team-attendance.component.html',
})
export class TeamAttendancePage {

  todayDate: string;
  attendanceData:any;

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public attendanceService: AttendanceService) {
    
  }

  ionViewDidEnter(){
    this.todayDate=new Date().toISOString();
    console.log("this.todayDate",this.todayDate);
    this.loadTeamAttendance(this.todayDate);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad TeamAttendancePage');
  }

  loadTeamAttendance(currentDate:any){
    this.attendanceService.teamAttendance(currentDate).subscribe(x=>{
      this.attendanceData=x;
      console.log("attendance",this.attendanceData);
    });
  }

  previousDay(){
    let newDate=new Date(this.todayDate);
    this.todayDate=new Date(newDate.setDate(newDate.getDate()-1)).toISOString();
    console.log("previous Date",this.todayDate);
    this.loadTeamAttendance(this.todayDate);
  }

  nextDay(){
    let newDate=new Date(this.todayDate);
    this.todayDate=new Date(newDate.setDate(newDate.getDate()+1)).toISOString();
    console.log("next Date",this.todayDate);
    this.loadTeamAttendance(this.todayDate);

  }



}
