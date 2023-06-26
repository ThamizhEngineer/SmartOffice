import { Component, ViewChild } from '@angular/core';
import { App, IonicPage, NavController, NavParams, AlertController,PopoverController } from 'ionic-angular';

import { AttendancePage } from '../attendance/attendance';
import { ExpensePage } from '../expense/expense.component';
import { ExpenseApprovalPage } from '../expense-approval/expense-approval';
import { TeamAttendancePage } from '../team-attendance/team-attendance';
import { LeavePage } from '../leave/leave.component';
import { PayslipPage } from '../payslip/payslip';
import { CalendarPage } from '../calendar/calendar';
import { PopoverPage } from '../popover/popover';

import { AuthenticationService } from '../../providers/authentication.service';
import { LeaveApprovalPage } from '../leave-approval/leave-approval';
import { HolidaysPage } from '../holidays/holidays';


/**
 * Generated class for the HomePage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-home',
  templateUrl: 'home.html',
})
export class HomePage {

  currentUser: any;
  params: any = {};
  showSearchBar:boolean=false;

  @ViewChild('searchBar') searchBar : any;

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public alertCtrl: AlertController,
              public popoverCtrl: PopoverController,
              public authService: AuthenticationService,
              public app: App) { 
                let user =JSON.parse(localStorage.getItem('currentUser'));
                if(user){
                  this.currentUser = user.firstName +' '+ user.lastName;
                }
                this.params.data = [
                  { title: "Attendance",      icon: "far fa-lg fa-calendar-check",  page: AttendancePage      , display:true, bgcolor:"tomato" ,        color:"white" },
                  { title: "Team Attendance", icon: "fas fa-lg fa-users",           page: TeamAttendancePage  , display:true, bgcolor:"BlueViolet" ,    color:"white" },
                  { title: "Leave",           icon: "far fa-lg fa-calendar-times",  page: LeavePage           , display:true, bgcolor:"RoyalBlue" ,     color:"white" },
                  { title: "Leave Approval",  icon: "far fa-lg fa-edit",            page: LeaveApprovalPage   , display:true, bgcolor:"Orange" ,        color:"white" },
                  { title: "Payslip",         icon: "fas fa-lg fa-rupee-sign",      page: PayslipPage         , display:true, bgcolor:"DodgerBlue" ,    color:"white" },
                  { title: "Holidays",        icon: "fas fa-lg fa-plane-departure", page: HolidaysPage        , display:true, bgcolor:"brown" ,         color:"white" },
                  { title: "Calendar",        icon: "far fa-lg fa-calendar-alt",    page: CalendarPage        , display:true, bgcolor:"BlueViolet" ,    color:"white" },
                  { title: "Expense Claim",   icon: "far fa-lg fa-credit-card",     page: ExpensePage         , display:true, bgcolor:"SlateBlue" ,     color:"white" },
                  { title: "Expense Approval",icon: "fas fa-lg fa-hand-holding-usd",page: ExpenseApprovalPage , display:true, bgcolor:"Orange" ,        color:"white" }
                ];
              }
         

  ionViewDidLoad() {
    console.log('ionViewDidLoad HomePage');
  }

  focusButton(){
    setTimeout(() => {
      this.searchBar.setFocus();
    }, 100);
  }

  searchText(e){
    console.log(e);
  }

  onCancel(e){
    console.log(e);
    this.showSearchBar=false;
  }

  moreActions(event){
    console.log(event);
    let popover = this.popoverCtrl.create(PopoverPage);
    popover.present({
      ev: event
    });
  }

  checkBlur(e){
    this.showSearchBar=false;
  }
}
