import { Routes } from '@angular/router';

import { JobReportListComponent } from './_comp/job-report-list.component';
import { JobReportDetailComponent } from './_comp/job-report-detail.component';


export const JobReportRoutes: Routes = [
    { path: 'job-report-list', component: JobReportListComponent },
    { path: 'job-report-detail/:_id', component: JobReportDetailComponent },
    
    { path: '**', redirectTo: 'job-report-list' },
];