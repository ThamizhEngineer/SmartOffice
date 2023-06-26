import { Routes } from '@angular/router';


import { ApplicantDetailComponent } from './_comp/applicant-detail.component';

export const ApplicantRoutes: Routes = [
  
    { path: 'detail', component: ApplicantDetailComponent },
   
    { path: '**',redirectTo: 'detail' }
 
];
