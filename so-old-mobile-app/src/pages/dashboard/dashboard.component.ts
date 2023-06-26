import { Component, ViewChild } from '@angular/core';
import { NavController , Searchbar} from 'ionic-angular';

import { AttendancePage } from '../attendance/attendance.component';

@Component({
  selector: 'page-dashboard',
  templateUrl: 'dashboard.component.html'
})
export class DashboardPage {

  showSearchbar:boolean;
  showErrorMsg:boolean=false;
  searchInput:string=null;
  currentUser:any=null;
  checkInDetails:any=null;
  checkInTime:any;
  checkInLocation:string;
  currentJobNo:number;
  currentJobLocation:string;
  currentJobDuration:any;
  upcomingJobNo:number;
  upcomingJobLocation:string;
  upcomingJobDuration:any;
  upcomingJobStartDate:any;
  arrivedDate:string=new Date().toISOString();
  departedDate:string=new Date().toISOString();

  @ViewChild('searchbar') searchbar:Searchbar;

  constructor(public navCtrl: NavController) {}

  attendance(){
    this.navCtrl.push(AttendancePage);
  }

  hideShowSearch(){
    this.showSearchbar=!this.showSearchbar;
    setTimeout(() => {
      this.searchbar.setFocus();
    }, 0);
  }
  onCancel(event){
    this.showSearchbar=!this.showSearchbar;
    this.searchInput=null;
  }

  calculateDuration(minDate,maxDate){
    let duration=Date.parse(maxDate)-Date.parse(minDate);
    let days= (duration/(60*60*24*1000));
    return days;
  }

  ionViewDidLoad(){
    if(localStorage.getItem('currentUser')){
      this.currentUser=JSON.parse(localStorage.getItem('currentUser'));
      
      if(this.currentUser.currentJobSummary){
        this.currentJobNo=this.currentUser.currentJobSummary.jobCode;
        this.currentJobLocation=this.currentUser.currentJobSummary.jobLocation;
        this.currentJobDuration=this.calculateDuration(this.currentUser.currentJobSummary.startDate,this.currentUser.currentJobSummary.endDate);
      }

      if(this.currentUser.upcomingJobSummary){
        this.upcomingJobNo=this.currentUser.upcomingJobSummary.jobCode;
        this.upcomingJobLocation=this.currentUser.upcomingJobSummary.jobLocation;
        this.upcomingJobDuration=this.calculateDuration(this.currentUser.upcomingJobSummary.startDate,this.currentUser.upcomingJobSummary.endDate)
        this.upcomingJobStartDate=this.currentUser.upcomingJobSummary.startDate;
      }
    } 
  }

  ionViewDidEnter(){
    this.showSearchbar=false;
    if(localStorage.getItem('checkInTime')){
      this.showErrorMsg=false;
      this.checkInTime=localStorage.getItem('checkInTime');
      this.checkInLocation=localStorage.getItem('checkInLocation');
    }else{
      this.showErrorMsg=true;
    }
  }
}
