import { Routes } from '@angular/router';

import { UserAccessShowComponent } from './access/_comp/show.component';
import { AttendanceDirectShowComponent } from './direct-attendance/_comp/direct.component';
import { LeaveApprovalRequestComponent } from './hr-leave-approval/comp/leave-approvel-view.component';
import { UserGroupComponent } from './user-group/comp/user-group.component';
import { UserGroupEmpComponent } from './user-group/comp/user-group-employee.component';
import { LeaveTypeListComponent } from './leave-type/_comp/leave-type-list.component';
import { LeaveBalanceComponent } from './leave-balance/leave-balance.component';
import { DayRangeComponent } from './day-range/_comp/day-range.component';
import { BankInfoComponent } from './bank-info/bank-info.component';
// import { EmployeeServiceComponent } from '../transaction/employee-service.component';
import { } from './admin-view/admin-emp-profile.module'
import { DocumentManagementComponent } from './document-management/document-manangement-list.component';
import { DocTypeComponent } from './doc-type/comp/doc-type.component';
import { AttendanceReportComponent } from './attendance-report/comp/attendance-report.component';
import { SkillAnalysisModule } from '../transaction/skill-analysis/skill-analysis.module';
// import { FunctionGroupListComponent } from './function-group/comp/function-group-list.component';
import { FunctionGroupModule } from '../job/function-group/function-group.module';
import { PaymentTermsComponent } from './payment-terms/comp/payment-terms.component';
import { AttendanceCheckModule } from '../transaction/attendance-check/attendance.module';
import { AttendanceShiftComponent } from './attendance-shift/comp/attendance-shift.component';
import { AttendanceShiftModule } from  '../master/attendance-shift/attendance-shift.module';
export const OperationRoutes: Routes = [
    { path: 'direct-attendance', component: AttendanceDirectShowComponent },
	{ path: 'direct-attendance/:id', component: AttendanceDirectShowComponent },
	{ path: 'hr-leave-approval', component: LeaveApprovalRequestComponent },
	{ path: 'access', component: UserAccessShowComponent },
	{ path: 'leave-balances', component: LeaveBalanceComponent },
	{ path: 'user-group', component: UserGroupComponent },
	{ path: 'user-group-emp', component: UserGroupEmpComponent },
	// { path: 'function-group', component: FunctionGroupListComponent },
	{ path: 'employee', loadChildren: './employee/employee.module#EmployeeModule' },
	{ path: 'function-group', loadChildren: '../job/function-group/function-group.module#FunctionGroupModule' },
	{ path: 'office-master', loadChildren: '../job/office-master/office-master.module#OfficeMasterModule' },
	{ path: 'announcement', loadChildren: './announcement/announcement.module#AnnouncementModule' },
	{ path: 'shift-request', loadChildren: '../transaction/shift-change/shift-change.module#ShiftChangeModule' },
	{path:'contractor',loadChildren:'./contractor-master/contractor.module#ContractorModule'},
	{path:'manager-swap',loadChildren:'./manager-swap/manager-swap.module#ManagerSwapModule'},
	{ path: 'applicant', loadChildren: './applicant/applicant.module#ApplicantModule' },
	{ path: 'payout-process', loadChildren: './payout-process/payout-process.module#PayoutProcessModule' },
	{ path: 'process-salary', loadChildren: './process-salary/process-salary.module#ProcessSalaryModule' },
	{ path: 'employee-payout', loadChildren: './employee-payout/employee-payout.module#EmployeePayoutModule' },
	{ path: 'provident-fund', loadChildren: './provident-fund/provident-fund.module#ProvidentFundModule' },	
	{ path: 'expense-approval', loadChildren: './expense-approval/expense-approval.module#ExpenseApprovalModule' },
	{ path: 'upload-payslips', loadChildren: './payslips/payslips.module#PayslipsModule' },
	{ path: 'payout-adjustment', loadChildren: './payout-adjustment/payout-adjustment.module#payoutAdjustmentModule' },
	{ path: 'appraisal-master', loadChildren:'../master/appraisal/appraisal.module#AppraisalModule' },
	{ path: 'exit-interview', loadChildren: './exit-interview/exit-interview.module#ExitInterviewModule' },
	{ path: 'cash-balance', loadChildren: './cash-balance/cash-balance.module#CashBalanceModule' },
	{ path: 'admin-profile-list', loadChildren: './admin-view/admin-emp-profile.module#AdminProfileModule' },
	{ path: 'employee-convention', loadChildren:'../master/employee-convention/employee-convention.module#EmployeeConventionModule' },
	{ path: 'office-calendar', loadChildren:'../master/office-calendar/office-calendar.module#OfficeCalendarModule' },
	{ path:'leave-type',component:LeaveTypeListComponent },
	{ path:'day-range',component:DayRangeComponent },
	{ path:'attendance-shift', loadChildren:'../master/attendance-shift/attendance-shift.module#AttendanceShiftModule' },
	{ path:'payment-terms',component:PaymentTermsComponent },
	{ path: 'bank-info', component:BankInfoComponent },
	{ path: 'attendance-report', component:AttendanceReportComponent },
	{path:'document-management',component:DocumentManagementComponent},
	{path:'doc-type',loadChildren: './doc-type/doc-type.module#DocTypeModule' },
	{path:'attendance-update',loadChildren: '../transaction/attendance-check/attendance.module#AttendanceCheckModule' },
	{path:'expense-category',loadChildren: './expense-category/expense-category.module#ExpenseCategoryModule' },
	{path:'notification-rule',loadChildren: './notification-rule/notification-rule.module#NotificationRuleModule' },
	{ path: 'department-master', loadChildren:'../master/department-master/department-master.module#DepartmentMasterModule' },
	{ path: 'appraisal', loadChildren:'../transaction/appraisal/appraisal.module#AppraisalModule' },  
	{ path: 'employee-service-record', loadChildren:'../transaction/employee-service-record/employee-service.module#EmployeeServiceModule' },
	{ path: 'skill-analysis', loadChildren:'../transaction/skill-analysis/skill-analysis.module#SkillAnalysisModule' },
	// { path: 'employee-service-record', loadChildren:'../transaction/employee-service-record/employee-service.module#EmployeeServiceModule' },
	{ path: 'fiscal-year', loadChildren:'.././org-settings/org-settings.module#OrgSettingsModule' },
	{ path: '**', redirectTo: 'direct-attendance'}
];

