import { Routes } from '@angular/router';

import { ResourceSchedulerListComponent } from './_comp/resource-scheduler-list.component';
import { ResourceSchedulerDetailComponent } from './_comp/resource-scheduler-detail.component';

export const ResourceSchedulerRoutes: Routes = [
    { path: '', component: ResourceSchedulerListComponent },
    { path: 'detail/:_id', component: ResourceSchedulerDetailComponent },
    { path: '**', redirectTo: '' },
];