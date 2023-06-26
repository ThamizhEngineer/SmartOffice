import { Routes } from '@angular/router';
import { TransactionComponent } from './transaction.component';


export const TransactionRoutes: Routes = [
	{ path: '', component: TransactionComponent  },
{ path: 'take-test', loadChildren: './take-test/take-test.module#TakeTestModule' },
{ path: 'job-openinig', loadChildren: './take-test/take-test.module#TakeTestModule' },
{ path: '**', redirectTo: '' },
];