import { Routes } from '@angular/router';
import { ExpenseClaimListComponent } from './_comp/expense-claim-list.component';
import { ExpenseClaimDetailComponent } from './_comp/expense-claim-detail.component';

export const ExpenseClaimRoutes: Routes = [
    { path: 'detail/:id', component: ExpenseClaimDetailComponent},
    { path: 'new', component: ExpenseClaimDetailComponent},
    { path: 'list', component: ExpenseClaimListComponent },
    { path: '**', redirectTo: 'list' }
];
