import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CommonService } from '../../../shared/common/common.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FullCalendarModule } from 'ng-fullcalendar';
import { SharedModule } from '../../../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import {AttendanceReportListComponent} from './comp/attendance-report-list.component';
import { AttendanceReportService } from './attendance-report.service';
import { AttendanceReportDetailComponent } from './comp/attendance-report-detail.component';
const routes: Routes = [
     {path:':view',component:AttendanceReportListComponent},
     {path:':view/:month/:year/:officeId',component:AttendanceReportListComponent},
     {path:':view/detail/:empCode/:month/:year',component:AttendanceReportDetailComponent},
]

@NgModule({
    imports: [
        CommonModule,NgbModule,SharedModule,FullCalendarModule,FormsModule,ReactiveFormsModule,
        RouterModule.forChild(routes)
    ],
    exports: [],
    declarations: [AttendanceReportListComponent,AttendanceReportDetailComponent],
    providers: [CommonService,AttendanceReportService],
})
export class AttendanceReportModule { }