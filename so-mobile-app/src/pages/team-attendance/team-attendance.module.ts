import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { TeamAttendancePage } from './team-attendance';

@NgModule({
  declarations: [
    TeamAttendancePage,
  ],
  imports: [
    IonicPageModule.forChild(TeamAttendancePage),
  ],
})
export class TeamAttendancePageModule {}
