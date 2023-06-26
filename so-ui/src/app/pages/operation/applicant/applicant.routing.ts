import { Routes } from '@angular/router';

 import {ApplicantListComponent } from './_comp/applicant-list.component';
import { ApplicantDetailComponent } from './_comp/applicant-detail.component';

export const ApplicantRoutes: Routes = [
    { path: 'list', component: ApplicantListComponent },
    { path: 'detail', component: ApplicantDetailComponent },
    { path: 'new', component: ApplicantDetailComponent },
    { path: '**',redirectTo: 'list' }
 
];
