import { Routes } from '@angular/router';


import { PayoutProcessDetailComponent } from './_comp/payout-process-detail.component';
import { PayoutProcessListComponent } from './_comp/payout-process-list.component';
export const PayoutProcessRoutes: Routes = [
    

    { path: 'list', component: PayoutProcessListComponent },
    { path: 'new', component: PayoutProcessDetailComponent },
    { path: 'detail/:_id', component: PayoutProcessDetailComponent },


	{ path: '**',redirectTo: 'list' }
 
];
