import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { MainPage } from './main';

import { DashboardPageModule } from './../dashboard/dashboard.module';
import { AttendancePageModule } from './../attendance/attendance.module';
import { ExpensePageModule } from './../expense/expense.module';
import { TeamAttendancePageModule } from './../team-attendance/team-attendance.module';
import { LeavePageModule } from './../leave/leave.module';
import { PayslipPageModule } from './../payslip/payslip.module';
import { CalendarPageModule } from './../calendar/calendar.module';
import { TasksPageModule } from './../tasks/tasks.module';
import { TaskDetailsPageModule } from './../task-details/task-details.module';
import { HolidaysPageModule } from './../holidays/holidays.module';
import { LeaveApprovalPageModule } from './../leave-approval/leave-approval.module';
import { ExpenseApprovalPageModule } from './../expense-approval/expense-approval.module';

import { TabsService } from './../../services/tabs-service';


@NgModule({
  declarations: [
    MainPage,
  ],
  imports: [
    IonicPageModule.forChild(MainPage),
    DashboardPageModule,
    AttendancePageModule,
    ExpensePageModule,
    TeamAttendancePageModule,
    LeavePageModule,
    PayslipPageModule,
    CalendarPageModule,
    TasksPageModule,
    TaskDetailsPageModule,
    HolidaysPageModule,
    LeaveApprovalPageModule,
    ExpenseApprovalPageModule
  ],
  providers:[
    TabsService
  ]
})
export class MainPageModule {}
