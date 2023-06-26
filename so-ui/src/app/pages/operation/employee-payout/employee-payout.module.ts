import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from './../../../shared/shared.module';

import { EmployeePayoutDetailComponent } from './_comp/employee-payout-detail.component';
import { EmployeePayoutListComponent } from './_comp/employee-payout-list.component';
import { EmployeePayoutRoutes } from './employee-payout.routing';
import { EmployeePayoutService } from './employee-payout.service';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FullCalendarModule } from 'ng-fullcalendar';
import { CommonService } from '../../../shared/common/common.service';

@NgModule({
    imports: [
        CommonModule, NgbModule, FormsModule,SharedModule, FullCalendarModule,
        RouterModule.forChild(EmployeePayoutRoutes),
    ],
	providers: [ EmployeePayoutService, CommonService ],
    declarations: [ EmployeePayoutListComponent, EmployeePayoutDetailComponent ]
})

export class EmployeePayoutModule { }