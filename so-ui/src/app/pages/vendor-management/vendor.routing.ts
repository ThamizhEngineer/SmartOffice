import { Routes } from '@angular/router';

import { VendorProfileComponent } from './_comp/vendor-profile.component';
import { VendorProfileEditComponent } from './_comp/vendor-profile-edit.component';
import { VendorListComponent } from './_comp/vendor-list.component';
import { VendorProfileAddComponent } from './_comp/vendor-profile-add.component';
// import { PurchaseOrderModule } from '../job/purchase-order/purchase-order.module';

export const VendorRoutes: Routes = [
    
    { path: 'view/:_id', component: VendorProfileComponent },
    { path: 'new', component: VendorProfileAddComponent },
    { path: 'edit/:_id', component: VendorProfileEditComponent },
    { path: 'edit/', component: VendorProfileEditComponent },
    { path: 'list', component: VendorListComponent },
    { path: 'purchase-order',loadChildren:'../job/purchase-order/purchase-order.module#PurchaseOrderModule'},
    { path: '**',redirectTo: 'list' }
];
