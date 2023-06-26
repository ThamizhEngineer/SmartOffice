import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { MySpaceRoutes } from './my-space.routing';

// import { PayslipsComponent } from './_comp/payslips.component';
// import { EmployeePayoutService } from './employee-payout.service';
import { CommonService } from '../../shared/common/common.service';

@NgModule({
  imports: [
    RouterModule.forChild(MySpaceRoutes), CommonModule
  ],
  providers: [  CommonService ],
  declarations: [  ]
})
export class MySpaceModule { }
