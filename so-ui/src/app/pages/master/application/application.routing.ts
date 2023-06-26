import { Routes } from '@angular/router';

import { ApplicationListComponent } from '../application/_comp/application-list.component';
import { ApplicationDetailComponent } from '../application/_comp/application-detail.component';
export const ApplicationRoutes: Routes = [{
    path: '',
    children: [
        { path: 'application-list', component: ApplicationListComponent},
        { path: 'application-detail', component: ApplicationDetailComponent },
        { path: '**', redirectTo: 'application-list' }
    ]
}];
