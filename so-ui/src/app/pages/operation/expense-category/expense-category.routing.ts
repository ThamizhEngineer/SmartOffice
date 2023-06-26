import { Routes } from '@angular/router';

import { ExpenseCategoryComponent } from './comp/expense-category.component';

export const ExpenseCategoryRoutes: Routes = [
    { path: 'expense', component: ExpenseCategoryComponent},
    { path: '**', redirectTo: 'expense' }
];