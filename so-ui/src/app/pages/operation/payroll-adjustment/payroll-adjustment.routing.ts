import { Routes } from '@angular/router';


import { PayrollAdjustmentComponent } from './_comp/payroll-adjustment.component';
import {PayrollListComponent} from './_comp/payroll-list.component'

export const PayRollAdjustmentRoutes: Routes = [
  
    { path:'detail', component: PayrollAdjustmentComponent },
    { path:'', component: PayrollListComponent },
    { path: '**',redirectTo: '' }
 
];
