import { Routes } from '@angular/router';

import { MasterComponent } from './_comp/master.component';


export const MasterRoutes: Routes = [
//  { path: 'employee', loadChildren: './employee/employee.module#EmployeeModule' },
//  { path: 'payroll', loadChildren: './payroll/payroll.module#PayrollModule' },
  // { path: 'customer', loadChildren: './customer/customer.module#CustomerModule' },
  // { path: 'application', loadChildren: './application/application.module#ApplicationModule' },
  // { path: 'vacancy', loadChildren: './vacancy/vacancy.module#VacancyModule' },
//  { path: 'provident-fund', loadChildren: './provident-fund/provident-fund.module#ProvidentFundModule' },
  // { path: 'travel', loadChildren: './travel/travel.module#TravelModule' },
//  { path: 'payroll-adjustment', loadChildren: './payroll-adjustment/payroll-adjustment.module#PayrollAdjustmentModule' },
  //{ path: 'compensation', loadChildren: './compensation/compensation.module#CompensationModule' },
  { path: '**', redirectTo: 'master' },
];
