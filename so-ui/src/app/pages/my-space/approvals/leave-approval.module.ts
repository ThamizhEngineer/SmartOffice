import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { SharedModule } from './../../../shared/shared.module';

import { LeaveApprovalRequestComponent } from './_comp/request.component';

import { LeaveApprovalRoutes } from './leave-approval.routing';
import { LeaveApplicationService } from './../my-leave/leave-application.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { DtdiffPipe } from './_model/datediff';

@NgModule({
    imports: [
        CommonModule, NgbModule, FormsModule, SharedModule,
        RouterModule.forChild(LeaveApprovalRoutes),
    ],
	providers: [ LeaveApplicationService ],
    declarations: [ LeaveApprovalRequestComponent, DtdiffPipe ]
})

export class LeaveApprovalModule { }
