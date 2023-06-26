import { Routes } from '@angular/router';

// import { JobReportListComponent } from './_comp/job-report-list.component';
// import { JobReportDetailComponent } from './_comp/job-report-detail.component';
import { ContractorListComponent } from './_comp/contractor-list.component';
import { ContractorDetailComponent } from './_comp/contractor-detail.component';
export const ContractorRoutes: Routes = [
    { path: 'contractor-list', component: ContractorListComponent },
    { path: 'contractor-detail', component: ContractorDetailComponent },
    { path: 'new', component: ContractorDetailComponent },
    
    { path: '**', redirectTo: 'contractor-list' },
];