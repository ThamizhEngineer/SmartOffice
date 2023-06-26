import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule } from '../../../shared/shared.module';
import { RouterModule } from '@angular/router';
import { CommonService } from '../../../shared/common/common.service';
import { payoutAdjustmentComponent } from './comp/payout-adjustment-list.component';
import { payoutAdjustmentRoutes } from './payout-adjustment.routing';
import { payoutAdjustmentService } from './payout-adjustment.service';
import { payoutAdjustmentDetailComponent } from './comp/payout-adjustment-detail.component';

@NgModule({
    imports: [ CommonModule, FormsModule, NgbModule, SharedModule,
        RouterModule.forChild(payoutAdjustmentRoutes)],
    exports: [],
    declarations: [payoutAdjustmentComponent, payoutAdjustmentDetailComponent],
    providers: [payoutAdjustmentService, CommonService],
})
export class payoutAdjustmentModule { }
