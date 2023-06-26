import { CashBalanceComponent } from './comp/cash-balance-list.component';
import {CashBalanceDetailComponent} from './comp/cash-balance-detail.component';

import { Routes } from '@angular/router';
export const CashBalanceRoutes: Routes = [
{ path: 'list', component: CashBalanceComponent },
{ path: 'new', component: CashBalanceDetailComponent},
{ path: 'detail', component: CashBalanceDetailComponent},
{ path: 'detail/:id', component: CashBalanceDetailComponent},
{ path: '**',redirectTo: 'list'}

]

