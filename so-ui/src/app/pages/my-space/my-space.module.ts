import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { MySpaceRoutes } from './my-space.routing';

import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule } from './../../shared/shared.module';
import { PayslipsComponent } from './_comp/payslips.component';
import { MySkillComponent } from './my-skill/comp/my-skill.component';
import { EmployeePayoutService } from './employee-payout.service';
import { CommonService } from '../../shared/common/common.service';
import { MySkillService } from './my-skill/my-skill.service';
import { TypeaheadPopupDirective } from './popup.directive';
@NgModule({
  imports: [
    RouterModule.forChild(MySpaceRoutes),NgbModule,FormsModule,CommonModule,SharedModule
  ],
  providers: [ EmployeePayoutService, CommonService,MySkillService ],
  declarations: [ PayslipsComponent,MySkillComponent,TypeaheadPopupDirective ]
})
export class MySpaceModule { }
