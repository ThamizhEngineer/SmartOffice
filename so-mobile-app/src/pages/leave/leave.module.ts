import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { LeavePage } from '../leave/leave.component';

@NgModule({
  declarations: [
    LeavePage,
  ],
  imports: [
    IonicPageModule.forChild(LeavePage),
  ],
})
export class LeavePageModule {}
