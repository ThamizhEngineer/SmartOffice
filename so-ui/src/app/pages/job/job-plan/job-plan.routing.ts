import { Routes } from '@angular/router';

import { JobPlanListComponent } from './_comp/list.component';
import { JobPlanDetailComponent } from './_comp/detail.component';

export const JobPlanRoutes: Routes = [
    { path: 'list', component: JobPlanListComponent },
    { path: 'detail/:_id', component: JobPlanDetailComponent },
    { path: '**', redirectTo: 'list' },
];
