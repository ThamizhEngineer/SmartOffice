import { Routes } from '@angular/router';

 import { EmployeeConventionListComponent } from './_comp/employee-convention-list.component';
import { EmployeeConventionDetailComponent } from './_comp/employee-convention-detail.component';

export const EmployeeConventionRoutes: Routes = [
    { path: 'list', component: EmployeeConventionListComponent },
    { path: 'detail', component: EmployeeConventionDetailComponent },
    { path: '**',redirectTo: 'list' }
 
];
