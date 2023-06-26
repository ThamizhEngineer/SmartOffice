import { Routes } from '@angular/router';

import { PurchaseOrderListComponent } from './_comp/purchase-order-list.component';
import { PurchaseOrderDetailComponent } from './_comp/purchase-order-detail.component';
import { PurchaseOrderViewComponent } from './_comp/purchase-order-view.component';
export const PurchaseOrderRoutes: Routes = [
    { path: 'purchase-order-list', component: PurchaseOrderListComponent },
    { path: 'purchase-order-detail/:_id', component: PurchaseOrderDetailComponent },
    { path: 'purchase-order-new', component: PurchaseOrderDetailComponent },
    { path: 'purchase-order-view/:_id', component: PurchaseOrderViewComponent },
    { path: '**', redirectTo: 'purchase-order-list' },
];
