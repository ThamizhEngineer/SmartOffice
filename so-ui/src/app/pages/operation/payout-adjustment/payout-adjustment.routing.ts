import { Routes } from '@angular/router';
import { payoutAdjustmentComponent } from './comp/payout-adjustment-list.component';
import { payoutAdjustmentDetailComponent } from './comp/payout-adjustment-detail.component';

export const payoutAdjustmentRoutes: Routes = [
    { path: 'list', component: payoutAdjustmentComponent },
    // { path: 'detail/:_id', component: PayslipsDetailComponent },
    { path: 'new/:payMonth/:payYear', component: payoutAdjustmentDetailComponent },
    { path: 'detail', component: payoutAdjustmentDetailComponent },
    { path: '**', redirectTo: 'list' },
]

