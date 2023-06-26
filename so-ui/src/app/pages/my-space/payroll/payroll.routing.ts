import { Routes } from '@angular/router';


import { PayrollDetailComponent } from './_comp/payroll-detail.component';
export const PayRollRoutes: Routes = [
  
    { path: 'payroll-detail', component: PayrollDetailComponent },
    { path: '**',redirectTo: 'payroll-detail' }
 
];
