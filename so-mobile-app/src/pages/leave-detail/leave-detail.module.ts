import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { LeaveDetailPage } from '../leave-detail/leave-detail.component';
import{LeaveService} from '../../services/leave.service';
@NgModule({
  declarations: [
    LeaveDetailPage,
  ],
  imports: [
    IonicPageModule.forChild(LeaveDetailPage),
  ],
  providers:[ LeaveService]
})
export class LeavePageDetailModule {}
