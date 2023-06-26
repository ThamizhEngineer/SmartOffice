import { Routes } from '@angular/router';

import { DepartmentMasterComponent } from './comp/department-master.component';
// import { DepartmentMasterDetailComponent } from './comp/department-master-detail.component';
export const DepartmentMasterRoutes: Routes = [{
    path: '',
    children: [
        { path: 'dept-list', component: DepartmentMasterComponent},
        // { path: 'dept-master-detail', component: DepartmentMasterDetailComponent},
        { path: '**', redirectTo: 'dept-list' }
    ]
}];

