import { Routes } from '@angular/router';


export const MySpaceRoutes: Routes = [

    // { path: 'my-leave', loadChildren: './my-leave/leave-application.module#LeaveApplicationModule' },
    // { path: 'leave-approval', loadChildren: './approvals/leave-approval.module#LeaveApprovalModule' },
	{ path: '', loadChildren: './applicant/applicant.module#ApplicantModule' },
    // { path: 'my-profile', loadChildren: './my-profile/my-profile.module#MyProfileModule' },
    // { path: 'expense-claim', loadChildren: './expense-claim/expense-claim.module#ExpenseClaimModule' },
    // { path: '**', redirectTo: 'calendar' },
];
