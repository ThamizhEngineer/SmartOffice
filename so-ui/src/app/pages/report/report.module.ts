import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CommonService } from '../../shared/common/common.service';
import { Reportroutes } from './report.routing';
import { OrderReportComponent } from './order-report/order-report.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FullCalendarModule } from 'ng-fullcalendar';
import { SharedModule } from './../../shared/shared.module';
import { ReportService } from './report.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RevenueComponent } from './revenue/revenue.component';

@NgModule({
    imports: [
        CommonModule,NgbModule,SharedModule,FullCalendarModule,FormsModule,ReactiveFormsModule,
        RouterModule.forChild(Reportroutes)
    ],
    exports: [],
    declarations: [OrderReportComponent,RevenueComponent],
    providers: [CommonService,ReportService],
})
export class ReportModule { }
