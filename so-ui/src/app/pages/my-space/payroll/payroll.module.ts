import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PayrollDetailComponent } from './_comp/payroll-detail.component';


import { PayRollRoutes } from './payroll.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [
        CommonModule, NgbModule, FormsModule,
        RouterModule.forChild(PayRollRoutes),
    ],
    declarations: [PayrollDetailComponent]
})

export class PayrollModule { }
