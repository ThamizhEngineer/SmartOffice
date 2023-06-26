import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
 import { PayrollAdjustmentComponent } from './_comp/payroll-adjustment.component';
import {PayrollListComponent}  from './_comp/payroll-list.component';

import { PayRollAdjustmentRoutes } from './payroll-adjustment.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [
        CommonModule, NgbModule, FormsModule,
        RouterModule.forChild(PayRollAdjustmentRoutes),
    ],
    declarations: [PayrollAdjustmentComponent,PayrollListComponent]
})

export class PayrollAdjustmentModule { }
