import { Routes } from '@angular/router';

import { ProcessSalaryListComponent } from './_comp/process-salary-list.component';
export const ProcessSalaryRoutes: Routes = [
    

    { path: 'list', component: ProcessSalaryListComponent },
    // { path: 'new', component: PayoutProcessDetailComponent },
    // { path: 'detail/:_id', component: PayoutProcessDetailComponent },


	{ path: '**',redirectTo: 'list' }
 
];
