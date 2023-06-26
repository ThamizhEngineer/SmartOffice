import { Routes } from '@angular/router';
import { BankAdviseReportListComponent } from './_comp/bank-advise-list.component';



export const BankAdviseReportRoutes: Routes = [{
    path: '',
    children: [
     
         { path: 'bank-advise-reports', component: BankAdviseReportListComponent },
         { path: '**', redirectTo: 'bank-advise-reports' }
    ]
}];