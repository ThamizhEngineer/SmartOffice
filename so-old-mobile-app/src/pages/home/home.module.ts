import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { BackgroundGeolocation } from '@ionic-native/background-geolocation';

import { HomePage } from './home.component';

import { DashboardPageModule } from './../../pages/dashboard/dashboard.module';
import { AttendancePageModule } from './../attendance/attendance.module';
import { ExpensePageModule } from './../expense/expense.module';
import { ExpenseTrackerPageModule } from './../expense-tracker/expense-tracker.module';
import { ChatRoomPageModule } from './../chat-room/chat-room.module';
import { ChatHomePageModule } from './../chat-home/chat-home.module';
import { TeamAttendancePageModule } from './../team-attendance/team-attendance.module';
import { CalendarPageModule } from './../calendar/calendar.module';

@NgModule({
  declarations: [
    HomePage
  ],
  imports: [
    IonicPageModule.forChild(HomePage),
    DashboardPageModule,
    AttendancePageModule,
    ExpensePageModule,
    ExpenseTrackerPageModule,
    ChatRoomPageModule,
    ChatHomePageModule,
    TeamAttendancePageModule,
    CalendarPageModule
  ],
  providers:[
    BackgroundGeolocation
  ]
})
export class HomePageModule {}
