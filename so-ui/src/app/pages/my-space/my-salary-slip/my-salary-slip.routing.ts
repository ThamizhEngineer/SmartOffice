import { Routes } from '@angular/router';


import { MySalarySlipComponent } from './_comp/my-salary-slip.component';
export const MySalarySlipRoutes: Routes = [
  
    { path: 'my-salary-slip', component: MySalarySlipComponent },
    { path: '**',redirectTo: 'my-salary-slip' }
 
];
