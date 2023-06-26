import { Routes } from '@angular/router';

import { LeaveApplicationShowComponent } from './_comp/show.component';
import { LeaveApplicationCreateComponent } from './_comp/create.component';

export const LeaveApplicationRoutes: Routes = [{
    path: '',
    children: [
        { path: '', component: LeaveApplicationShowComponent},
        { path: 'create', component: LeaveApplicationCreateComponent},
        { path: 'detail', component: LeaveApplicationCreateComponent},
        { path: '**', redirectTo: '' }
    ]
}];
