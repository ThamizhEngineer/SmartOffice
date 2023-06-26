import { Routes } from '@angular/router';
import { FunctionGroupListComponent } from './comp/function-group-list.component';
import { FunctionGroupDetailComponent } from './comp/function-group-detail.component';

export const FunctionGroupRoutes: Routes = [
    { path: 'list', component: FunctionGroupListComponent },
    { path: 'detail/:id', component: FunctionGroupDetailComponent },
    { path: 'new', component: FunctionGroupDetailComponent },
    { path: '**',redirectTo: 'list' }
 
];
