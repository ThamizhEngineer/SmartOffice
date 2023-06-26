import { Routes } from '@angular/router';

import { PayslipsComponent } from './_comp/payslips.component';
import { NotificationModule } from './notification/notif.module';
import { MySkillComponent } from './my-skill/comp/my-skill.component';
import { IndexModule } from './index/index.module'

export const MySpaceRoutes: Routes = [
    { path: 'payslips', component: PayslipsComponent },
    { path: 'my-skill', component: MySkillComponent },
    { path: 'calendar', loadChildren: './calendar/calendar.module#CalendarModule' },
    { path: 'my-leave', loadChildren: './my-leave/leave-application.module#LeaveApplicationModule' },
    { path: 'leave-approval', loadChildren: './approvals/leave-approval.module#LeaveApprovalModule' },
	{ path: 'attendance', loadChildren: './attendance/attendance.module#AttendanceModule' },
    { path: 'my-profile', loadChildren: './my-profile/my-profile.module#MyProfileModule' },
    { path: 'shift-request', loadChildren: '../transaction/shift-change/shift-change.module#ShiftChangeModule' },
    { path: 'expense-claim', loadChildren: './expense-claim/expense-claim.module#ExpenseClaimModule' },
    { path: 'notif-list', loadChildren: './notification/notif.module#NotificationModule' },
    { path: 'index', loadChildren: './index/index.module#IndexModule' },
    { path: 'my-tar-request', loadChildren: './my-tar-request/my-tar-request.module#MyTarRequestModule' },
    { path: 'appraisal', loadChildren: '../transaction/appraisal/appraisal.module#AppraisalModule' },
    { path: 'appraisal-review', loadChildren: '../transaction/appraisal-review/appraisal-review.module#AppraisalReviewModule' },
    { path: 'training', loadChildren: '../transaction/training-opeining/training-opeining.module#TrainingOpeiningModule' },
    { path: '**', redirectTo: 'calendar' },
];
