import { Routes } from '@angular/router';
import { AuthGuard } from '../auth/_guards/auth.guard';
import { ThemeComponent } from './theme.component';
import { FaqModule } from '../pages/transaction/faq/faq.module';

export const ThemeRoutes: Routes = [{
    path: '', component: ThemeComponent, 'canActivate': [AuthGuard],
    'children': [
        { path: 'index', loadChildren: '../pages/dashboard/dashboard.module#DashboardModule' },
        { path: 'master', loadChildren: '../pages/master/master.module#MasterModule' },
        { path: 'calendar', loadChildren: '../pages/calendar/calendar.module#CalendarViewModule' },
        { path: 'settings', loadChildren: '../pages/settings/settings.module#SettingsModule' },
        { path: 'transaction', loadChildren: '../pages/transaction/transaction.module#TransactionModule' },
        { path: 'my-space', loadChildren: '../pages/my-space/my-space.module#MySpaceModule' },
        { path: 'my-task', loadChildren: '../pages/my-task/my-task.module#MyTaskModule' },
        { path: 'knowledge-assessment', loadChildren: '../pages/knowledge-assessment/knowledge-assessment.module#KnowledgeAssessmentModule' },
        { path: 'job', loadChildren: '../pages/job/job.module#JobModule' },
        { path: 'authorisation', loadChildren: '../pages/authorisation-setup/authorisation.module#AuthorisationModule'},
        { path: 'org-settings', loadChildren: '../pages/org-settings/org-settings.module#OrgSettingsModule' },
        { path: 'ctc-settings', loadChildren: '../pages/ctc-settings/ctc-settings.module#CtcSettingsModule' },
        { path: 'operation', loadChildren: '../pages/operation/operation.module#OperationModule' },
        { path: 'vendor', loadChildren: '../pages/vendor-management/vendor.module#VendorModule' },
        { path: 'client', loadChildren: '../pages/client-management/client.module#ClientModule' },
        { path: 'director', loadChildren: '../pages/director/director.module#DirectorModule' },
        { path: 'account', loadChildren: '../pages/account/account.module#AccountModule' },
        { path: 'recruitment', loadChildren: '../pages/recruitment/recruitment.module#RecruitmentModule' },
        { path: 'appraisal', loadChildren: '../pages/appraisal/appraisal.module#AppraisalModule' },
        { path: 'chat', loadChildren: '../pages/chat/chat.module#ChatModule' },
        { path: 'attendance', loadChildren: '../pages/attendance/attendance.module#AttendanceModule' },
        { path: 'reports', loadChildren: '../pages/report/report.module#ReportModule' },
        { path: 'faq', loadChildren: '../pages/transaction/faq/faq.module#FaqModule' },
        { path: 'file-manager', loadChildren: '../pages/file-manager/file-manager.module#FileManagerModule' },
        { path: '', redirectTo: 'index', pathMatch: 'full' },
    ],
}
];
