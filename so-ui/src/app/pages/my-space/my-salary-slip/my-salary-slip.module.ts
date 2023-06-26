import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MySalarySlipComponent } from './_comp/my-salary-slip.component';


import { MySalarySlipRoutes } from './my-salary-slip.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [
        CommonModule, NgbModule, FormsModule,
        RouterModule.forChild(MySalarySlipRoutes),
    ],
    declarations: [MySalarySlipComponent]
})

export class MySalarySlipModule { }
