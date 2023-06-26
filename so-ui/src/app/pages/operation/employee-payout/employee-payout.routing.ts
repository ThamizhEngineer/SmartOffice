import { Routes } from '@angular/router';

import { EmployeePayoutDetailComponent } from './_comp/employee-payout-detail.component';
import { EmployeePayoutListComponent } from './_comp/employee-payout-list.component';

export const EmployeePayoutRoutes: Routes = [
    

    { path: '', component: EmployeePayoutListComponent },
    { path: 'new', component: EmployeePayoutDetailComponent },
    { path: 'detail/:_id', component: EmployeePayoutDetailComponent },
	{ path: '**',redirectTo: '' }
 
];
