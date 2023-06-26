import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from './../../shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonService } from '../../shared/common/common.service';
import { FullCalendarModule } from 'ng-fullcalendar';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';

import { UserAccessShowComponent } from './access/_comp/show.component';
import { AttendanceDirectShowComponent } from './direct-attendance/_comp/direct.component';

import { UserAccessService } from './access/access.service';
import { AttendanceService } from './direct-attendance/attendance.service';

import { OperationRoutes } from './operation.routing';
import { FileModule } from '../../shared/file.module';
import { DtdiffPipe } from './hr-leave-approval/model/datediff';
import { LeaveApprovalRequestComponent } from './hr-leave-approval/comp/leave-approvel-view.component';
import { LeaveApplicationService } from './hr-leave-approval/hr-leave-approval.service';

import { UserGroupComponent } from './user-group/comp/user-group.component';
import { UserGroupEmpComponent } from './user-group/comp/user-group-employee.component';
import { LeaveTypeService } from './leave-type/leave-type.service';
import { LeaveTypeListComponent } from './leave-type/_comp/leave-type-list.component';
import { LeaveBalanceComponent } from './leave-balance/leave-balance.component';
import { DayRangeComponent } from './day-range/_comp/day-range.component';
import { DayRangeService } from './day-range/day-range.service';
import { PaymentTermsComponent } from './payment-terms/comp/payment-terms.component';
import { PaymentTermsService } from './payment-terms/payment-terms.service';
import { BankInfoComponent } from './bank-info/bank-info.component';
import { BankInfoService } from './bank-info/bank-info.service';
import { SortablejsModule } from 'angular-sortablejs';
import {DocumentManagementComponent} from './document-management/document-manangement-list.component';
import {DocumentManagementService} from  './document-management/documentmanagement.service'	
import { NgxPaginationModule } from 'ngx-pagination';
import { AttendanceReportComponent } from './attendance-report/comp/attendance-report.component';
import { AttendanceReportService } from './attendance-report/attendance-report.service';
import { TypeaheadPopupDirective } from './popup.directive';
import { AttendanceShiftComponent } from './attendance-shift/comp/attendance-shift.component';
import { AttendanceShiftService } from './attendance-shift/attendance-shift.service';



@NgModule({
	imports: [
		CommonModule, NgbModule, FormsModule,FileModule, SharedModule, FullCalendarModule, PerfectScrollbarModule,SortablejsModule,PerfectScrollbarModule,NgxPaginationModule,
		RouterModule.forChild(OperationRoutes),
	],
	declarations: [ UserAccessShowComponent,AttendanceShiftComponent,LeaveBalanceComponent, AttendanceDirectShowComponent,DtdiffPipe,LeaveApprovalRequestComponent,UserGroupComponent,UserGroupEmpComponent,LeaveTypeListComponent,DayRangeComponent,BankInfoComponent,DocumentManagementComponent,AttendanceReportComponent,TypeaheadPopupDirective, PaymentTermsComponent],
	providers:[ UserAccessService,AttendanceShiftService, AttendanceService,CommonService,LeaveApplicationService,LeaveTypeService,DayRangeService,BankInfoService,DocumentManagementService,AttendanceReportService, PaymentTermsService]
})
export class OperationModule { }
