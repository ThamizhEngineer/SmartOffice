import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { TeamAttendancePage } from './team-attendance.component';

import { PipesModule } from './../../pipes/pipes.module';

@NgModule({
  declarations: [
    TeamAttendancePage,
  ],
  imports: [
    IonicPageModule.forChild(TeamAttendancePage),
    PipesModule
  ],
})
export class TeamAttendancePageModule {}
