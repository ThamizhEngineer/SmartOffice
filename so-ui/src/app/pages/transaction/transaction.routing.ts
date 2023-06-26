import { Routes } from '@angular/router';
import { TransactionComponent } from './transaction.component';

import { SkillAnalysisModule } from './skill-analysis/skill-analysis.module';
import { ItemMasterModule } from './item-master/item-master.module'
import { InvoiceModule } from './invoice/invoice.module';
import { AttendanceCheckModule } from './attendance-check/attendance.module';

export const TransactionRoutes: Routes = [
	{ path: '', component: TransactionComponent  },
//	{ path: 'attendance', loadChildren: './attendance/attendance.module#AttendanceModule' },
//	{ path: 'sale-order', loadChildren: './sale-order/sale-order.module#SaleOrderModule' },
    { path: 'shift-request', loadChildren: './shift-change/shift-change.module#ShiftChangeModule' },
    { path: 'skill-analysis', loadChildren: './skill-analysis/skill-analysis.module#SkillAnalysisModule' },
    { path: 'item-master', loadChildren: './item-master/item-master.module#ItemMasterModule' },
    { path: 'invoice', loadChildren: './invoice/invoice.module#InvoiceModule' },
    { path: 'faq', loadChildren: './faq/faq.module#FaqModule' },
    { path: 'attendance', loadChildren: './attendance-check/attendance.module#AttendanceCheckModule' },
    { path: '**', redirectTo: '' },
];
