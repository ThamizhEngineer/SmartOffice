import { Routes } from '@angular/router';

import { LeaveApprovalRequestComponent } from './_comp/request.component';

export const LeaveApprovalRoutes: Routes = [{
    path: '',
    children: [
        { path: '', component: LeaveApprovalRequestComponent},
        { path: '**', redirectTo: '' }
    ]
}];
