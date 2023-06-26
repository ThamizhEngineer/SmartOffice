import { Routes } from '@angular/router';

import { JobFeedbackDetailComponent } from './_comp/detail.component';
import { JobFeedbackListComponent } from './_comp/list.component';


export const JobFeedbackRoutes: Routes = [
    { path: 'list', component: JobFeedbackListComponent },
    { path: 'detail/:_id', component: JobFeedbackDetailComponent },
    { path: '**', redirectTo: 'list' },
];
