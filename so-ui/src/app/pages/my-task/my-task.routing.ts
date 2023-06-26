import { Routes } from '@angular/router';
import { AnnouncementComponent } from '../operation/announcement/comp/announcement.component';
import { ShiftChangeModule } from '../transaction/shift-change/shift-change.module';
import { ExitInterviewModule } from '../operation/exit-interview/exit-interview.module';

export const MyTaskRoutes: Routes = [
    { path: 'my-tar-n1', loadChildren: './my-tar-n1-approve/my-tar-n1-approve.module#MyTarN1ApproveModule' },
    { path: 'my-tar-acc2-approve', loadChildren: './my-tar-acc2-approve/my-tar-acc2-approve.module#MyTarAcc2ApprovceModule' },
    { path: 'leave-approvel', loadChildren: './leave-approval/leave-approvel.module#LeaveApprovalModule' },
    { path: 'shift-request', loadChildren: '../transaction/shift-change/shift-change.module#ShiftChangeModule' },
    { path: 'n1-expense-approval', loadChildren: './n1-expense-approval/n1-expense.module#N1ExpenseModule' },
    { path: 'acc-expense', loadChildren: './acc-expense-approval/acc-expense.module#AccExpenseModule' },
    { path: 'my-team-skill', loadChildren: './my-team-skill/my-team-skill.module#MyTeamSkillProfileModule' },
    { path: 'appraisal', loadChildren: '../transaction/appraisal/appraisal.module#AppraisalModule' },
    { path: 'appraisal-review', loadChildren: '../transaction/appraisal-review/appraisal-review.module#AppraisalReviewModule' },
    { path: 'training', loadChildren: '../transaction/training-opeining/training-opeining.module#TrainingOpeiningModule' },
    { path: 'exit-interview', loadChildren: '../operation/exit-interview/exit-interview.module#ExitInterviewModule' },
    { path: '**', redirectTo: '/index' },
];
