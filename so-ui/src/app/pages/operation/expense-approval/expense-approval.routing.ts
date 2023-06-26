import { Routes } from '@angular/router';
import { ExpenseApprovalComponent } from '../expense-approval/_comp/expense-approval.component';
import { ExpenseApprovalViewComponent } from '../expense-approval/_comp/expense-approval-view.component';
import { ExpenseClaimCreateComponent } from './_comp/expense-claim-create.component';

export const ExpenseApprovalRoutes: Routes = [
    { path: 'list', component: ExpenseApprovalComponent },
    { path: 'new', component: ExpenseClaimCreateComponent },
    { path: 'view/:id', component: ExpenseApprovalViewComponent },
    { path: '**', redirectTo: 'list' }
];
