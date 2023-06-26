import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

import { MySpaceService } from './../../providers/my-space.service';
import { CommonService } from './../../providers/common.service';

/**
 * Generated class for the TeamAttendancePage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-team-attendance',
  templateUrl: 'team-attendance.html',
})
export class TeamAttendancePage {

  todayDate: string;
  attendanceData:any;

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public mySpaceService: MySpaceService,
              public commonService: CommonService) {
                this.todayDate=this.commonService.formatDate(new Date());
                console.log("this.todayDate",this.todayDate);
                this.loadTeamAttendance(this.todayDate);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad TeamAttendancePage');
  }

  loadTeamAttendance(currentDate:any){
    this.mySpaceService.getMyTeamAttendance(currentDate).subscribe(x=>{
      this.attendanceData=x;
      console.log("attendance",this.attendanceData);
    },err=>{
      console.log('err',err);
    });
  }

  previousDay(){
    let newDate=new Date(this.todayDate);
    let yesterdayDate=new Date(newDate.setDate(newDate.getDate()-1));
    this.todayDate=this.commonService.formatDate(yesterdayDate);
    console.log("previous Date",this.todayDate);
    this.loadTeamAttendance(this.todayDate);
  }

  nextDay(){
    let newDate=new Date(this.todayDate);
    let tomorrowDate=new Date(newDate.setDate(newDate.getDate()+1));
    this.todayDate=this.commonService.formatDate(tomorrowDate);
    console.log("next Date",this.todayDate);
    this.loadTeamAttendance(this.todayDate);

  }

}
