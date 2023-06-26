import { Routes } from '@angular/router';

 import { EmployeeListComponent } from './_comp/employee-list.component';
import { EmployeeDetailComponent } from './_comp/employee-detail.component';

export const EmployeeRoutes: Routes = [
    { path: 'list', component: EmployeeListComponent },
    { path: 'detail', component: EmployeeDetailComponent },
    { path: 'new', component: EmployeeDetailComponent },
    { path: '**',redirectTo: 'list' }
 
];
