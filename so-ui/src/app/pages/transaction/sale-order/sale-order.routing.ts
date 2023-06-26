import { Routes } from '@angular/router';

//import { ApplicationListComponent } from '../application/_comp/application-list.component';
import { SaleOrderListComponent } from '../sale-order/_comp/sale-order-list.component';
import { SaleOrderDetailComponent} from '../sale-order/_comp/sale-order-detail.component';
export const SaleOrderRoutes: Routes = [{
    path: '',
    children: [
        { path: 'sale-order-new', component: SaleOrderDetailComponent},
        { path: 'sale-order-detail/:_id', component: SaleOrderDetailComponent},
        { path: 'sale-order-list', component: SaleOrderListComponent },
        { path: '**', redirectTo: 'sale-order-list' }
    ]
}];
