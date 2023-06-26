import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { SharedModule } from './../../../shared/shared.module';

import { LeaveApprovalRequestComponent } from './comp/leave-approvel-view.component';
import { LeaveApprovelDetailComponent } from './comp/leave-approvel-detail.component';

import { LeaveApprovalRoutes } from './leave-approvel.routing';
import { LeaveApplicationService } from './leave-approvel.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { DtdiffPipe } from './_model/datediff';
import { DatePipe } from '@angular/common';


@NgModule({
    imports: [
        CommonModule, NgbModule, FormsModule, SharedModule,
        RouterModule.forChild(LeaveApprovalRoutes),
    ],
	providers: [ LeaveApplicationService,DatePipe ],
    declarations: [ LeaveApprovalRequestComponent,LeaveApprovelDetailComponent, DtdiffPipe ]
})

export class LeaveApprovalModule { }
