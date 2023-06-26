import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from './../../../shared/shared.module';


import { PayslipsListComponent } from './_comp/list.component';
import { PayslipsDetailComponent } from './_comp/detail.component';
import { PayslipsNewComponent } from './_comp/new.component';
import { PayslipsService } from './payslips.service';

import { PayslipsRoutes } from './payslips.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonService } from '../../../shared/common/common.service';

@NgModule({
  imports: [
    CommonModule, FormsModule, NgbModule, SharedModule,
    RouterModule.forChild(PayslipsRoutes)
  ],
  providers:[PayslipsService, CommonService],
  declarations: [ 
    PayslipsListComponent, PayslipsDetailComponent, PayslipsNewComponent
  ]
})
export class PayslipsModule { }