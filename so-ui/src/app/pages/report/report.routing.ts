import { Routes } from '@angular/router';
import { OrderReportComponent } from './order-report/order-report.component';
import { RevenueComponent } from './revenue/revenue.component';
// import { AuthorisationModule } from '../authorisation-setup/authorisation.module';
import { AttendanceReportModule } from './attendance-report-summary/attendance-report.module'
import { EmployeeLocationModule } from './emp-location/emp-location.module';
export const Reportroutes: Routes = [
    { path: 'order-report', component: OrderReportComponent },
    { path: 'revenue-report', component: RevenueComponent },
    { path: 'attendance-report', loadChildren:'./attendance-report-summary/attendance-report.module#AttendanceReportModule'},
    { path: 'emp-location-report', loadChildren:'./emp-location/emp-location.module#EmployeeLocationModule'},
    // { path: 'setup', loadChildren:'../authorisation-setup/authorisation.module#AuthorisationModule'}
]