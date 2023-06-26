import { Routes } from '@angular/router';
import { N1ExpenseListComponent } from './comp/n1-expense-list.component';
import { ExpenseViewComponent } from './comp/n1-expense-view.component';

export const N1ExpenseClaimRoutes: Routes = [
    { path: 'view/:id', component: ExpenseViewComponent},
    { path: 'list', component: N1ExpenseListComponent },
    { path: '**', redirectTo: 'list' }
];
