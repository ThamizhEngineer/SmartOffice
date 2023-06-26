import { Routes } from '@angular/router';
import { ExpenseListComponent } from './comp/acc-expense-list.component';
import { ExpenseViewComponent } from './comp/acc-expense-view.component';

export const AccExpenseClaimRoutes: Routes = [
    { path: 'view/:id', component: ExpenseViewComponent},
    { path: 'list', component: ExpenseListComponent },
    { path: '**', redirectTo: 'list' }
];
