import { Routes } from '@angular/router';

import { SaleOrderListComponent } from '../sale-order/_comp/sale-order-list.component';
import { SaleOrderDetailComponent} from '../sale-order/_comp/sale-order-detail.component';

export const SaleOrderRoutes: Routes = [{
    path: '',
    children: [
        { path: 'new', component: SaleOrderDetailComponent},
        { path: 'detail/:_id', component: SaleOrderDetailComponent},
        { path: 'list', component: SaleOrderListComponent },
        { path: '**', redirectTo: 'list' }
    ]
}];
